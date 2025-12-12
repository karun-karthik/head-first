Design Patterns Catalog — com.dsahub.patterns

Overview

This repository is a hands-on collection of classic object-oriented design pattern examples implemented in Java under the package com.dsahub.patterns. The README below documents each pattern included in the project, points to the demo/source files, and gives brief run instructions so you can compile and execute demos.

How this README is organized

- Pattern name — short description
- Files — relative file paths implementing or demonstrating the pattern
- Run — recommended class to run for a demo (fully-qualified name)

Quick build & run (plain Java)

1) From the project root compile all sources:

   javac -d out $(find src -name "*.java")

2) Run a demo (example: DJApp):

   java -cp out com.dsahub.patterns.compound.DJApp

Notes:
- Some demos require GUI (Swing) or network (RMI) and may behave differently depending on your environment.
- For audio (beat.wav) demos, ensure the resource is on the classpath. Place beat.wav under src/com/dsahub/patterns/compound/audiobeat/beat.wav and load via getResource (the project includes code guidance to load from the classpath rather than filesystem).

Patterns (files and run instructions)

1) Adaptor
- Description: Converts one interface (e.g., Turkey) to another (e.g., Duck) so objects can collaborate.
- Files:
  - src/com/dsahub/patterns/adaptor/DuckTest.java
- Run: com.dsahub.patterns.adaptor.DuckTest

2) Builder
- Description: Constructs complex objects step-by-step using a Builder to manage optional parts.
- Files:
  - src/com/dsahub/patterns/builder/BurgerTest.java
- Run: com.dsahub.patterns.builder.BurgerTest

3) Command
- Description: Encapsulates requests as objects (commands), enabling parameterization, undo, and macros.
- Files:
  - src/com/dsahub/patterns/command/RemoteControlLoader.java
  - src/com/dsahub/patterns/command/simple/SimpleRemoteControl.java
- Run: com.dsahub.patterns.command.RemoteControlLoader (full demo) or com.dsahub.patterns.command.simple.SimpleRemoteControl (minimal example)

4) Composite
- Description: Composes objects into tree structures and treats leaves and composites uniformly.
- Files:
  - src/com/dsahub/patterns/composite/CompositeTest.java
- Run: com.dsahub.patterns.composite.CompositeTest

5) Compound (Beat / DJ example)
- Description: A compound example that combines multiple patterns (observer, MVC-like, strategy) to build a beat/DJ system with pluggable generators and observers.
- Files:
  - src/com/dsahub/patterns/compound/DJApp.java
  - src/com/dsahub/patterns/compound/BeatGenerator.java
  - src/com/dsahub/patterns/compound/BeatModel.java
  - src/com/dsahub/patterns/compound/BeatModelInterface.java
  - src/com/dsahub/patterns/compound/BeatObserver.java
  - src/com/dsahub/patterns/compound/BeatView.java
  - src/com/dsahub/patterns/compound/BPMObserver.java
  - src/com/dsahub/patterns/compound/DJController.java
  - src/com/dsahub/patterns/compound/SimpleThreadBeatGenerator.java
- Run: com.dsahub.patterns.compound.DJApp
- Extra: If you use an audio-based generator, ensure beat.wav is on the classpath and loaded via getResource. If you encounter "Failed to load beat.wav" ensure the resource path is correct.

6) Decorator
- Description: Adds responsibilities to objects dynamically via decorator objects (e.g., condiments added to beverages).
- Files:
  - src/com/dsahub/patterns/decorator/StarBuzzCoffee.java
- Run: com.dsahub.patterns.decorator.StarBuzzCoffee

7) Facade
- Description: Provides a simplified interface to a complex subsystem (e.g., a home theater setup).
- Files:
  - src/com/dsahub/patterns/facade/MovieTest.java
- Run: com.dsahub.patterns.facade.MovieTest

8) Factory (Simple / Factory Method / Abstract)
- Description: Shows variants of factory patterns for creating related objects without exposing concrete classes.
- Files:
  - src/com/dsahub/patterns/factory/PizzaFactory.java
  - src/com/dsahub/patterns/factory/objectoriented/SimpleFactory.java
  - src/com/dsahub/patterns/factory/Abstract/AbstractPizzaFactory.java
- Run: 
  - com.dsahub.patterns.factory.PizzaFactory (Factory Method example)
  - com.dsahub.patterns.factory.objectoriented.SimpleFactory (Simple Factory)
  - com.dsahub.patterns.factory.Abstract.AbstractPizzaFactory (Abstract Factory example)

