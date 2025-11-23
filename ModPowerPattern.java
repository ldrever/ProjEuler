import java.util.ArrayList;

public class ModPowerPattern {
/*
 * Observe that:
 *
 * A
 * ---
 * 6^1 = 6; 6 mod 8 = 6
 * 6^2 = 36; 36 mod 8 = 4
 * 6^3 = 216; 216 mod 8 = 0
 * having hit an exact multiple of 8, every subsequent power of 6 must ALSO equal 0 mod 8
 *
 * B
 * --
 * 6^1 mod 56 = 6
 * 6^2 mod 56 = 36
 * 6^3 mod 56 = 48
 * 6^4 mod 56 = 8
 * 6^5 mod 56 = 48
 * and having hit 48 again after 2 iterations, we now just alternate 8 and 48 for ever
 *
 * C
 * --
 * 5^n mod 7 loops through the sequence 5,4,6,2,3,1 forever
 *
 * Every mod power pattern eventually must repeat - perhaps with some initial values that
 * aren't part of the repeating set. Let's keep track of these with an array for the initial
 * values, and a second array for the repeating ones.
 */
	private ArrayList<Integer> nonRepeatingPowers;
	private ArrayList<Integer> repeatingPowers;

	public ArrayList<Integer> getPowerPattern(int firstPower, int step, int maxPower) {
		// consider 14 mod 72; 2 nonrepeaters: [14, 52]; 6 repeaters: [8, 40, 56, 64, 32, 16]
		// now suppose we want to extract the 2nd, 12th, 22nd, 32nd etc etc terms from the sequence

		// the approach will be to be "pull-based" on all non-repeaters and repeaters, and return
		// an arraylist that is NOT necessarily in the order that the sequence encounters them

		ArrayList<Integer> answer = new ArrayList<Integer>();

		while (firstPower <= this.nonRepeatingPowers.size()) {
			answer.add(nonRepeatingPowers.get(firstPower - 1));
			firstPower += step;
		}

		// now safe to assume we're in the repeating-powers area...

		// FIXME probably wanna check it vs a brute-force approach...

		for (int currentPower = firstPower; currentPower <= maxPower; currentPower += step) {
			answer.add(getSinglePower(currentPower));
		}

		return answer;
	}

	public int getSinglePower(int power) {
		int index = power - 1;
		// LDFIXME TODO rab zero/negative powers...
		if (index < this.nonRepeatingPowers.size()) {
			return this.nonRepeatingPowers.get(index);

		} else {
			index -= this.nonRepeatingPowers.size();
			index %= this.repeatingPowers.size();
			return this.repeatingPowers.get(index);
		}


	}

	public ModPowerPattern(int number, int modulus) {
		boolean isRepeat = false;

		// if A = B mod C, then A^n = B^n mod C, for any n, and so we can freely do:
		number %= modulus;

		// however we still need to ensure this is non-negative:
		number += modulus;
		number %= modulus;

		ArrayList<Integer> powers = new ArrayList<Integer>();

		boolean isFreshPower = true;
		int runningValue = number;
		int knownNonRepeaters = 0;

		// start each loop iteration with the number already multiplied up and checked for freshness:
		while(isFreshPower) {
			powers.add(runningValue);

			runningValue *= number;
			runningValue %= modulus;

			knownNonRepeaters = 0;
			for (int alreadyfound : powers) {

				if (runningValue == alreadyfound) {
					isFreshPower = false;
					break;
				} else {
					knownNonRepeaters++;
				}
			}
		}

//		System.out.println(powers.toString());
//		System.out.println("Where the " + (knownNonRepeaters + 1) + "th value (" + (powers.get(knownNonRepeaters)) + ") kicks off a repeating pattern with period " + (powers.size() - knownNonRepeaters));

		nonRepeatingPowers = new ArrayList<Integer>();
		repeatingPowers = new ArrayList<Integer>();

		int quantityAssigned = 0;
		for (int toAssign : powers) {
			if (quantityAssigned < knownNonRepeaters) {
				nonRepeatingPowers.add(toAssign);
//				System.out.println(toAssign + " was added to the non-repeaters");
			} else {
				repeatingPowers.add(toAssign);
//				System.out.println(toAssign + " was added to the repeaters");
			}
			quantityAssigned++;
		}

		System.out.print("considering " + number + " mod " + modulus);
		System.out.print("; " + nonRepeatingPowers.size() + " nonrepeaters: " + nonRepeatingPowers.toString());
		System.out.println("; " + repeatingPowers.size() + " repeaters: " + repeatingPowers.toString());
	}

}
