# Quantity Measurement Application

## 1. Introduction

The Quantity Measurement Application is a Java-based system designed to perform operations on measurable quantities across different units. The application supports multiple measurement categories, including length and weight, and allows comparison, conversion, and arithmetic operations between values expressed in different units.

The design follows object-oriented principles and emphasizes scalability, maintainability, and clarity. It is structured to support the addition of new measurement categories without requiring significant changes to existing code.

---

## 2. Features

### 2.1 Length Measurement (UC1 – UC8)

* Supports units: Feet, Inches, Yards, Centimeters
* Equality comparison across different units
* Conversion between supported units
* Addition of quantities:

  * Default result in the unit of the first operand
  * Explicit target unit specification
* Refactored design with a standalone LengthUnit enum responsible for conversion logic

### 2.2 Weight Measurement (UC9)

* Supports units: Kilogram, Gram, Pound
* Equality comparison across different units
* Conversion between supported units
* Addition of quantities:

  * Default result in the unit of the first operand
  * Explicit target unit specification
* Separate WeightUnit and Weight classes following the same architectural pattern as length
* Ensures type safety by preventing comparison between incompatible categories (e.g., length and weight)

---

## 3. Design Principles

The application is implemented using the following software design principles:

* **Single Responsibility Principle**:
  Each class has a clearly defined role. Unit enums handle conversion logic, while quantity classes handle comparison and arithmetic operations.

* **Separation of Concerns**:
  Unit-related operations are isolated from domain logic.

* **Immutability**:
  All quantity objects are immutable. Operations return new objects rather than modifying existing ones.

* **Method Overloading**:
  Provides flexibility for addition operations with and without explicit target units.

* **Delegation**:
  Conversion responsibilities are delegated to the unit enums.

* **Scalability**:
  The architecture supports easy addition of new measurement categories such as volume or temperature.

---

## 4. Project Structure

```text
src/com/apps/quantitymeasurement/

Length.java
LengthUnit.java
Weight.java
WeightUnit.java
QuantityMeasurementApp.java
```

---

## 5. Working Mechanism

### 5.1 Base Unit Normalization

Each measurement category uses a base unit:

* Length: Feet
* Weight: Kilogram

All operations are performed by converting values to the base unit and then converting the result to the desired unit.

### 5.2 Conversion

To convert between units:

1. Convert the value to the base unit
2. Convert from the base unit to the target unit

### 5.3 Addition

To add two quantities:

1. Convert both values to the base unit
2. Add the base values
3. Convert the result to:

   * The unit of the first operand, or
   * A specified target unit

---

## 6. Example Operations

* Quantity(1.0, FEET) + Quantity(12.0, INCHES) results in Quantity(2.0, FEET)
* Quantity(1.0, FEET) + Quantity(12.0, INCHES) with target INCHES results in Quantity(24.0, INCHES)
* Quantity(1.0, KILOGRAM) equals Quantity(1000.0, GRAM)
* Quantity(1.0, KILOGRAM) + Quantity(1000.0, GRAM) results in Quantity(2.0, KILOGRAM)

---

## 7. Validation and Error Handling

* Null units are not allowed and result in an exception
* Non-finite values (NaN or infinite) are rejected
* Cross-category comparisons are not permitted
* All inputs are validated before performing operations

---

## 8. Future Enhancements

* Support for additional measurement categories such as volume and temperature
* Introduction of a generic quantity abstraction for all categories
* Enhanced precision handling for scientific calculations

---

## 9. Author

Satyam Malik
