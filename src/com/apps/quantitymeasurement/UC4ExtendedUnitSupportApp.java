package com.apps.quantitymeasurement;

public class UC4ExtendedUnitSupportApp {

    enum Unit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        Unit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    public static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            this.value = value;
            this.unit = unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Quantity other = (Quantity) obj;

            double thisInFeet = this.unit.toFeet(this.value);
            double otherInFeet = other.unit.toFeet(other.value);

            return Double.compare(thisInFeet, otherInFeet) == 0;
        }
    }

    public static void main(String[] args) {
        Quantity q1 = new Quantity(1.0, Unit.YARD);
        Quantity q2 = new Quantity(3.0, Unit.FEET);

        Quantity q3 = new Quantity(1.0, Unit.CENTIMETER);
        Quantity q4 = new Quantity(0.393701, Unit.INCH);

        System.out.println("Yard vs Feet: " + q1.equals(q2));
        System.out.println("CM vs Inch: " + q3.equals(q4));
    }
}