public class Problem0003 {

	public static void main(String args[]) {

		long target = 600851475143L;

		PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(target);

		System.out.println(pfd.getHighestPrimeFactor());

	}

}
