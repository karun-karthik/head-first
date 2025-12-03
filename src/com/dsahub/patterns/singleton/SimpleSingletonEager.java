package com.dsahub.patterns.singleton;

/**
 * This is thread-safe, and no synchronization cost.
 * Instance is created even if you never use it, problematic if constructor heavy.
 */
public class SimpleSingletonEager {
    private static final SimpleSingletonEager instance = new SimpleSingletonEager();

    private SimpleSingletonEager() {
        // private constructor
    }

    public static SimpleSingletonEager getInstance() {
        return instance;
    }
}
