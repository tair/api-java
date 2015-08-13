package org.phoenixbioinformatics.api;


import java.util.Random;


/**
 * A random-string generator
 * 
 * @author Robert J. Muller
 */
public class RandomString {
  /**
   * Generate a random string of a maximum length. For strings of length 1,
   * generate a random lowercase letter a-z.
   * 
   * @param maxlength the maximum length of the string to generate
   * 
   * @return the random string
   */
  public static String getString(int maxlength) {
    String s = null;
    Random r = new Random();
    if (maxlength == 1) {
      // Generate a random number between 97 and 122, inclusive.
      int n = 97 + r.nextInt(26);
      // Convert the number to a lowercase Unicode letter (x61-x7A).
      char c = (char)n;
      s = Character.toString(c);
    } else {
      s = Long.toString(Math.abs(r.nextLong()), 36);
      // Truncate the string to the maximum length if necessary.
      if (s.length() > maxlength) {
        s = s.substring(0, maxlength);
      }
    }
    return s;
  }
}
