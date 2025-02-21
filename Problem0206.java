package euler;

import java.util.*;

public class Problem0206 {
	/*
	 * 
	 * 	Find the unique positive integer whose square has the form 1_2_3_4_5_6_7_8_9_0,
		where each “_” is a single digit.
	 */

	public static void main(String args[]) {
		
		String targetString = "1_2_3_4";
		
		// initial bound based solely on length of string, and first digit:
		
		int stringLength = targetString.length();
		
		System.out.println("Your targetString has a length of " + stringLength + " digits, hence is at least 10^" + (stringLength - 1));
						
		long minRoot = (long) Math.sqrt(Math.pow(10, stringLength - 1));
		
		System.out.println("A lower bound for the root is therefore " + minRoot);
		
		String absoluteMaxString = targetString.replace('_', '9');
		long absoluteMax = Long.valueOf(absoluteMaxString);
		
		System.out.print("Meanwhile, replacing underscores with 9s gives ");
		System.out.println("a maximum SQUARE of " + absoluteMax);
		
		long maxRoot = (long) Math.sqrt(absoluteMax);
		
		System.out.println("An upper bound for the root is therefore " + maxRoot);
		
		for(long testValue = minRoot; testValue <= maxRoot; testValue++) {
			long result = testValue * testValue;
			
			String resultString = "" + result;
			String patternString = underscorize(resultString);
			
			if(patternString.equals(targetString)) {
				System.out.print("Found " + testValue + " (squares to ");
				System.out.print(result + ", which patternizes to ");
				System.out.println(underscorize("" + result) + ")");
				
			}
		}
		/*
		HashSet<Long> input = new HashSet<>();
		input.add(1L);
		HashSet<Long> output = valuesModTenPower(1, 1, input);
		
		System.out.println(output.toString());
		
		for(int pos1 = 0; pos1 < 10; pos1++) {
			
			HashSet<Long> output1 = valuesModTenPower(2, 10 * pos1 + 1, output);
			System.out.println("working on " + (10*pos1) + ": ");
			System.out.println(output1.toString());
			System.out.println("testiung " + pos1);
		}
		
		*/
		HashSet<Long> input = new HashSet<>();
		input.add(1L);
		
		HashSet<Long> outputAt5 = valuesModTenPower(1, 5, input);
		System.out.println("5 mod 10 reached by " + outputAt5.toString());
		
		HashSet<Long> hashSet5 = new HashSet<Long>();
		
		for (int digit5 = 0; digit5 <= 9; digit5++) {
			long target = 10 * digit5 + 5;
			HashSet<Long> outputPre5 = valuesModTenPower(2, target, input);
			hashSet5.addAll(outputPre5);
			System.out.println(target + " mod 100 reached by " + outputPre5.toString());
			
		}
		
		//HashSet<Long> outputAt4 = valuesModTenPower(3, )
		
		// FIXME FIRST we need to be capturing the 0-9 underscore choices that ACTUALLY
		// YIELD STUFF...
		
		
		
		
		
		
	}
	
	public static String underscorize(String input) {
		
		String output = "";
		
		for(int position = 0; position < input.length(); position++) {
			String nextDigit = input.substring(position, position + 1);
			output += position % 2 == 0 ? nextDigit : "_";
		}
		
		return output;
	}
	
	public static void printEveryExample(String input) {
		
		// great now let's recurse
		
		// input will be something like 1620344_5_6
		
		// it'll run itself 10 times, with the leftmost blank switched for
		// some digit
		
		// we're going to need to do better than this... billions of base-cases implied...
	}
	
	/*
	 * How about this. We can look at the final digit of the target, then
	 * place limits MOD 10 on what the answer can be.
	 * 
	 * Then look at the last TWO digits, and place limits MOD 100, having
	 * ALREADY filtered these based on the MOD 10 requirement. And so on!
	 */
	
	
	
	// FIXME bigIntegers of course!
	public static HashSet<Long> valuesModTenPower(int tensPower, long target, HashSet<Long> valuesModPrevious) {

		// easy way of avoiding repeated numbers, when they're multiples of more than one of the input
		// values - COULD be more efficient though FIXME
		
		
		HashSet<Long> output = new HashSet<>();
					
		long tenPower = (long) Math.pow(10, tensPower);
		
		if(target == 0) {
			// suppose that x is nonzero, but x^2 IS zero mod 10^n
			// well then x^2 must be 2^n * 5^n * K for some K
			// being square, that n must be even; call it 2m
			// so FIXME FINISH THIS
			
			output.add(0L);
		} else {
			
			for(long prevValue : valuesModPrevious) {
				long testValue = 0;
				long maxValue = tenPower - 1;
				while(testValue < maxValue) {
					
					if((testValue * testValue) % tenPower == target) {
						output.add(testValue);
						// System.out.println("detuct that " + testValue + " squares to " + target);
					}
					
					testValue += prevValue;
				}
			}
		}
		return output;
	}
}
