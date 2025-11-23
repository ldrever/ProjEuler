import java.util.ArrayList;

public class Problem0451 {

	public static void main(String args[]) {

		System.out.println(cleverSum(100));

		PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(21);

		//pfd.getDecomposition()
		System.out.println(pfd.getDecomposition().toString());
		System.out.println(pfd.getAllFactors().toString());

//		pfd.getAllFactors();

	}

	public static long cleverSum(long max) {
		long sum = max - 2;
		ArrayList<Long> memo = new ArrayList<Long>();
		for(long root = 2; root < max / 2; root++) {
			long subsquare = root*root - 1;

			PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(root - 1);
			pfd = pfd.multiply(root + 1);

			// LDFIXME LDHERE - now to get every divisor, starting with highest... also SO many lessa re that pfd class...!

			for(long h = max; h >= root + 2; h--) {
				// System.out.println("Checking whether " + h + " divides " + subsquare + "...");
				if(subsquare % h == 0) {
					if(! memo.contains(h)) {

						sum += h - root - 1;
						memo.add(h);
					}
				}
			}
		}
		return sum;
	}
}
