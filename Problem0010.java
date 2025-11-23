import java.util.ArrayList;

public class Problem0010 {

	public static void main(String args[]) {

		long maxPrime = 1_999_999L;

		ArrayList<Long> sieve = new PrimeSieve(maxPrime).sieve;

		long sum = 0L;

		for(long prime : sieve) {
			sum += prime;
		}

		System.out.println(sum);
	}
}
