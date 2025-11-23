
/* CHECKLIST
- class name
- debug mode
- default value
- input safety
- neat comments



*/

/* HISTORY



*/

/* PROBLEM SOURCE AND STATEMENT
https://projecteuler.net/problem=13
Work out the first ten digits of the sum of the following one-hundred 50-digit numbers.

[digits have been pasted into E:\workspace_2024-09-20a\euler\src\euler\0013_largesum.txt]
*/

/* DISCUSSION
This is really just me familiarizing myself with java.math.BigInteger for the first time - I'm hoping it's trivial...



*/



import java.math.BigInteger;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem0013_LargeSum {

	public static void main(String args[]) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("E:\\workspace_2024-09-20a\\euler\\src\\euler\\0013_largesum.txt"));

		BigInteger total = new BigInteger("0");

		while (scanner.hasNext()) {
			total = total.add(new BigInteger(scanner.nextLine()));
		}

		System.out.println("actual total: " + total.toString());
		System.out.println("digits requested: " + total.toString().substring(0,  10));

		scanner.close();

	}

}


/* OUTPUT

cd E:\workspace_2024-09-20a\euler\bin
java euler.Problem0013_LargeSum

actual total: 5537376230390876637302048746832985971773659831892672
digits requested: 5537376230



*/

/* GREEN TICK CHECK

DONE
Tue, 24 Sep 2024, 21:27
You are the 240301st person to have solved this problem.

*/