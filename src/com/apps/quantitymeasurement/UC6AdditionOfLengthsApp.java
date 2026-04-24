package com.apps.quantitymeasurement;

public class UC6AdditionOfLengthsApp {

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

    public static class Quantity {
        private final double value;
        private final Unit unit;

        public Quantity(double value, Unit unit) {
            if (unit == null || !Double.isFinite(value))
                throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        public Quantity add(Quantity other) {
            if (other == null)
                throw new IllegalArgumentException();

            double thisInFeet = this.unit.toFeet(this.value);
            double otherInFeet = other.unit.toFeet(other.value);

            double sumInFeet = thisInFeet + otherInFeet;

            double resultValue = this.unit.fromFeet(sumInFeet);

            return new Quantity(resultValue, this.unit);
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {
        Quantity q1 = new Quantity(1.0, Unit.FEET);
        Quantity q2 = new Quantity(12.0, Unit.INCHES);

        Quantity result1 = q1.add(q2);
        Quantity result2 = q2.add(q1);

        System.out.println(result1);
        System.out.println(result2);

        Quantity q3 = new Quantity(1.0, Unit.YARDS);
        Quantity q4 = new Quantity(3.0, Unit.FEET);

        System.out.println(q3.add(q4));

        Quantity q5 = new Quantity(2.54, Unit.CENTIMETERS);
        Quantity q6 = new Quantity(1.0, Unit.INCHES);

        System.out.println(q5.add(q6));
    }
}