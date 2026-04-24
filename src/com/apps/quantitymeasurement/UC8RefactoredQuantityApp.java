package com.apps.quantitymeasurement;

public class UC8RefactoredQuantityApp {

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
            return unit.convertToBaseUnit(value);
        }

        public QuantityLength convertTo(LengthUnit target) {
            double base = toBase();
            double converted = target.convertFromBaseUnit(base);
            return new QuantityLength(converted, target);
        }

        public QuantityLength add(QuantityLength other, LengthUnit target) {
            double sumBase = this.toBase() + other.toBase();
            double result = target.convertFromBaseUnit(sumBase);
            return new QuantityLength(result, target);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof QuantityLength)) return false;
            QuantityLength q = (QuantityLength) obj;
            return Double.compare(this.toBase(), q.toBase()) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(12.0, LengthUnit.INCHES);

        System.out.println(q1.convertTo(LengthUnit.INCHES));
        System.out.println(q1.add(q2, LengthUnit.FEET));
        System.out.println(q2.equals(new QuantityLength(1.0, LengthUnit.YARDS)));
    }
}