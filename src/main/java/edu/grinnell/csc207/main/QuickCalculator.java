package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFRegisterSet;

/**
 * Will take expressions from the command line and print results.
 * 
 * Author: Sam Schmidt
 */
public class QuickCalculator {

  /** The register to store vals */
  static BFRegisterSet reg1 = new BFRegisterSet();

  /** The last computed value */
  static BigFraction previous = new BigFraction(0);

  /**
   * Will create a new fraction with the given info.
   * @param str
   *  The string that will turn into a fraction
   * @param operator
   *  What will be done to the frac's (+,-, etc.)
   * @param where
   *  Whether the fraction will be the first or second half
   * @return
   */
  public static BigFraction newFrac(String str, String where) {
    if (where.equals("begin")){
      if (Character.isLetter(str.charAt(0))) {
        return reg1.get(str.charAt(0));
      }
      String frac1 = str.substring(0, (str.indexOf(' ')));
      return new BigFraction(frac1);
    } else if (where.equals("end")) {
      if (Character.isLetter(str.charAt(str.indexOf(' ') + 3))) {
        return reg1.get(str.charAt(str.indexOf(' ') + 3));
      }
      String frac2 = str.substring((str.indexOf(' ') + 3), str.length());
      return new BigFraction(frac2);
    } else {
      System.err.println("Invalid where");
      return new BigFraction(0);
    }
  }

  /**
   * Will check through the string for an operator and return a number associated with each.
   * @param str
   *  The String given from the args
   * @return
   *  An int; 1 for add; 2 for subtract; 3 for multiplication
   *  4 for division; 5 for STORE; and -1 for none
   */
  public static int operation(String str){
    if (str.indexOf('+') != -1) {
      return 1;
    } else if (str.indexOf('-') != -1) {
      return 2;
    } else if (str.indexOf('*') != -1) {
      return 3;
    } else if (str.indexOf('/') != -1) {
      return 4;
    } else if (str.charAt(0) == 'S') {
      return 5;
    } else{
      return -1;
    }
  }
  /**
    *
    * @param args
    *
    * @throws Exception
    */
  public static void main(String[] args) throws Exception {
    for (int i = 0; i < args.length; i++) {
      String str = args[i];
      BigFraction frac1;
      BigFraction frac2;
      BigFraction finalFrac;
      switch(operation(str)) {
        case 1:
          frac1 = newFrac(str, "begin");
          frac2 = newFrac(str, "end");
          finalFrac = frac1.add(frac2);
          System.out.println(frac1 + " + " + frac2 + " = " + finalFrac);
          previous = finalFrac;
          break;
        case 2:
        frac1 = newFrac(str, "begin");
        System.out.printf("IM HERE %s.\n", frac1.toString());
        frac2 = newFrac(str, "end");
        System.out.printf("IM HERE %s.\n", frac2.toString());
        finalFrac = frac1.subtract(frac2);
        System.out.println(frac1 + " - " + frac2 + " = " + finalFrac);
        previous = frac1.subtract(frac2);
          break;
        case 3:
        frac1 = newFrac(str, "begin");
        frac2 = newFrac(str, "end");
        finalFrac = frac1.multiply(frac2);
        System.out.println(frac1 + " * " + frac2 + " = " + finalFrac);
        previous = finalFrac;
          break;
        case 4:
        frac1 = newFrac(str, "begin");
        frac2 = newFrac(str, "end");
        finalFrac = frac1.divide(frac2);
        System.out.println(frac1 + " / " + frac2 + " = " + finalFrac);
          break;
        case 5:
        reg1.store(str.charAt(str.length() - 1), previous);
        System.out.printf("STORED %s\n", previous.toString());
          break;
        default:
          System.err.println("Invalid string");
      }
    }
  } // main
} // class QuickCalculator
