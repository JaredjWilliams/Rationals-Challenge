package com.cooksys.ftd.assignments.objects;

public class SimplifiedRational implements IRational {
    private final int numerator;
    private final int denominator;

    /**
     * Determines the greatest common denominator for the given values
     *
     * @param a the first value to consider
     * @param b the second value to consider
     * @return the greatest common denominator, or shared factor, of `a` and `b`
     * @throws IllegalArgumentException if a <= 0 or b < 0
     */
    public static int gcd(int numerator, int denominator) throws IllegalArgumentException {
        if (numerator <= 0 || denominator < 0) {
            throw new IllegalArgumentException();
        }

        int gcd = numerator;
        int divisor = denominator;

        while (divisor != 0) {
            int sub = divisor;
            divisor = gcd % divisor;
            gcd = sub;
        }

        return gcd;
    }

    /**
     * Simplifies the numerator and denominator of a rational value.
     * <p>
     * For example:
     * `simplify(10, 100) = [1, 10]`
     * or:
     * `simplify(0, 10) = [0, 1]`
     *
     * @param numerator   the numerator of the rational value to simplify
     * @param denominator the denominator of the rational value to simplify
     * @return a two element array representation of the simplified numerator and denominator
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public static int[] simplify(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
        int gcd = numerator != 0 ? gcd(Math.abs(numerator), Math.abs(denominator)) : 1;

        return new int[] { numerator / gcd, denominator / gcd };
    }

    /**
     * Constructor for rational values of the type:
     * <p>
     * `numerator / denominator`
     * <p>
     * Simplification of numerator/denominator pair should occur in this method.
     * If the numerator is zero, no further simplification can be performed
     *
     * @param numerator   the numerator of the rational value
     * @param denominator the denominator of the rational value
     * @throws IllegalArgumentException if the given denominator is 0
     */
    public SimplifiedRational(int numerator, int denominator) throws IllegalArgumentException {

        if (denominator == 0) {
            throw new IllegalArgumentException();
        }

        int[] simplified = simplify(numerator, denominator);

        this.numerator = simplified[0];
        this.denominator = simplified[1];
    }

    /**
     * @return the numerator of this rational number
     */
    @Override
    public int getNumerator() {
        return numerator;
    }

    /**
     * @return the denominator of this rational number
     */
    @Override
    public int getDenominator() {
        return denominator;
    }

    /**
     * Specializable constructor to take advantage of shared code between Rational and SimplifiedRational
     * <p>
     * Essentially, this method allows us to implement most of IRational methods directly in the interface while
     * preserving the underlying type
     *
     * @param numerator   the numerator of the rational value to construct
     * @param denominator the denominator of the rational value to construct
     * @return the constructed rational value (specifically, a SimplifiedRational value)
     * @throws IllegalArgumentException if the given denominator is 0
     */
    @Override
    public SimplifiedRational construct(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
        return new SimplifiedRational(numerator, denominator);
    }

    /**
     * @param obj the object to check this against for equality
     * @return true if the given obj is a rational value and its
     * numerator and denominator are equal to this rational value's numerator and denominator,
     * false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SimplifiedRational) {
            SimplifiedRational rational = (SimplifiedRational) obj;
            return rational.getNumerator() == this.numerator && rational.getDenominator() == this.denominator;
        }

        return false;
    }

    /**
     * If this is positive, the string should be of the form `numerator/denominator`
     * <p>
     * If this is negative, the string should be of the form `-numerator/denominator`
     *
     * @return a string representation of this rational value
     */
    @Override
    public String toString() {
        String sign = numerator < 0 != denominator < 0 ? "-" : "";
        return String.format("%s%d/%d", sign, Math.abs(numerator), Math.abs(denominator));
    }
}
