package com.dsahub.patterns.singleton;

/**
 * The outer class loads first → no instance created.
 * The inner static class loads only when referenced (lazy).
 * JVM guarantees class loading is thread-safe.
 * ✔️ Pros - No synchronization, no volatile, lazy, 100% thread-safe due to JVM classloading semantics
 * Extremely clean & small code
 */
public class SimpleSingletonHolder {
    private SimpleSingletonHolder() {
        // private constructor
    }

    private static class Holder {
        private static final SimpleSingletonHolder INSTANCE = new SimpleSingletonHolder();
    }

    public static SimpleSingletonHolder getInstance() {
        return Holder.INSTANCE;
    }
}
