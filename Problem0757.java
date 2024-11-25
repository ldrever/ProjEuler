package euler;

import java.util.ArrayList;

public class Problem0757 {
	public static void main(String[] args) throws Exception {
		isStealthy(69L);
	}
	
	
	static void isStealthy(long n) throws Exception {
		PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(n);
		ArrayList<Long> primes = pfd.getPrimes();
		ArrayList<Integer> powers = pfd.getPowers();
		
		
		System.out.println("Primes: " + primes.toString());
		System.out.println("Powers: " + powers.toString());
		
		int digitCount = primes.size();
		
		ArrayList<Integer> current = pfd.zeroArray(digitCount);
		
		int i = 0;
		do {
			System.out.println("After " + i + " increments" + current.toString());
			current = pfd.increment(current, powers);
			i++;
		} while (!pfd.isZeroArray(current));

	}

	
}