9) Iterator
- Description: Provides a uniform way to traverse elements of a collection without exposing its internal structure.
- Files:
  - src/com/dsahub/patterns/iterator/MenuItemTest.java
- Run: com.dsahub.patterns.iterator.MenuItemTest

10) Observer
- Description: Defines a one-to-many dependency; observers (displays) are automatically updated when the subject (weather data) changes. Includes both push and pull styles.
- Files:
  - src/com/dsahub/patterns/observer/WeatherStation.java
  - src/com/dsahub/patterns/observer/WeatherStationPull.java
- Run: com.dsahub.patterns.observer.WeatherStation (push example) or com.dsahub.patterns.observer.WeatherStationPull (pull example)

11) Proxy — Protection
- Description: Controlling access to objects (e.g., PersonBean owner vs non-owner proxy) using dynamic proxies.
- Files:
  - src/com/dsahub/patterns/proxy/protection/ProtectionProxyDemo.java
- Run: com.dsahub.patterns.proxy.protection.ProtectionProxyDemo

12) Proxy — Remote
- Description: Represents a remote object locally (RMI example with a GumballMachine) so clients can interact as if the object was local.
- Files:
  - src/com/dsahub/patterns/proxy/remote/GumballMachineTestDrive.java
  - src/com/dsahub/patterns/proxy/remote/GumballMachineRemote.java
  - src/com/dsahub/patterns/proxy/remote/GumballMonitor.java
  - src/com/dsahub/patterns/proxy/remote/GumballMachine.java
  - src/com/dsahub/patterns/proxy/remote/GumballMonitorTestDrive.java
- Run: com.dsahub.patterns.proxy.remote.GumballMachineTestDrive
- Extra: This demo may start an RMI registry and bind objects. Run in an environment that allows it.

13) Proxy — Virtual
- Description: Lazy-loading placeholder (virtual proxy) defers creating a heavy object (an image) until it is needed.
- Files:
  - src/com/dsahub/patterns/proxy/virtual/ImageProxyTestDrive.java
- Run: com.dsahub.patterns.proxy.virtual.ImageProxyTestDrive
- Extra: Opens a Swing UI and may fetch images over the network.

14) Singleton
- Description: Examples of multiple singleton implementations ensuring a single instance per JVM (eager, lazy, synchronized, holder, enum).
- Files:
  - src/com/dsahub/patterns/singleton/SimpleSingletonDoubleCheckLocking.java
  - src/com/dsahub/patterns/singleton/SimpleSingletonEager.java
  - src/com/dsahub/patterns/singleton/SimpleSingletonHolder.java
  - src/com/dsahub/patterns/singleton/SimpleSingletonLazy.java
  - src/com/dsahub/patterns/singleton/SimpleSingletonSynchronised.java
  - src/com/dsahub/patterns/singleton/SingleTonEnum.java
- Run: No dedicated main; call getInstance() methods from a small test runner or add a short main to exercise each implementation.

15) State
- Description: Encapsulates behavior corresponding to the object's state (GumballMachine) into separate state objects for cleaner state-specific logic.
- Files:
  - src/com/dsahub/patterns/state/GumballMachineTest.java
  - src/com/dsahub/patterns/state/GumballMachineWinnerTest.java
- Run: com.dsahub.patterns.state.GumballMachineTest

16) Strategy
- Description: Encapsulates interchangeable algorithms (e.g., flying, quacking, payment strategies) and lets clients choose them at runtime.
- Files:
  - src/com/dsahub/patterns/strategy/DuckStrategy.java
  - src/com/dsahub/patterns/strategy/PaymentStrategy.java
- Run: com.dsahub.patterns.strategy.DuckStrategy

17) Template
- Description: Defines the skeleton of an algorithm in a base class with hook methods to customize steps in subclasses.
- Files:
  - src/com/dsahub/patterns/template/TemplateWithHook.java
  - src/com/dsahub/patterns/template/DuckSortTest.java
- Run: com.dsahub.patterns.template.TemplateWithHook

Assumptions & Notes

- Java is used; demos should be executed with their fully-qualified class names on the project classpath after compiling.
- RMI (remote proxy) requires an RMI registry and network permissions. If you encounter security or binding exceptions, run with appropriate JVM flags or run the RMI registry manually.
- GUI demos need a graphical environment (not headless).
- Audio demos require resources (beat.wav). Place audio resources on the classpath (see note above).
