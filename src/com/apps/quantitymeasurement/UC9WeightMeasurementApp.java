package com.apps.quantitymeasurement;

public class UC9WeightMeasurementApp {

    public static void main(String[] args) {

        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Equality: " + w1.equals(w2));

        QuantityWeight converted = w1.convertTo(WeightUnit.POUND);
        System.out.println("Converted: " + converted);

        QuantityWeight sum = w1.add(w2);
        System.out.println("Addition: " + sum);

        QuantityWeight sumTarget = w1.add(w2, WeightUnit.GRAM);
        System.out.println("Addition (GRAM): " + sumTarget);
    }
}
