package euler;

import static java.lang.System.out;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TractionTest {

	public static void main(String args[]) throws IOException {

		ArrayList<String> people = new ArrayList<>();
		Scanner diskScanner = new Scanner(new File("E:\\names.txt"));

		while (diskScanner.hasNext()) {
			people.add(diskScanner.nextLine());
		}

		people.remove(0);
		people.remove(0);
		people.remove(1);
		people.add(1, "rohembitz");

		for (String name : people) {
			out.println(name);
		}

		diskScanner.close();

	}

}