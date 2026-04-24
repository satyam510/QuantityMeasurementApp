package com.apps.quantitymeasurement;

public class UC7AdditionWithTargetUnitApp {

    enum LengthUnit {
        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toFeet(double value) {
            return value * toFeet;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeet;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException();
            }
            this.value = value;
            this.unit = unit;
        }

        private double toBase() {
            return unit.toFeet(value);
        }

        public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit targetUnit) {
            if (a == null || b == null || targetUnit == null) {
                throw new IllegalArgumentException();
            }

            double sumFeet = a.toBase() + b.toBase();
            double result = targetUnit.fromFeet(sumFeet);

            return new QuantityLength(result, targetUnit);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength result1 = QuantityLength.add(a, b, LengthUnit.FEET);
        QuantityLength result2 = QuantityLength.add(a, b, LengthUnit.INCHES);
        QuantityLength result3 = QuantityLength.add(a, b, LengthUnit.YARDS);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}