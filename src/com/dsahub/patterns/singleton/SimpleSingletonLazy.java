package com.dsahub.patterns.singleton;

/**
 * Not thread-safe, if 2 threads call it simultaneously, then 2 instances could be created.
 */
public class SimpleSingletonLazy {
    private static SimpleSingletonLazy instance;

    private SimpleSingletonLazy() {
        // private constructor
    }

    public static SimpleSingletonLazy getInstance() {
        if (instance == null) {
            instance = new SimpleSingletonLazy();
        }
        return instance;
    }
}
