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
		
		HashSet<Long> input = new HashSet<>();
		input.add(1L);
		HashSet<Long> output = valuesModTenPower(1, 6, input);
		
		System.out.println(output.toString());
		
		HashSet<Long> output2 = valuesModTenPower(2, 16, output);
		
		System.out.println(output2.toString());
		
		
		
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
	public static HashSet<Long> valuesModTenPower(int power, long target, HashSet<Long> valuesModPrevious) {

		// easy way of avoiding repeated numbers, when they're multiples of more than one of the input
		// values - COULD be more efficient though FIXME
		
		
		HashSet<Long> output = new HashSet<>();
					
		long tenPower = (long) Math.pow(10, power);
		
		for(long prevValue : valuesModPrevious) {
			long testValue = 0;
			long maxValue = tenPower - 1;
			while(testValue < maxValue) {
				
				if((testValue * testValue) % tenPower == target) {
					output.add(testValue);
					System.out.println("detuct that " + testValue + " squares to " + target);
				}
				
				testValue += prevValue;
			}
		}
		
		return output;
	}
}
