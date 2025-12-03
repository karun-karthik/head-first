package com.dsahub.patterns.singleton;

/**
 * This is thread-safe, lazy-loaded, fast after 1st initialization, applicable after Java 5+ (due to volatile support)
 * A lil complex
 */
public class SimpleSingletonDoubleCheckLocking {
    private volatile static SimpleSingletonDoubleCheckLocking instance;

    private SimpleSingletonDoubleCheckLocking() {
        // private constructor
    }

    public static SimpleSingletonDoubleCheckLocking getInstance() {
        if (instance == null) {
            synchronized (SimpleSingletonDoubleCheckLocking.class) {
                if (instance == null) {
                    instance = new SimpleSingletonDoubleCheckLocking();
                }
            }
        }
        return instance;
    }
}
