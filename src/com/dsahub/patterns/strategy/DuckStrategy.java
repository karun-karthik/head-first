package com.dsahub.patterns.strategy;

interface IFlyBehavior {
    void fly();
}

class FlyWithWings implements IFlyBehavior {
    @Override
    public void fly() {
        System.out.println("Flying with wings");
    }
}

class FlyNoWay implements IFlyBehavior {
    @Override
    public void fly() {
        System.out.println("Cannot fly");
    }
}

class FlyRocketPowered implements IFlyBehavior {
    @Override
    public void fly() {
        System.out.println("Flying with rocket");
    }
}

/*****************************************************************************/

interface IQuackBehavior {
    void quack();
}

class Quack implements IQuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack");
    }
}

class Squeak implements IQuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak");
    }
}

/*****************************************************************************/

abstract class Duck {
    private IFlyBehavior flyBehavior;
    private IQuackBehavior quackBehavior;

    public Duck() {
    }

    public void setFlyBehavior(IFlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(IQuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public abstract void display();
}

class MallardDuck extends Duck {
    public MallardDuck() {
        setFlyBehavior(new FlyWithWings());
        setQuackBehavior(new Quack());
    }

    @Override
    public void display() {
        System.out.println("I am a Mallard Duck");
    }
}

class RubberDuck extends Duck {
    public RubberDuck() {
        setFlyBehavior(new FlyNoWay());
        setQuackBehavior(new Squeak());
    }

    @Override
    public void display() {
        System.out.println("I am a Rubber Duck");
    }
}

/*****************************************************************************/

public class DuckStrategy {
    public static void main(String[] args) {
        Duck mallard = new MallardDuck();
        mallard.display();
        mallard.performFly();
        mallard.performQuack();

        Duck rubber = new RubberDuck();
        rubber.display();
        rubber.performFly();
        rubber.performQuack();

        System.out.println("Rubber duck got a new behavior");
        rubber.setFlyBehavior(new FlyRocketPowered());
        rubber.display();
        rubber.performFly();
        rubber.performQuack();
    }
}
