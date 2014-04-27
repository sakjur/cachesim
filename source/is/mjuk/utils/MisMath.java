package is.mjuk.utils;

import java.lang.Math;

/**
* mjuk.is Mathematics Library for Java
*/
public class MisMath {

    /**
    * Returns the unary representation of an integer.
    * <p>
    * Sets the amount of bits in input to one.
    *
    * @param input Amount of bits to set to one
    * @return A digit with a row of bits set to one
    */
    public static long intToUnary(int input)
    {
        return ((long) Math.pow(2, input)-1);
    }

    /**
    * Calculates the logarithm of two for a double.
    */
    public static double log2(double n) {
        return Math.log(n)/Math.log(2.0);
    }

    /**
    * Calculates the logarithm of two for a integer.
    *
    * @see is.mjuk.utils.MisMath#log2(double n)
    */
    public static double log2(int n) {
        return log2((double) n);
    }
}