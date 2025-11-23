import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem0067_MaximumPathSumII {

	public static void main(String args[]) {

		int[][] array = new int[100][100];

		try {

			Scanner diskScanner = new Scanner(new File("E:\\workspace_2024-09-20a\\euler\\src\\euler\\0067_triangle.txt"));

			int i = 0;
			while (diskScanner.hasNext()) {

				String[] strLine = diskScanner.nextLine().split(" ");

				int j = 0;
				for(String str : strLine) {
					array[i][j] = Integer.parseInt(str);
					j++;
				}

				i++;
			}

			diskScanner.close();

		} catch (FileNotFoundException e) {
			System.out.println("bad file name");
			e.printStackTrace();
		}

		for(int i = array.length -1; i > 0; i--) { // > 0 since no comparison occurs with the apex element
			for(int j = 0; j < i; j++) { // the far-right element doesn't start a pair
				array[i-1][j] += array[i][j] > array[i][j + 1] ? array[i][j] : array[i][j + 1];
			}
		}

		System.out.println("winner: " + array[0][0]);

	}
}