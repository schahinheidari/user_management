package com.UserManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCalc {
    UnityTestCalc unityTestCalc = new UnityTestCalc();

    @BeforeEach
    void setUp() {
        unityTestCalc = new UnityTestCalc();
    }

    @Test
    public void addition(){
        int result = unityTestCalc.sum(5, 3);
        Assertions.assertEquals(5, result, "Addition should work correctly");
    }

    @Test
    public void subtraction(){
        int result = unityTestCalc.minus(5, 3);
        Assertions.assertEquals(2, result, "Subtraction should work correctly");
    }

    @Test
    public void multiplication(){
        int result = unityTestCalc.multiply(5, 3);
        Assertions.assertEquals(15, result, "Multiplication should work correctly");
    }

    @Test
    public void division(){
        int result = unityTestCalc.divide(5, 3);
        Assertions.assertEquals(2, result, "Division should work correctly");
    }

    @Test
    public void divisionByZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            unityTestCalc.divide(10, 0);
        }, "Division by zero should throw an exception");
    }
    @Test
    public void additionWithNegativeNumbers() {
        int result = unityTestCalc.sum(-2, -3);
        Assertions.assertEquals(-5, result, "Addition with negative numbers should work correctly");
    }

    @Test
    public void subtractionWithNegativeNumbers() {
        int result = unityTestCalc.minus(-10, -4);
        Assertions.assertEquals(-6, result, "Subtraction with negative numbers should work correctly");
    }

    @Test
    public void additionWithZero() {
        int result = unityTestCalc.sum(0, 5);
        Assertions.assertEquals(5, result, "Addition with zero should work correctly");
    }

    @Test
    public void subtractionWithZero() {
        int result = unityTestCalc.minus(10, 0);
        Assertions.assertEquals(10, result, "Subtraction with zero should work correctly");
    }

    @Test
    public void multiplicationWithZero() {
        int result = unityTestCalc.multiply(0, 7);
        Assertions.assertEquals(0, result, "Multiplication with zero should work correctly");
    }

    @Test
    public void divisionWithZero() {
        int result = unityTestCalc.divide(15, 0);
        Assertions.assertEquals(0, result, "Division with zero should work correctly");
    }

    @Test
    public void additionOverflow() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            unityTestCalc.sum(Integer.MAX_VALUE, 1);
        }, "Addition should throw an exception on overflow");
    }

    @Test
    public void subtractionUnderflow() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            unityTestCalc.minus(Integer.MIN_VALUE, 1);
        }, "Subtraction should throw an exception on underflow");
    }

    @Test
    public void floatingPointAddition() {
        double result = unityTestCalc.sumDouble(2.5, 3.7);
        Assertions.assertEquals(6.2, result, 0.001, "Floating-point addition should work correctly");
    }

    @Test
    public void floatingPointDivision() {
        double result = unityTestCalc.divideDouble(10.0, 3.0);
        Assertions.assertEquals(3.333, result, 0.001, "Floating-point division should work correctly");
    }

    @Test
    public void floatingPointDivisionByZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            unityTestCalc.divideDouble(10.0, 0.0);
        }, "Division by zero should throw an exception");
    }





}
