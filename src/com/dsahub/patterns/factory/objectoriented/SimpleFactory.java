package com.dsahub.patterns.factory.objectoriented;

abstract class Pizza {
    public void prepare() {
        System.out.println("Preparing " + this.getClass().getSimpleName());
    }

    public void bake() {
        System.out.println("Baking " + this.getClass().getSimpleName());
    }

    public void cut() {
        System.out.println("Cutting " + this.getClass().getSimpleName());
    }

    public void box() {
        System.out.println("Boxing " + this.getClass().getSimpleName());
    }
}

class CheesePizza extends Pizza {
}

class PepperoniPizza extends Pizza {
}

class ClamPizza extends Pizza {
}

class VeggiePizza extends Pizza {
}

class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        return switch (type) {
            case "cheese" -> new CheesePizza();
            case "pepperoni" -> new PepperoniPizza();
            case "clam" -> new ClamPizza();
            case "veggie" -> new VeggiePizza();
            default -> null;
        };
    }
}

class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = factory.createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
        return pizza;
    }
}

public class SimpleFactory {
    public static void main(String[] args) {
        PizzaStore store = new PizzaStore(new SimplePizzaFactory());
        store.orderPizza("cheese");
        store.orderPizza("pepperoni");
    }
}
