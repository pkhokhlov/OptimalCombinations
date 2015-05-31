/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package optimalcombinations;

/**
 *
 * @author jackprescott
 */

import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {

   public static BigInteger factorial(int n) {
       BigInteger fact = new BigInteger("1");
       for (int i = 1; i <= n; i++) {
           fact = fact.multiply(new BigInteger(i + ""));
       }
       return fact;
   }
}