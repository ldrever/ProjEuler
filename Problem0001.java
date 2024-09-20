package euler;

public class Problem0001 {

	public static void main(String args[]) {
		System.out.println ("Test answer: " + answer(10));
		System.out.println ("Real answer: " + answer(1000));
		
	}
	
	/* Get the sum of all multiples of 3 or 5,
	 * below a given number.
	 */
	static int answer(int i) {
		return sumMults(i, 3) + sumMults(i, 5) - sumMults(i,15);
	}
	
	/* Get the sum of all multiples of x
	 * that are below m.
	 */
	static int sumMults(int m, int x) {
		
		/* Define the n'th multiple to be the largest multiple of x that is smaller than m. */
		int n = (m-1) / x;
		
		/* Standard way of finding the n'th triangle number. */
		int triangle = n * (n+1) / 2;
		
		
		return x * triangle;
	}
}
