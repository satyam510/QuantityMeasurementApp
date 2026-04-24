package com.apps.quantitymeasurement;

public class UC5UnitConversionApp {

    enum Unit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeetFactor;

        Unit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double value) {
            return value / toFeetFactor;
        }
    }

    public static double convert(double value, Unit source, Unit target) {
        if (source == null || target == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid number");

        double valueInFeet = source.toFeet(value);
        return target.fromFeet(valueInFeet);
    }

    public static void main(String[] args) {
        System.out.println(convert(1.0, Unit.FEET, Unit.INCHES));
        System.out.println(convert(3.0, Unit.YARDS, Unit.FEET));
        System.out.println(convert(36.0, Unit.INCHES, Unit.YARDS));
        System.out.println(convert(1.0, Unit.CENTIMETERS, Unit.INCHES));
        System.out.println(convert(0.0, Unit.FEET, Unit.INCHES));
    }
}