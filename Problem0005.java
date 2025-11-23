import java.util.HashMap;
import java.util.ArrayList;

public class Problem0005 {
/*
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *
 * What is the smallest positive number that is EVENLY DIVISIBLE by all of the numbers from 1 to 20?
 */

	/*
	 * I suppose the proper way to do this would be maintain an Nx2 vector of primes, alongside a
	 * power for each one.
	 *
	 * We would then go through the list, which happens to be 1-20, and for each item in the list,
	 * determine the vector for its prime decomposition.
	 *
	 * We would then ensure that the ANSWER-vector is not underpowered relative to that list-member's
	 * vector, on any of the primes.
	 */


	public static void main(String args[]) {

		HashMap<Long, Integer> collective = new PrimeFactorDecomposition(1).getDecomposition();

		//System.out.println(collective.getDecomposition().toString());

		ArrayList<PrimeFactorDecomposition> pfds = new ArrayList<PrimeFactorDecomposition>();

		for(long trial = 1; trial<=20; trial++) {
			PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(trial);
			System.out.println(pfd.getDecomposition().toString());
			pfds.add(pfd);




		}

		PrimeFactorDecomposition result = new PrimeFactorDecomposition(pfds);
		System.out.println("----------------------");
		System.out.println(result.getDecomposition().toString());
		System.out.println(result.evaluate());

	}

}
