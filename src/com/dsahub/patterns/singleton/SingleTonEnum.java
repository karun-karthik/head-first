package com.dsahub.patterns.singleton;

/**
 * Simplest, prevents reflection & serialization issues - Guaranteed by JVM
 * Not lazy, cannot extend another class
 */
public enum SingleTonEnum {
    INSTANCE;
}
