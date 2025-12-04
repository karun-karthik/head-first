package com.dsahub.patterns.command.simple;

interface Command {
    void execute();
}

class Light {
    public void on() {
        System.out.println("Light is on");
    }
    public void off() {
        System.out.println("Light is off");
    }
}

class LightOnCommand implements Command {
    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

class RemoteHandler {
    Command slot;
    public void setCommand(Command command) {
        slot = command;
    }
    public void buttonPressed() {
        slot.execute();
    }
}

public class SimpleRemoteControl {
    public static void main(String[] args) {
        RemoteHandler remote = new RemoteHandler();
        Light light = new Light();
        LightOnCommand lightOnCommand = new LightOnCommand(light);
        remote.setCommand(lightOnCommand);
        remote.buttonPressed();
    }
}