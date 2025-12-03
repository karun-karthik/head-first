package com.dsahub.patterns.singleton;

/**
 * This is thread-safe (because of `SimpleSingletonSynchronisedsynchronized` keyword) but slow
 */
public class SimpleSingletonSynchronised {
    private static SimpleSingletonSynchronised instance;

    private SimpleSingletonSynchronised() {
        // private constructor
    }

    public static synchronized SimpleSingletonSynchronised getInstance() {
        if (instance == null) {
            instance = new SimpleSingletonSynchronised();
        }
        return instance;
    }
}
