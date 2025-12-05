package com.dsahub.patterns.adaptor;

interface Duck {
    void quack();
    void fly();
}

interface Turkey {
    void gobble();
    void fly();
}

class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}

class TurkeyAdapter implements Duck {
    Turkey turkey;

    TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i=0; i<5; i++) {
            turkey.fly();
        }
    }
}


public class DuckTest {
    public static void main(String [] args) {
        Turkey turkey = new WildTurkey();
        Duck adapter = new TurkeyAdapter(turkey);
        System.out.println("The adapter says");
        test(adapter);
    }

    static void test(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
