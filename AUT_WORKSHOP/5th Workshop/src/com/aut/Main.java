package com.aut;
public class Main {

    public static void main(String[] args) {
        int sumResult = AUTMath.sum(10,12);
        assertResult(sumResult, 22, "sum");

        int subtractResult =  AUTMath.subtract(10, 20);
        assertResult(subtractResult, -10, "subtract");

        double multiplyResult  = AUTMath.multiply(89, 12);
        assertResult(multiplyResult, 1068, "multiply");

        double divideResult = AUTMath.divide(11,5);
        assertResult(divideResult, 2.2, "divide");

        double factorial = AUTMath.factorial(7);
        assertResult(factorial, 5040, "factorial");

        double powResult = AUTMath.pow(2,8);
        assertResult(powResult, 256, "pow");

        int fibResult = AUTMath.fib(9);
        assertResult(fibResult, 34, "Fibonacci");
    }

    private static void assertResult(double value, double expected, String operation)  {
        if (value != expected) {
            System.err.println("from " + operation + " function got: "  + value + " expected: " + expected);
            System.exit(1);
        }
        
        System.out.println(operation + " is doing its work as is expected :)");
    }
}