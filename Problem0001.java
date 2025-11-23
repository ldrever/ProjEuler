import java.lang.Math;

public class Problem0001 {

	public static void main(String args[]) {

		try {

			System.out.println ("Test answer: " + answer(10));
			System.out.println ("Real answer: " + answer(1000));
			System.out.println ("Testing limits 1: " + answer(92_000));
			System.out.println ("Testing limits 2: " + sumMults(9510, 5770162));

		} catch (Exception e) {
			System.out.println("Input values outside acceptable bounds.");
		} finally {
			System.out.println("Program terminated.");
		}

	}

	static int answer(int i) throws Exception {
		/* Get the sum of all multiples of 3 or 5,
		 * below a given number.
		 */
		return sumMults(3, i) + sumMults(5, i) - sumMults(15, i);
	}




	static int sumMults(int a, int b) throws Exception {
		/*
		 * Sum up all multiples of a that are strictly less than b.
		 *
		 * (This function may be easiest to understand if read from the bottom up.)
		 */

		/* PART 1 - SANITIZE INPUTS
		 *
		 */

		// this happens to be worth caching:
		final int maxInt = Integer.MAX_VALUE;

		// exclude non-positive values:
		if(a <= 0 || b <= 0) {
			System.out.println("negative or zero inputs are forbidden");
			throw new Exception();
		}

		// we need the upper limit maxA, so that the later calculation of maxB
		// can't hit the integer limit
		int maxA = maxInt / 2;

		if(a > maxA) {
			System.out.println("a-value is too big");
			throw new Exception();
		}

		// maxB calculations...
		int twiceARoot = (int) Math.sqrt(2 * a);
		int maxIntRoot = (int) Math.sqrt(maxInt);
		int maxB = twiceARoot * maxIntRoot - a;
		/* Insisting that b <= maxB as defined above...
		 * means b + a 				<= twiceARoot * maxIntRoot
		 * means b + a 				<= sqrt(2a * maxInt)
		 * means (b + a)^2 			<= 2a * maxInt
		 * means (b + a)^2 / 2a 	<= maxInt
		 * means b(b + a) / 2a 		< maxInt 		(being even smaller)
		 * means an(an + a) / 2a 	< maxInt 		(because an < b - see n's definition further down)
		 * means an(n + 1) / 2 		< maxInt 		(factoring out the a)
		 *
		 * ... hence there is no risk that the calculations below hit the integer limit.
		 */

		if(b > maxB) {
			System.out.println("b-value is too big for this choice of a-value");
			throw new Exception();
		}



		/* PART 2 - PERFORM CALCULATION
		 *
		 */

		// find the maximum n such that an < b
		int n = (b-1) / a; // we know that a > 0

		// find the n'th triangle number
		int triangle = n * (n + 1) / 2; // either n or n+1 must be even, so no risk of rounding down

		// the sum of the first n multiples of a, is just the n'th triangle number, multiplied by a
		return a * triangle;
	}
}
