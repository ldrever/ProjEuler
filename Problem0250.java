import java.util.ArrayList;

public class Problem0250 {

	public static void main(String args[]) {
//		System.out.println(naiveNumberPowerModulus(6,3,8));

		ArrayList<Integer> allResults = new ArrayList<Integer>();

		for (int i = 1; i <= 250; i++) {
			ModPowerPattern mpp = new ModPowerPattern(i,250);
			ArrayList<Integer> results = mpp.getPowerPattern(i, 250, 250250);

			allResults.addAll(results);
		}

		allResults.sort(null);

		System.out.println(allResults.toString());
		System.out.println(allResults.size());

//		for (int i = 0; i < 44; i++) {
//			ModPowerPattern mpp = new ModPowerPattern(i,72);
////			System.out.print(i + "::");
////			for (int j = 1; j < 11; j++) {
////				System.out.print(j + "th power: " + mpp.getSinglePower(j) + "; ");
////			}
//
//			System.out.println("");
//		}
	}

	static int naiveNumberPowerModulus(int number, int power, int modulus) {
		return (int) (Math.pow((double) number, (double)  power)) % modulus;
	}

	static int numberPowerModulus(int number, int power, int modulus) {
		return 0; // TODO LDFIXME
	}
}
