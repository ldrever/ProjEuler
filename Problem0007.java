import java.util.ArrayList;

public class Problem0007 {


	public static void main(String args[]) {

		ArrayList<Long> sieve = new ArrayList<Long>();

		long next = 2L;

		while (sieve.size() < 10001) {

			boolean newPrime = true;

			for (long trial : sieve) {

				if(trial*trial > next) {
					break;
				}

				long remainder = next % trial;
				//System.out.println("Remainder of " + next + " versus " + trial + ": " + remainder);

				if(remainder == 0) {
					newPrime = false;
					break;
				}

			}

			if(newPrime) {
				sieve.add(next);
				//System.out.println(next + " has been identified as a prime and added to the sieve");

				}

			next++;

			}


			System.out.println(sieve.toString());
			System.out.println(sieve.get(sieve.size()-1));
		}


	}

