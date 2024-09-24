package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * Makes BigFractions and has some operators like add, subtract, multiply, divide.
 * 
 * Author: Sam Schmidt
 */
public class BigFraction {
  /** The numerator of the fraction. Can be positive, zero or negative. */
  BigInteger num;

  /** The denominator of the fraction. Must be non-negative. */
  BigInteger denom;

  /**
  * Build a new fraction with numerator num and denominator denom.
  * Specifically takes BigIntegers
  *
  * @param numerator
  *   The numerator of the fraction.
  * @param denominator
  *   The denominator of the fraction.
  */
  public BigFraction(BigInteger numerator, BigInteger denominator) {
    BigInteger gcd = findGcd(numerator, denominator);
    this.num = numerator.divide(gcd);
    this.denom = denominator.divide(gcd);
  } // BigFraction(BigInteger, BigInteger)

   /**
   * Build a new fraction with numerator num and denominator denom.
   * Can take integers
   *
   * @param numerator
   *   The numerator of the fraction.
   * @param denominator
   *   The denominator of the fraction.
   */
  public BigFraction(int numerator, int denominator) {
    int gcd = findGcdInt(numerator, denominator);
    this.num = BigInteger.valueOf(numerator/gcd);
    this.denom = BigInteger.valueOf(denominator/gcd);
  } // BigFraction(int, int)

   /**
   * Build a new fraction by parsing a string.
   *
   * @param str
   *   The fraction in string form
   */
  public BigFraction(String str) {
    int length = str.length();
    int indexOfSlash = str.indexOf('/');

    if (indexOfSlash == -1) {
      int numInt = Integer.valueOf(str);
      this.num = BigInteger.valueOf(numInt);
      this.denom = BigInteger.valueOf(1);
    } else {
      int numLength = indexOfSlash;
      int denomLength = (length - 1) - (indexOfSlash);
  
      char[] numArr = new char[numLength];
      char[] denomArr = new char[denomLength];
  
      for (int i = 0; i < numLength; i++) {
        numArr[i] = str.charAt(i);
      } // end for
  
      int j = 0;
      for (int i = (indexOfSlash + 1); i < length; i++) {
        denomArr[j] = str.charAt(i);
        j++;
      } // end for
  
      String numerator = new String(numArr);
      String denominator = new String(denomArr);
  
  
      int numInt = Integer.valueOf(numerator);
      int numDenom = Integer.valueOf(denominator);
  
      int gcd = findGcdInt(numInt, numDenom);
  
      this.num = BigInteger.valueOf(numInt/gcd);
      this.denom = BigInteger.valueOf(numDenom/gcd);
    } //endif

  } // BigFraction

  /**
   * Builds a new fraction as a whole number.
   * @param wholeNumber
   *  A singular whole number
   */
  public BigFraction(int wholeNumber) {
    this.num = BigInteger.valueOf(wholeNumber);
    this.denom = BigInteger.valueOf(1);
  } // BigFraction (BigInteger)

  /**
   * Will simplify a fraction to it's most simple form.
   *
   * @param fraction
   *  A BigFraction
   * @return BigFraction
   *  Will return the given fraction in it's most simple form
   */
  public BigFraction simplify(BigFraction fraction) {
    BigInteger newNum = fraction.num;
    BigInteger newDenom = fraction.denom;
    BigInteger gcd = findGcd(newNum, newDenom);

    newNum = newNum.divide(gcd);
    newDenom = newDenom.divide(gcd);

    if (newNum.compareTo(newDenom) == 0) {
      return new BigFraction(1);
    }
    return new BigFraction(newNum, newDenom);
  } // simplify(BigFraction)

  /**
   * Will find the greatest common divisor (as a BigInteger)
   * @param numerator
   *  A BigInteger that is the numerator of a fraction
   * @param denominator
   *  A BigInteger that is the denominator of a fraction
   * @return
   *  A BigInteger that will be the greatest common divisor
   */
  public BigInteger findGcd(BigInteger numerator, BigInteger denominator){
    BigInteger gcd = BigInteger.valueOf(1);

    BigInteger max;
    if (numerator.compareTo(denominator) > 0) {
      max = numerator;
    } else {
      max = denominator;
    } //endif

    for (int i = 1; max.compareTo(BigInteger.valueOf(i)) > 0; i++) {
      if ((numerator.mod(BigInteger.valueOf(i)).equals(BigInteger.ZERO))
          && (denominator.mod(BigInteger.valueOf(i)).equals(BigInteger.ZERO))) {
        gcd = BigInteger.valueOf(i);
      } //endif
    } //end for

    return gcd;
  }

