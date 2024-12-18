package euler;

public class Problem0018_MaximumPathSumI {

	
	public static void main(String args[]) {		
		
		int[][] array = new int[][] {
			{75},
			{95,64},
			{17,47,82},
			{18,35,87,10},
			{20,04,82,47,65},
			{19,01,23,75,03,34},
			{88,02,77,73,07,63,67},
			{99,65,04,28,06,16,70,92},
			{41,41,26,56,83,40,80,70,33},
			{41,48,72,33,47,32,37,16,94,29},
			{53,71,44,65,25,43,91,52,97,51,14},
			{70,11,33,28,77,73,17,78,39,68,17,57},
			{91,71,52,38,17,14,91,43,58,50,27,29,48},
			{63,66,04,68,89,53,67,30,73,16,69,87,40,31},
			{04,62,98,27,23, 9,70,98,73,93,38,53,60,04,23}
		};
		
		
		/*

		int[][] array = new int[][] {
			{3},
			{7, 4,},
			{2, 4, 6},
			{8, 5, 9, 3}
		};
		
		*/
		
		//System.out.println(array[2].length);
		
		/*
		 * Here's what we do then. Work across the bottom row, and find the largest of every
		 * pair (note that the number of pairs per row is not HALF the element count - it is
		 * ONE LESS than it). Then, find the pair's "parent", and increase its value by that.
		 * Then work upwards and repeat.
		 */
		
		for(int i = array.length -1; i > 0; i--) { // > 0 since no comparison occurs with the apex element
			for(int j = 0; j < i; j++) { // the far-right element doesn't start a pair
				array[i-1][j] += array[i][j] > array[i][j + 1] ? array[i][j] : array[i][j + 1];
			}
		}
		
		System.out.println("winner: " + array[0][0]);
		
	}
}