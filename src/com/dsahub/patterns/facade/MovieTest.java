package com.dsahub.patterns.facade;

class Amplifier {
    private int volume;

    void on() {
        System.out.println("Amplifier 🔊 is ON");
    }

    void off() {
        System.out.println("Amplifier 🔇 is OFF");
    }

    void setVolume(int v) {
        this.volume = v;
        System.out.println("Amplifier volume set to " + this.volume);
    }

    void setSurroundSound() {
        System.out.println("Amplifier surround sound enabled 🎧");
    }
}

class DvdPlayer {
    void on() {
        System.out.println("DVD Player 📀 is ON");
    }

    void off() {
        System.out.println("DVD Player 📀 is OFF");
    }

    void play(String movie) {
        System.out.println("DVD Player is playing: \"" + movie + "\" 🎬");
    }

    void stop() {
        System.out.println("DVD Player stopped");
    }

    void eject() {
        System.out.println("DVD ejected ⏏️");
    }
}

class PopcornPopper {
    void on() {
        System.out.println("Popcorn Popper 🍿 is ON");
    }

    void off() {
        System.out.println("Popcorn Popper 🍿 is OFF");
    }

    void pop() {
        System.out.println("Popcorn Popper popping popcorn... 🍿✨");
    }
}

class TheaterLights {
    void dim(int level) {
        System.out.println("Theater lights dimmed to " + level + "% 💡");
    }

    void on() {
        System.out.println("Theater lights are ON 💡");
    }

    void off() {
        System.out.println("Theater lights are OFF 🌑");
    }
}


class HomeTheaterFacade {
    private Amplifier amp;
    private DvdPlayer dvd;
    private PopcornPopper popper;
    private TheaterLights lights;

    public HomeTheaterFacade(Amplifier amp, DvdPlayer dvd,
                             PopcornPopper popper, TheaterLights lights) {
        this.amp = amp;
        this.dvd = dvd;
        this.popper = popper;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch: " + movie);
        popper.on();
        popper.pop();
        lights.dim(10);
        amp.on();
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down theater...");
        lights.dim(100);
        dvd.on();
        amp.on();
        popper.on();
    }
}

public class MovieTest {
    public static void main(String[] args) {
        HomeTheaterFacade home = new HomeTheaterFacade(
                new Amplifier(),
                new DvdPlayer(),
                new PopcornPopper(),
                new TheaterLights()
        );

        home.watchMovie("Avengers Endgame");
        System.out.println("\n");
        home.endMovie();
    }
}