  /**
   * Will find the greatest common divisor (as an int)
   * @param numerator
   *  An int that is the numerator of a fraction
   * @param denominator
   *  An int that is the denominator of a fraction
   * @return
   *  An int that will be the greatest common divisor
   */
  public int findGcdInt(Integer numerator, Integer denominator){
    int gcd = 1;

    int max;
    if (numerator > denominator) {
      max = numerator;
    } else {
      max = denominator;
    } //endif

    for (int i = 1; i < max; i++) {
      if ((numerator % i == 0)
          && (denominator % i ==0)) {
        gcd = i;
      } //endif
    } //end for

    return gcd;
  }

  /**
   * Add another fraction to this fraction.
   *
   * @param addend
   *   The fraction to add.
   *
   * @return the result of the addition.
   */
  public BigFraction add(BigFraction addend) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and addend's denominator
    resultDenominator = this.denom.multiply(addend.denom);
    // The numerator is this objects numerator multiplied by the addend's
    // denominator and this objects denominator multiplied by the adden's
    // numerator to add
    resultNumerator = (this.num.multiply(addend.denom)).add(addend.num.multiply(this.denom));

    // Return the computed value
    return simplify(new BigFraction(resultNumerator, resultDenominator));
  } // add(BigFraction)


  /**
   * Multiply another fraction by this fraction.
   *
   * @param multiplier
   *   The fraction to add.
   *
   * @return the result of the multiplication
   */
  public BigFraction multiply(BigFraction multiplier) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and multiplier's denominator
    resultDenominator = this.denom.multiply(multiplier.denom);
    // The numerator of the result is the product of this object's
    // denominator and multiplier's numerator
    resultNumerator = this.num.multiply(multiplier.num);

    // Return the computed value
    return simplify(new BigFraction(resultNumerator, resultDenominator));
  } // multiply(BigFraction)

  /**
   * Subtract this fraction from another fraction.
   *
   * @param subtractor
   *   The fraction to add.
   *
   * @return the result of the subtraction
   */
  public BigFraction subtract(BigFraction subtractor) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and subtractor's denominator
    resultDenominator = this.denom.multiply(subtractor.denom);
    // The numerator is this objects numerator multiplied by the subtractor's
    // denominator and this objects denominator multiplied by the subtractor's
    // numerator to add
    resultNumerator =
    (this.num.multiply(subtractor.denom)).subtract(subtractor.num.multiply(this.denom));

    // Return the computed value
    return simplify(new BigFraction(resultNumerator, resultDenominator));
  } // subtract(BigFraction)

  /**
   * Divide this fraction by another fraction.
   *
   * @param divisor
   *   The fraction to divide.
   *
   * @return the result of the division.
   */
  public BigFraction divide(BigFraction divisor) {
    BigInteger resultNumerator;
    BigInteger resultDenominator;

    // The denominator of the result is the product of this object's
    // denominator and divisor's numerator
    resultDenominator = this.denom.multiply(divisor.num);
    // The numerator of the result is the product of this object's
    // numerator and the divisor's denominator
    resultNumerator = this.num.multiply(divisor.denom);

    // Return the computed value
    return simplify(new BigFraction(resultNumerator, resultDenominator));
  } // divide(BigFraction)


  /**
   * Get the denominator of this fraction.
   *
   * @return the denominator
   */
  public BigInteger denominator() {
    return this.denom;
  } // denominator()

  /**
   * Get the numerator of this fraction.
   *
   * @return the numerator
   */

  public BigInteger numerator() {
    return this.num;
  } // numerator()

  /**
   * Convert this fraction to a string for ease of printing.
   *
   * @return a string that represents the fraction.
   */
  public String toString() {
    // Special case: It's zero
    if (this.num.equals(BigInteger.ZERO)) {
      return "0";
    } else if (this.denom.equals(BigInteger.ONE)) {
      return String.valueOf(this.num);
    } // if it's a whole number
    // Lump together the string represention of the numerator,
    // a slash, and the string representation of the denominator
    // return this.num.toString().concat("/").concat(this.denom.toString());
    return this.num + "/" + this.denom;
  } // toString()
} // class BigFraction
