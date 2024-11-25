package euler;

import java.math.BigInteger;

public class Problem0020_FactorialDigitSum {
	
	public static void main(String args[]) {
		/*
		 * Find the sum of the digits in the number 100!
		 */
		
		/*
		 * We're looking at a number not far off 100^100, so even long
		 * integers won't let us attack the problem directly. Let's make
		 * the key observation that zeroes don't contribute to the digit
		 * sum.
		 * 
		 * So this product will have an identical digit sum:
		 * 
		 * 99 * 98 * ... * 91 * 9 *
		 * 89 * 88 * ... * 81 * 8 *
		 * ... *
		 * 19 * 18 * ... * 11 *
		 *  9 *  8 * ... *  1
		 *  
		 * We can go further still though - every factor of 10 can be
		 * removed, not just those that start off together. In other
		 * words, make pairs of divisions - by 5 and by 2:
		 * 
		 * 99 * 98 * 97 * 96
		 * * 19...
		 * 
		 * In fact, perhaps we could see how far we get using prime factor
		 * decomposition...
		 * 
		 */
		
		PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(2);
		
		for(int i = 3; i < 100; i++) {
			pfd = pfd.multiply(i);
		}
		
		// System.out.println(pfd.getDecomposition().toString());
		
		/* Observing the 5^22 therein... */
		
		for(int i = 0; i < 22; i++) {
			pfd = pfd.divide(10L);
		}
		
		System.out.println(pfd.getDecomposition().toString());
		
		/*
		 * Still pretty big, but hopefully there are clever things that can be done with digital sums.
		 * 
		 * Is it true, for instance, that every factor of 9 increases the digital sum by 8? No - 36 * 9 = 324, leaving it unaffected...
		 * 
		 * 
		 */
		
		
	}

}
