package com.dsahub.patterns.factory.Abstract;

interface Dough {}
interface Sauce {}
interface Cheese {}

class ThinCrustDough implements Dough {}
class ThickCrustDough implements Dough {}

class MarinaraSauce implements Sauce {}
class PlumTomatoSauce implements Sauce {}

class ReggianoCheese implements Cheese {}
class MozzarellaCheese implements Cheese {}

interface PizzaIngredientFactory {
    Dough createDough();
    Sauce createSauce();
    Cheese createCheese();
}

class NYPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new MarinaraSauce();
    }

    @Override
    public Cheese createCheese() {
        return new ReggianoCheese();
    }
}

class ChicagoPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickCrustDough();
    }

    @Override
    public Sauce createSauce() {
        return new PlumTomatoSauce();
    }

    @Override
    public Cheese createCheese() {
        return new MozzarellaCheese();
    }
}

abstract class Pizza {
    String name;
    Dough dough;
    Sauce sauce;
    Cheese cheese;

    abstract void prepare(); // Factory

    public void bake() {
        System.out.println("Bake for 25 minutes at 350 degrees " + name);
    }

    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices " + name);
    }

    public void box() {
        System.out.println("Placing pizza in official PizzaStore box " + name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class CheesePizza extends Pizza {
    PizzaIngredientFactory ingredientFactory;

    public CheesePizza(PizzaIngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    @Override
    void prepare() {
        System.out.println("Preparing " + name);
        dough = ingredientFactory.createDough();
        sauce = ingredientFactory.createSauce();
        cheese = ingredientFactory.createCheese();
    }
}

abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        System.out.println("Creating " + pizza.getName());
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }

    abstract Pizza createPizza(String type);
}

class NYPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            var pizza = new CheesePizza(new NYPizzaIngredientFactory());
            pizza.setName("NY Style Cheese Pizza");
            return pizza;
        }
        return null;
    }
}

class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            var pizza = new CheesePizza(new ChicagoPizzaIngredientFactory());
            pizza.setName("Chicago Style Cheese Pizza");
            pizza.box();
            return pizza;
        }
        return null;
    }
}


public class AbstractPizzaFactory {
    public static void main(String[] args) {
        PizzaStore nyStore = new NYPizzaStore();
        PizzaStore chicagoStore = new ChicagoPizzaStore();

        Pizza pizza = nyStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");

        pizza = chicagoStore.orderPizza("cheese");
        System.out.println("Joel ordered a " + pizza.getName() + "\n");
    }
}
