package euler;

public class Problem0006 {
/*
 * Here it's worth illustrating the square of the Nth sum visually, as being decomposed
 * into N*N cells, each of whose value is the product of its row number and its column
 * number.
 * 
 * The leading diagonal of cells contains the values 1, 4, 9 etc - adding these would
 * give the sum of the squares. Therefore every OTHER cell amongst the N*N cells has a
 * value that contributes to the desired result.
 * 
 * Observing that such cells comprise an upper and a lower triangle, we can see that
 * the upper triangle GROWS by the sequence: 2, 3+6, 4+8+12, etc - each such growth
 * being the Mth triangle number, multipled by M+1.
 * 
 * Differences are therefore the triangles, integration suggests blah, hence we arrive at
 * the polynomial expression N^4/4 + N^3/6 - N^2/4-N/6. Factorizes to
 * 
 * n(n+1)(n-1)(3n+2)/12
 */
	public static void main(String args[]) {
		long n = 100L;
		long answer = n*(n+1)*(n-1)*(3*n+2)/12;
		System.out.println("answer: " + answer);
		
	}
}
