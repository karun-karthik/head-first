package com.dsahub.patterns.state;

import java.util.Random;

/**
 * Standalone winner-variant of the Gumball Machine test.
 * Uses the package-visible State interface already defined in
 * GumballMachineTest.java. All classes here are named with the
 * "Winner" suffix to avoid colliding with the original example.
 */

// ---- NoQuarterStateWinner ----
class NoQuarterStateWinner implements State {
    GumballMachineWinner gumballMachine;

    public NoQuarterStateWinner(GumballMachineWinner gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("You inserted a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    public void ejectQuarter() {
        System.out.println("You haven't inserted a quarter");
    }

    public void turnCrank() {
        System.out.println("You turned, but there's no quarter");
    }

    public void dispense() {
        System.out.println("You need to pay first");
    }
}

// ---- HasQuarterStateWinner ----
class HasQuarterStateWinner implements State {
    GumballMachineWinner gumballMachine;
    Random random;

    public HasQuarterStateWinner(GumballMachineWinner gumballMachine) {
        this(gumballMachine, new Random());
    }

    public HasQuarterStateWinner(GumballMachineWinner gumballMachine, Random random) {
        this.gumballMachine = gumballMachine;
        this.random = random;
    }

    public void insertQuarter() {
        System.out.println("You can't insert another quarter");
    }

    public void ejectQuarter() {
        System.out.println("Quarter returned");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    public void turnCrank() {
        System.out.println("You turned the crank...");
        int winner = random.nextInt(10); // 0..9
        if (winner == 0 && gumballMachine.getCount() > 1) {
            gumballMachine.setState(gumballMachine.getWinnerState());
        } else {
            gumballMachine.setState(gumballMachine.getSoldState());
        }
    }

    public void dispense() {
        System.out.println("No gumball dispensed");
    }
}

// ---- SoldStateWinner ----
class SoldStateWinner implements State {
    GumballMachineWinner gumballMachine;

    public SoldStateWinner(GumballMachineWinner gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank");
    }

    public void turnCrank() {
        System.out.println("Turning twice doesn't get you another gumball!");
    }

    public void dispense() {
        gumballMachine.releaseBall();

        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("Oops, out of gumballs!");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}

// ---- SoldOutStateWinner ----
class SoldOutStateWinner implements State {
    GumballMachineWinner gumballMachine;

    public SoldOutStateWinner(GumballMachineWinner gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("Sorry, machine is sold out");
    }

    public void ejectQuarter() {
        System.out.println("You can't eject, you haven't inserted a quarter yet");
    }

    public void turnCrank() {
        System.out.println("You turned, but there are no gumballs");
    }

    public void dispense() {
        System.out.println("No gumball dispensed");
    }
}

// ---- WinnerState ----
class WinnerState implements State {
    GumballMachineWinner gumballMachine;

    public WinnerState(GumballMachineWinner gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    public void insertQuarter() {
        System.out.println("Please wait, we're already giving you a gumball");
    }

    public void ejectQuarter() {
        System.out.println("Sorry, you already turned the crank");
    }

    public void turnCrank() {
        System.out.println("Turning again doesn't give you more gumballs");
    }

    public void dispense() {
        System.out.println("YOU'RE A WINNER! You get two gumballs for your quarter");
        // Release first gumball
        gumballMachine.releaseBall();
        // Release second gumball if available
        if (gumballMachine.getCount() > 0) {
            gumballMachine.releaseBall();
        }

        if (gumballMachine.getCount() > 0) {
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        } else {
            System.out.println("Oops, out of gumballs!");
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}

// Context Class (winner-capable)
class GumballMachineWinner {

    State soldOutState;
    State noQuarterState;
    State hasQuarterState;
    State soldState;
    State winnerState;

    State state;
    int count = 0;

    public GumballMachineWinner(int numberGumballs) {
        this(numberGumballs, new Random());
    }

    public GumballMachineWinner(int numberGumballs, Random random) {
        soldOutState = new SoldOutStateWinner(this);
        noQuarterState = new NoQuarterStateWinner(this);
        hasQuarterState = new HasQuarterStateWinner(this, random);
        soldState = new SoldStateWinner(this);
        winnerState = new WinnerState(this);

        this.count = numberGumballs;

        if (numberGumballs > 0) {
            state = noQuarterState;
        } else {
            state = soldOutState;
        }
    }

    // ACTIONS DELEGATED TO STATE
    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    void releaseBall() {
        System.out.println("A gumball comes rolling out...");
        if (count > 0) {
            count--;
        }
    }

    // SETTERS & GETTERS
    void setState(State state) {
        this.state = state;
    }

    State getSoldOutState() { return soldOutState; }
    State getNoQuarterState() { return noQuarterState; }
    State getHasQuarterState() { return hasQuarterState; }
    State getSoldState() { return soldState; }
    State getWinnerState() { return winnerState; }

    int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "\nGumball Machine (Winner variant) [gumballs = " + count +
                ", state = " + state.getClass().getSimpleName() + "]";
    }
}

public class GumballMachineWinnerTest {
    public static void main(String[] args) {
        // For a deterministic run that demonstrates the winner path you can pass
        // a seeded Random to the constructor. Here we use an unseeded Random so
        // winners happen naturally (~10% of the turns).
        GumballMachineWinner gumballMachine = new GumballMachineWinner(10);

        System.out.println(gumballMachine);

        // Run multiple interactions to increase the chance of seeing a winner
        for (int i = 0; i < 15; i++) {
            System.out.println("\n-- Interaction " + (i + 1) + " --");
            gumballMachine.insertQuarter();
            gumballMachine.turnCrank();
            System.out.println(gumballMachine);
            if (gumballMachine.getCount() == 0) {
                System.out.println("Machine is empty, stopping interactions.");
                break;
            }
        }

        // Demonstrate deterministic winner (optional): seed Random to force a win
        System.out.println("\n--- Deterministic winner demo (seeded) ---");
        GumballMachineWinner seeded = new GumballMachineWinner(3, new Random(0));
        System.out.println(seeded);
        seeded.insertQuarter();
        seeded.turnCrank();
        System.out.println(seeded);
    }
}

