import java.util.ArrayList;

public class PrimeSieve {

	public ArrayList<Long> sieve;

	public PrimeSieve(long maxPrimeSize) {
		long next = 2L;
		sieve = new ArrayList<Long>();

		while (next <= maxPrimeSize) {
			boolean newPrime = true;

			for (long trial : sieve) {
				if(trial*trial > next) {
					break;
				}

				long remainder = next % trial;

				if(remainder == 0) {
					newPrime = false;
					break;
				}

			}

			if(newPrime) {
				sieve.add(next);
			}

			next++;

		}
	}



	public PrimeSieve(int maxPrimeCount) {
		long next = 2L;
		sieve = new ArrayList<Long>();

		while (sieve.size() < maxPrimeCount) {
			boolean newPrime = true;

			for (long trial : sieve) {
				if(trial*trial > next) {
					break;
				}

				long remainder = next % trial;

				if(remainder == 0) {
					newPrime = false;
					break;
				}

			}

			if(newPrime) {
				sieve.add(next);
			}

			next++;

		}
	}




}
