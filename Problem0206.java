package euler;

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
		
		
	}
	
	public static String underscorize(String input) {
		
		String output = "";
		
		for(int position = 0; position < input.length(); position++) {
			String nextDigit = input.substring(position, position + 1);
			output += position % 2 == 0 ? nextDigit : "_";
		}
		
		return output;
	}
	
	
}
