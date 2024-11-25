package euler;

import java.util.ArrayList;

public class Problem0008 {
/*
 * The four adjacent digits in the 1000-digit number that have the greatest product are 9*9*8*9 = 5832.
 * 
 * Find the thirteen adjacent digits in the 1000-digit number that have the greatest product. What is the value of this product?
 */
	static long winner = 0;

	
	
	public static void main(String args[]) {
		
		final int DIGITCOUNT = 13;
		
		String str = "";
		
		str += "73167176531330624919225119674426574742355349194934";
		str += "96983520312774506326239578318016984801869478851843";
		str += "85861560789112949495459501737958331952853208805511";
		str += "12540698747158523863050715693290963295227443043557";
		str += "66896648950445244523161731856403098711121722383113";
		str += "62229893423380308135336276614282806444486645238749";
		str += "30358907296290491560440772390713810515859307960866";
		str += "70172427121883998797908792274921901699720888093776";
		str += "65727333001053367881220235421809751254540594752243";
		str += "52584907711670556013604839586446706324415722155397";
		str += "53697817977846174064955149290862569321978468622482";
		str += "83972241375657056057490261407972968652414535100474";
		str += "82166370484403199890008895243450658541227588666881";
		str += "16427171479924442928230863465674813919123162824586";
		str += "17866458359124566529476545682848912883142607690042";
		str += "24219022671055626321111109370544217506941658960408";
		str += "07198403850962455444362981230987879927244284909188";
		str += "84580156166097919133875499200524063689912560717606";
		str += "05886116467109405077541002256983155200055935729725";
		str += "71636269561882670428252483600823257530420752963450";
		
		/*
		 * The first key observation is that the presence of a single
		 * zero in a group of digits means its product is also zero -
		 * so we can restrict our attention to zero-free substrings.
		 * 
		 * So let's convert the one big string into an ArrayList of
		 * zero-free strings, by using zero as a delimiter...
		 */
		
		String array[] = str.split("0");
		// System.out.println(array[0]);
		
		/*
		 * Next, let's convert to ArrayList, and eliminate any array
		 * members with fewer than DIGITCOUNT digits.
		 */
		
		ArrayList<String> blocks = new ArrayList<String>();
		
		for(int i = 0; i < array.length; i++) {
			if(array[i].length() >= DIGITCOUNT) {
				blocks.add(array[i]);
			}
		}
		
		
		/*
		 * Now, for EACH member of this ArrayList, here's what we do:
		 * 
		 * - evaluate its first DIGITCOUNT digits and store the result
		 * - if applicable, divide that result by the FIRST digit and
		 *   multiply by the DIGITCOUNT+1'th digit, storing again
		 * - repeat until every DIGITCOUNT-length substring has been
		 *   processed
		 *   
		 * Note that we store for the purposes of checking the next
		 * substring, but we also store a single "best so far" value
		 * across the entire ArrayList...
		 */
		
		
		for(String block : blocks) {
			// so this is the product of the INITIAL digitcount worth of digits in the block...
			long current = product(block.substring(0, DIGITCOUNT));
			capture(current);
			// System.out.println("block: " + block + "; current: " + current);
			
			// now proceed to work through the "spare" digits the block has at the right...
			
			for(int i = DIGITCOUNT; i < block.length(); i++) {
				current /= digitAt(block, i - DIGITCOUNT);
				current *= digitAt(block, i);
				capture(current);
			}
		}
		
		System.out.println("Winner is: " + winner);
	}
	
	
	// given a string of digits, calculate the product of those digits
	private static long product(String str) {
		long result = 1;
		
		for(int i = 0; i < str.length(); i++) {
			result *= digitAt(str,i);			
		}
		
		return result;
	}
	
	private static int digitAt(String str, int position) {
		int digit = (int) str.charAt(position);
		return digit - 48; // ascii
	}
	
	private static void capture(long current) {
		winner = Math.max(winner,  current);
	}
}
