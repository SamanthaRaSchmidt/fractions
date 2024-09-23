package edu.grinnell.csc207.util;

/**
 * Will allow for the use of registers to store numbers and retrieve them.
 */
public class BFRegisterSet {

  /** The number of letter in the alphabet. */
  BigFraction zeros = new BigFraction(0);

  /** An array, storage, where registers are stored. */
  BigFraction[] storage = new BigFraction[] 
  {zeros, zeros, zeros, zeros, zeros, zeros, zeros, 
    zeros, zeros, zeros, zeros, zeros, zeros, zeros, 
    zeros, zeros, zeros, zeros, zeros, zeros, zeros, 
    zeros, zeros, zeros, zeros, zeros};

  /**
  * Converts a letter into it's associated number 0-25.
  *  (Taken from cipher MP)
  * @param letter
  *    the char that will be turned into a number 0-25
  * @return number
  *    an int that will be returned based on the numerical association with letter
  */
  private static int letter2int(char letter) {
    int number;
    number = (int) letter - (int) 'a';
    return number;
  } // letter2int (char)

  /**
  * Stores the given value in the specifed register.
  * @param register A named storage area where val will be stored
  * @param val A fractional (or whole) value that will be stored in the register
  */
  public void store(char register, BigFraction val) {
    int index = letter2int(register);
    storage[index] = val;
  } // store (char, BigFraction)

  /**
  * Retrieves the value from the register.
  * @param register
  *  A named storage area that stores a value
  * @return
  *  A BigFraction associated with the register
  */
  public BigFraction get(char register) {
    return storage[letter2int(register)];
  } // get (char)
} // class BFRegisterSet
