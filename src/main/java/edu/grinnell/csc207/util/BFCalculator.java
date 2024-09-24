package edu.grinnell.csc207.util;

/**
 * Where most of the calculator lies; allows for values to be computed with the last entered values.
 * 
 * Author: Sam Schmidt
 */
public class BFCalculator {

  /** The last computed value */
  BigFraction prev = new BigFraction(0);

  /**
    * Retrieves the last computed value
    */
  public BigFraction get() {
    return prev;
  }


  /**
    * adds val to the last component.
    *
    * @param val
    *   a fractional (or whole number) value that will be added
    */
  public void add(BigFraction val) {
    prev.add(val);
    prev = prev.add(val);
  } // add (BigFraction)

  /**
    * subtracts val from the last component.
    *
    * @param val
    *  a fractional value (or whole number) value that will be subtracted
    *  from the last value
    */
  public void subtract(BigFraction val) {
    prev.subtract(val);
    prev = prev.subtract(val);

  } // subtract (BigFraction)

  /**
    * multiplies val from the last component.
    *
    * @param val
    *  a fractional (or whole) value that will be multiplied by the last
    *  value
    */
  public void multiply(BigFraction val) {

    prev.multiply(val);
    prev = prev.multiply(val);

  } // multiply (BigFraction)

  /**
    * divides the last component by val.
    *
    * @param val
    *  the value that will act as the divisor.
    */
  public void divide(BigFraction val) {
    prev.divide(val);
    prev = prev.divide(val);

  } // divide (BigFraction)

  /**
   * Will clear (make 0) the last component.
   */
  public void clear() {
    prev = new BigFraction(0);
  } // clear
} // class BFCalculator
