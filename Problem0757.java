package euler;

import java.util.*;

public class Problem0757 {
	/*
	 * A positive integer N is "stealthy", if there exist positive integers a, b, c, d
	 * such that ab = cd = N and a + b = c + d + 1. For example, 36 = 4 * 9 = 6 * 6 is 
	 * stealthy.
	 * 
	 * You are also given that there are 2851 stealthy numbers not exceeding 10^6.
	 * 
	 * How many stealthy numbers are there that don't exceed 10^14?
	 */
	
	
	
	/*
	 * Theorem: A positive integer is stealthy iff it is 4 times the product of
	 * two triangular numbers.
	 * 
	 * Proof ("if" part):
	 * Let's write our two triangular numbers down as
	 * a(a+1)/2 and b(b+1)/2. Four times their product is therefore
	 * ab(a+1)(b+1), which we can factorize in the following 2 different ways:
	 * 
	 * 1/ (a(b+1)) * (b(a+1))
	 * 2/ (ab) * ((a+1)(b+1))
	 * 
	 * The factor sums respectively are therefore:
	 * 1/ (ab+a) + (ab+b) = 2ab + a + b
	 * 2/ (ab) + (ab + a + b + 1) = 2ab + a + b + 1
	 * 
	 * Their difference being 1 means that such a product is stealthy, by
	 * definition.
	 * 
	 * ("only if" part):
	 * Observe that the largest out of a and b MUST exceed the smallest out
	 * of c and d. (Otherwise, we would have a + b <= c + d, contradicting
	 * our definition.)
	 * 
	 * So WLOG, let's write a = c + c', for some positive c'. This means
	 * 
	 * N/a < N/c, i.e. b < d. So let's say similarly that d = b + b'.
	 * 
	 * Therefore:
	 *    N = ab 		= cd 
	 * 		= b(c+c') 	= c(b+b')
	 * 		= bc + bc'	= bc + cb'
	 * 
	 * Hence bc' = cb'				(%)
	 * Hence c = b(c'/b')    		(*)
	 * 
	 * HENCE c'/c = b'/b			(F)
	 * 
	 * (b'+ 1) / c = b' / b
	 * 
	 * So b(b'+1) = b'c
	 * So b(b'+1) = b'b(c'/b')
	 * 
	 * 
	 * Returning to the additive aspect of the definition of stealth, we have
	 * 					a + b = c + d + 1
	 * Hence	   c + c' + b = c + b + b' + 1
	 * Hence		 	   c' = b' + 1
	 * Hence 				a = c + b' + 1
	 * Hence via (*)		a = b(c'/b') + b' + 1 = b((b'+1)/b') + b' + 1
	 * 
	 * ...
	 * 
	 * From (*), let's define m := b', so (m+1) = c.
	 * 
	 * From (%), divide through by c'c to obtain:
	 * 
	 * b/c = b'/c'
	 * 
	 * 
	 * 
	 * 
	 * If we say that 	c = a - c'
	 * 					c = (a-1) - b'
	 * 
	 * 
	 * Suppose we have b = Kc, for some rational K. Then
	 * 
	 * N = ab = aKc = cd = ac(b'/c')
	 * 
	 * Given any stealthy number N = ab = cd, with a + b = c + d + 1,
	 * let us demand WLOG that a <= b and c <= d. Let's now define
	 * a "characteristic" m(N) as HCF(a, d).
	 * 
	 * 
	 * 
	 */
	
	public static void Newmain(String[] args) {
		ArrayList<Long> smallProducts = new ArrayList<Long>();
		ArrayList<Long> largeProducts = new ArrayList<Long>();
		// HERE FIXME
		
		for(long smallNeighbour = 4L; smallNeighbour < 21L; smallNeighbour++) {
			long largeNeighbour = smallNeighbour + 1L;
			
			for(long smallFragment = 1L; smallFragment <= smallNeighbour / 2L; smallFragment++) {
				
				long largeFragment = smallNeighbour - smallFragment;
				long product = smallFragment * largeFragment;
				
				System.out.println("Breaking " + smallNeighbour + " into " + smallFragment + " + " + largeFragment);
				System.out.println("Hence seeking a breakdown of " + largeNeighbour + " with product " + product);
				
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		boolean debug = false;
		long stealthCounter = 0;
		
		try {
			for(long i = 1L; i <= 10_000L; i++) {
				if(isStealthy(debug, i)) {
					stealthCounter++;
//					System.out.println(stealthCounter + "th:" + i);
					PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(i);
					System.out.println("Prime factors: " + pfd.getPrimes().toString());
					System.out.println("Prime powers: " + pfd.getPowers().toString());
					System.out.println();
					
				}
			}
			
		} catch (Exception e) {
			
		}
	}
	
	public static boolean isStealthy(boolean debug, long N) throws Exception {
				
		if(debug) System.out.println(String.format("%,d", N));
		PrimeFactorDecomposition pfd = new PrimeFactorDecomposition(N);
		if(debug) System.out.println("Prime factors: " + pfd.getPrimes().toString());
		if(debug) System.out.println("Prime powers: " + pfd.getPowers().toString());
		
		ArrayList<Long> factors = pfd.getOrderedFactorList();
		int size = factors.size();
		if(debug) System.out.println(N + " has " + size + " factors.");
		
		// integer division here yields the smallest factor that's not less than the 
		// square root
		int majorFactorIndex = factors.size() / 2;
		
		int rootable = 4;
		int rootality = 0; // FIXME speculation - but it represents, in the logical ordering of factor pairs, how far away this pair is from the pair that's closest to the square root
		
		long oldFactorSum = 0;
		long oldMajorFactor = 0;
		long oldMinorFactor = 0;
		
		while (majorFactorIndex < size && rootality < rootable) {
			int minorFactorIndex = size - 1 - majorFactorIndex;
			long majorFactor = factors.get(majorFactorIndex);
			long minorFactor = factors.get(minorFactorIndex);
			long factorSum = majorFactor + minorFactor;
			long factorDifference = factorSum - oldFactorSum;
			if(debug) System.out.print(N + " = " + minorFactor + " * " + majorFactor);
			if(debug) System.out.println(" sum " + factorSum + (oldFactorSum == 0 ? "" : " (difference " + factorDifference + ")"));
			if(factorDifference == 1) {
				System.out.print(N + " = " + oldMinorFactor + " * " + oldMajorFactor);
				System.out.println(" sum " + oldFactorSum);
				
				System.out.print(N + " = " + minorFactor + " * " + majorFactor);
				System.out.println(" sum " + factorSum);
				
				System.out.println("Deltas therefore being " + (oldMinorFactor - minorFactor) + " and " + (majorFactor - oldMajorFactor));
				
				System.out.println("Rootalities " + (rootality - 1) + " and " + rootality);
				
				
				
				
				return true;
			}
			
			
			oldFactorSum = factorSum;
			oldMajorFactor = majorFactor;
			oldMinorFactor = minorFactor;
			
			majorFactorIndex++;
			rootality++;
		}
		return false;		
	}
		
}
