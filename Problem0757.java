package euler;

import java.util.*;

public class Problem0757 {
	/*
	 * A positive integer N is "stealthy", if there exist positive integers a, b, c, d such that ab = cd = N
	 * and a + b = c + d + 1. For example, 36 = 4 * 9 = 6 * 6 is stealthy.
	 * 
	 * You are also given that there are 2851 stealthy numbers not exceeding 10^6.
	 * 
	 * How many stealthy numbers are there that don't exceed 10^14?
	 */
	
	public static void main(String[] args) throws Exception {
		enumerateFactors(69L);
		
	}
	
	public static String formatScientificNotation(long value, Locale localisation) {
	    return String.format(localisation, "%.3E", value);
	}
	
	
	
	static void enumerateFactors(long n) throws Exception {
		/*
		 * 
		 */
		PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(n);
		PrimeFactorDecomposition current = pfd.allPowersZero();
		
		System.out.println("Primes: " + current.getPrimes().toString());
		int i = 0;
		do {
			System.out.println("After " + i + " increments" + current.getPowers().toString());
			current = current.getIncrement(pfd);
			i++;
		} while (!current.isZeroArray());

	}

	
}
