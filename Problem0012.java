package euler;

public class Problem0012 {
/*
 * How about if we could obtain a divisor count just using a number's prime factor decomposition?
 * Yes.
 */
	public static void main(String args[]) {
		long n = 1;
		long triangle = 0;
		
		int divisorCount = 0;
		
		while (divisorCount < 500) {
			
			triangle+=n;
			
			PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(triangle);
			System.out.print(triangle);
			//System.out.print(" - " + pfd.getDecomposition().toString());
			
			divisorCount = pfd.countDivisors();
			System.out.println(" - " + divisorCount);
			
			n++;
		}
		
		
	}
}
