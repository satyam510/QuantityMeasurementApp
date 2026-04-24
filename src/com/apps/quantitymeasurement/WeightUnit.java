package com.apps.quantitymeasurement;

public enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double toBase(double value) {
        return value * factor; // convert to kg
    }

    public double fromBase(double baseValue) {
        return baseValue / factor;
    }
}