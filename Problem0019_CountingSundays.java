public class Problem0019_CountingSundays {
	/*
	 * How many Sundays fell on the first of the month during the twentieth century?
	 *
	 * First, worth nothing that the 100 and 400 year rules cancel out, we we can
	 * just use naive 4-year based leap year calculations.
	 *
	 * There will be 25 4-year blocks to consider... each of these will start on a
	 * different day though, perhaps...
	 *
	 * How about we start by considering all hundred Januaries... 1/1/1900 being Monday
	 * means that 1/1/1901 was a Tuesday - as 1900 itself is NOT leap.
	 *
	 * How about we start to classify as follows
	 *
	 * Let's temporarily ignore January and February, and observe that the first of
	 * any given month has the following behaviour:
	 *
	 * 1. it increases by 1 (mod 7) per year
	 * 2. it increases by 1 more (mod 7) per multiple of 4 years
	 *
	 */

	public static void main(String[] args) {
		/*
		 * Let's start with an observation about which months have matching start days:
		 */

		String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int[] nonLeaps = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		int[] leaps = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

		/*
		for(int i = 0; i < 12; i++) {
			System.out.println(nonLeaps[i] + ": " + monthNames[i]);
		}
		*/

		/*
		 * So far so good. Now let's track the running total mod 7:
		 */
		int running = 4; // i just prefer it this way - doesn't matter since it's mod 7, and the assignment of days to numbers is choosable

		/*
		for(int i = 0; i < 12; i++) {
			System.out.println(running + ": " + monthNames[i]);
			running += nonLeaps[i];
			running %= 7;
		}

		*/

		/*
		 * Extend this to a 4-year period...
		 */

		int[][] quadGrid = new int[4][12];

		int sundayCount = 144;

		for(int year = 1; year <= 4; year++) {
			for(int month = 0; month < 12; month++) {

				quadGrid[year-1][month] = running;
				if(running == 2 || running == 4 || running == 6 || running == 1) {
					sundayCount++;
				}
				running += year % 4 == 0 ? leaps[month] : nonLeaps[month];
				running %= 7;



			}
		}

		for(int i = 0; i < 12; i++) {
			System.out.println(quadGrid[0][i] + ", " + quadGrid[1][i] + ", " + quadGrid[2][i] + ", " + quadGrid[3][i] + ": " + monthNames[i]);

		}

		/*
		 * One interesting thing is that because 7 is prime, and this block does not repeat, we can be sure that 7 blocks (ie 28 years)
		 * will go through all possible combinations. So in any 28-year period, we can be sure that one seventh of month-starts will be
		 * Sundays.
		 *
		 * Not only that, it's true over any 3 rounds of 28 years.
		 */

		System.out.println(84*12/7);

		/*
		 * We only need to add to that the Sunday-starts from 1901 - 1916...
		 */

		/*
		 * Question is - Sundays in the NEXT block will have the same count as WHAT day in THIS block?
		 *
		 * Well we can see from the block that the final December begins on a 6. So a second block would
		 * have its January begin on a 2 (add the 31 December days to that 6, mod 7). I.e., it starts 2
		 * instead of 4.
		 *
		 * Well look at it this way. If we say that the EXACT same grid repeated in the next 4-year block
		 * ... that could only be true if the number-meanings were changed, such that 4 DOESN'T mean thursday
		 * but now means Tuesday... or that Tuesday USED to mean 2 and now means 4... equivalently Sunday USED
		 * to mean 0 and now means 2...
		 *
		 * So: the Sunday-count across 4 adjacent blocks is... if we use the block below for 1901-4, meaning
		 * 2 is Sunday here... well then we want to count 2s + 4s + 6s + 1s.
		 *
		 * Ah no wait - that goes wrong because the leap needs to be LAST...
		 */



		System.out.println(sundayCount);



	}

}
