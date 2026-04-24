package com.apps.quantitymeasurement;

public class QuantityWeight {
    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.value = value;
        this.unit = unit;
    }

    public QuantityWeight convertTo(WeightUnit target) {
        double base = unit.toBase(value);
        double converted = target.fromBase(base);
        return new QuantityWeight(converted, target);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit target) {
        double sumBase = this.unit.toBase(this.value) + other.unit.toBase(other.value);
        return new QuantityWeight(target.fromBase(sumBase), target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight other = (QuantityWeight) obj;

        double v1 = this.unit.toBase(this.value);
        double v2 = other.unit.toBase(other.value);

        return Math.abs(v1 - v2) < 1e-6;
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}
