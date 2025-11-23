public class Problem0015 {
/*
 * Starting in the top left corner of a 2x2 grid, and only being able to move to the right and down, there
 * are exactly 6 routes to the bottom right corner. How many such routes are there through a 20x20 grid?
 */

/*
 * Label any route by a sequence of Rs and Ds. For an NxN grid, there must be exactly N Rs and N Ds.
 *
 * Let's start by imagining every position being a D by default, and then having to choose N.
 *
 * This is just 2N choose N - (2N)! / (N!*N!)
 *
 * However, working out 40 * 39 * 38 * ... * 21 will overflow even a Long.
 *
 * Let's do the whole thing by manipulating prime factor decompositions, and only evaluate the product at the end
 */
	public static void main(String args[]) {



		final int N = 20;

		long product = 1;

		PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(product);

		// top factorial and one of the bottom factorials
		for(int i = 2*N; i > N; i--) {
			pfd = pfd.multiply(i);

		}

		// other bottom factorial
		for(int i = N; i > 0; i--) {
			pfd = pfd.divide(i);
		}

		System.out.println(pfd.evaluate());
	}
}
