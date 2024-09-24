package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Repeatedly reads a line a user types to compute the result to print for the user.
 * 
 * Author: Sam Schmidt
 */
public class InteractiveCalculator {
/**
  *
  * @param args
  * @throws Exception
  */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner eyes = new Scanner(System.in);
    pen.print("Enter your computation: ");
    pen.flush();
    String stuff = eyes.nextLine();
    if (stuff.equals("QUIT")){
      
    } else{
      BigFraction frac1 = QuickCalculator.newFrac(stuff, "begin");
      BigFraction frac2 = QuickCalculator.newFrac(stuff, "end");
      System.out.println(frac1.add(frac2));
    }
    
    

  } // main
} // class Interactive Calculator