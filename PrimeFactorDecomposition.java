package euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Math;

import java.lang.Cloneable; 

public class PrimeFactorDecomposition implements Cloneable {

	
	private HashMap<Long, Integer> primePowers;
	private long highestPrimeFactor = 1; // FIXME not sync'ed
	
	
	// essential to keep in sync
	// note: these two are not sorted!
	
	private ArrayList<Long> primes;
	private ArrayList<Integer> powers;
	
    // Overriding clone() method 
    @SuppressWarnings("unchecked")
	// by simply calling Object class 
    // clone() method. 
    @Override
    protected Object clone() 
         
    { 
    	PrimeFactorDecomposition newcomer;
    	try {
    		newcomer = (PrimeFactorDecomposition) super.clone();
    		newcomer.primePowers = (HashMap<Long, Integer>) this.primePowers.clone();
    		
    		newcomer.primes = (ArrayList<Long>) this.primes.clone();
//    		System.out.println("new primes" + newcomer.primes.toString());
    		
    		newcomer.powers = (ArrayList<Integer>) this.powers.clone();
//    		System.out.println("new powers" + newcomer.powers.toString());
    		
    	} catch (CloneNotSupportedException e) {
//    		System.out.println("except!");
    		return null;
    	}
        return newcomer; 
    }
    
    private void setPower(Long prime, int power) {
    	int position = this.primes.indexOf(prime);
    	this.powers.set(position,  power);
    	this.primePowers.put(prime, power);   	 
//    	System.out.println("prime " + prime + " now set to " + power + " (position " + position + ")");
    }
	
	private void removeZeroPowers() {
		
		for (int i = this.primes.size() - 1; i >= 0; i--) {
			
			if (this.powers.get(i) == 0) {
				this.primes.remove(i);
				this.powers.remove(i);
				this.primePowers.remove(this.primes.get(i));
			}
		}
	}
	

	
	public static ArrayList<Integer> increment(ArrayList<Integer> input, ArrayList<Integer> maxes) throws Exception {
		// incrementing an ArrayList with respect to a second ArrayList (of maxes) means:
		// - increase its first member by 1, but only if the result would be no larger than the corresponding member of maxes
		// - if that's NOT the case, then try the second member, and so on
		// - if the input equals or exceeds the max, then return an ArrayList of zeroes
		
		// note that a separate object is returned, and the original left as it was
		
		int digitToIncrement = 0;
		int digitCount = input.size();
		
		if (maxes.size() != digitCount) {
			throw new Exception(); 
		}
		
		// clone
		ArrayList<Integer> output = new ArrayList<Integer>();
		for(int i = 0; i<digitCount; i++) {
			output.add(input.get(i));
		}		
		
		while(digitToIncrement < digitCount) {
			int currentValue = input.get(digitToIncrement); 
			int maxValue = maxes.get(digitToIncrement);
			
			if(currentValue < maxValue) {
				output.set(digitToIncrement,  1 + currentValue);
				break;
				
			} else {
//				System.out.println("carry has been triggered because digit " + digitToIncrement + " is at value " + currentValue + " of " + maxValue);
				output.set(digitToIncrement, 0);
				digitToIncrement++;
			}
		}
		return output;
	}
	
	public PrimeFactorDecomposition getIncrement(PrimeFactorDecomposition maxes) throws Exception {
		
		/*
		 * remember we're doing ONE increment - so let's systematically search through
		 * the constituent prime powers until we find one that's not maxed out
		 */
		int digitToIncrement = 0;
		int digitCount = this.primes.size();
		
		if (maxes.primes.size() != digitCount) {
//			System.out.println("testing a: mismatch"); // FIXME ideally we'd test every single prime matches
			throw new Exception(); 
		}
		PrimeFactorDecomposition output = (PrimeFactorDecomposition) this.clone();
//		System.out.println("testing b: " + output.powers.toString());
		while(digitToIncrement < digitCount) {
			long prime = this.primes.get(digitToIncrement);
			int currentValue = this.powers.get(digitToIncrement);
			int maxValue = maxes.powers.get(digitToIncrement);
			
//			System.out.println("prime " + prime + " is at power " + currentValue + " out of " + maxValue);
			
			if(currentValue < maxValue) { // straightforward case where the digit under consideration isn't maxed out
//				System.out.println("here");
				output.setPower(prime, 1 + currentValue);
				break;
				
			} else { // that digit's maxed out, so ZERO it, then try and increment the next one instead
				output.setPower(prime, 0);
				digitToIncrement++;
			}
		}
		
		return output;
	}
	
	
	
	static boolean isZeroArray(ArrayList<Integer> input) {
		for(int i = 0; i < input.size(); i++) {
			if (input.get(i) != 0) {
				return false;
			}
		}
		return true;
	}
	
	
	boolean isZeroArray() {
		for(int i = 0; i < this.primes.size(); i++) {
			if(this.powers.get(i) != 0) return false;
		}
		return true;
	}
	
	static ArrayList<Integer> zeroArray (int digitCount) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		
		for(int i = 0; i < digitCount; i++) {
			output.add(0);
		}
		
		return output;
	}
	
	
	
	public PrimeFactorDecomposition allPowersZero() throws Exception {
		PrimeFactorDecomposition output = (PrimeFactorDecomposition) this.clone();
		for(long prime : this.primes) {
			output.setPower(prime, 0);
		}
		return output;
	}
	
	public long getHighestPrimeFactor() {
//		ArrayList<Long> sorted;
//		sorted = (ArrayList<Long>) this.primes.clone();
//		sorted.sort(>);
//		
//		
//				
//		return primes.t
		
		return this.highestPrimeFactor;
		
		
		
		
	}
	
	public HashMap<Long, Integer> getDecomposition() {
		return this.primePowers;
	}
	
	
	
	// instantiating from a long integer means tackling factorization head-on - the approach is to build up
	// a "sieve" of found primes; AS we do so, we divide our integer down by any primes we find that divide
	// it
	public PrimeFactorDecomposition(long targetProduct) {
		
		this.primePowers = new HashMap<Long, Integer>();
		ArrayList<Long> sieve = new ArrayList<Long>();				
		long candidateDivisor = 2L;
		
		while (targetProduct > 1) {
			
			// this for-loop is to inspect candidateDivisor, and "catch it in the act" of being composite
			// - hence primality is our starting assumption
			boolean primeCandidate = true;			
			for (long sieveMember : sieve) {
				
				// if our candidate has non-trivial prime divisors, then one of these is at most its square root
				// hence if this point is reached, then we can be confident that the assumption of primality holds
				if(sieveMember*sieveMember > candidateDivisor) {
					break;
				}				
				
				// as soon as a sieve member divides our candidate, we know it's composite, so break out				
				if(candidateDivisor % sieveMember == 0) {
					primeCandidate = false;
					break;
				}								
			}
			
			if(primeCandidate) {
				sieve.add(candidateDivisor);
								
				if(targetProduct % candidateDivisor == 0) {
					
					this.highestPrimeFactor = candidateDivisor;					
					Integer previousPower = this.primePowers.get(candidateDivisor);
					int newPower;
					
					if(previousPower == null) {
						newPower = 1;
					} else {
						newPower = 1 + previousPower;
					}					
					
					this.primePowers.put(candidateDivisor, newPower);
					targetProduct /= candidateDivisor;
					candidateDivisor--; // counteracts the default ++ effect; result is to extricate multiple factor instances
				}				
			}
			
			candidateDivisor++;
			
		}
		
		this.populatePowers();
	}

	
	
	
	public long evaluate() {
		
		long result = 1L;
		
		for (long prime : primePowers.keySet()) {			
			result *= Math.pow(prime, primePowers.get(prime));			
		}
		
		return result;
	}
	
	public int countDivisors() {
		/*
		 * We don't actually need to DETERMINE any divisors, in order to COUNT them.
		 * There is a 1:1 mapping between divisors, and possible combinations
		 * of prime factor powers.
		 * 
		 * For instance, 3^1 * 5^2 = 75 has the possible combinations:
		 * 	3^0 * 5^0 = 1
		 *  3^0 * 5^1 = 5
		 *  3^0 * 5^2 = 25
		 *  3^1 * 5^0 = 3
		 *  3^1 * 5^1 = 15
		 *  3^1 * 5^2 = 75
		 *  
		 *  We can just obtain this count of 6 by adding one to each power, and multiplying (i.e. 2 * 3).
		 *  Likewise a prime number p^1 has two divisors - itself and one. And a square of a prime has 3.
		 */
		int count = 1;
		
		for(long prime : primePowers.keySet()) {
			count *= (primePowers.get(prime) + 1);
		}
		
		return count;
	}
	
	public ArrayList<Integer> getPowers() {
		return this.powers;
	}
	
	public ArrayList<Long> getPrimes() {
		return this.primes;
	}
	
//	public void getAllFactors() {
	public ArrayList<Long> getAllFactors() {
		
		// classic recursion!
		// consider the pfd formed by chopping the first prime off the current one
		// obtain all ITS factors, but multiply each by every possible power (inc 0)
		// OF that first factor
		
		long headPrime = this.primes.get(0);
		int headPower = this.powers.get(0);
		
		System.out.println("Base number: " + this.getDecomposition().toString());
		
		long divisor = 1;
		for(int i = 0; i < headPower; i++) {
			divisor *= headPrime;
		}
		System.out.println("divisor identified as " + divisor);
		
		PrimeFactorDecomposition tail = this.divide(divisor);	
		System.out.println("tail: " + tail.getDecomposition().toString());
		
		ArrayList<Long> recursedFactors  = tail.getAllFactors();
		ArrayList<Long> output = new ArrayList<Long>();
	
		for (Long recursedFactor : recursedFactors) {
			for(int i = 0; i <= this.powers.get(0); i++) {
				output.add(recursedFactor * (long) Math.pow(headPrime,i));
				
			}
			 
		} 
		return output;
		
	}
	
	
	// TODO - divide has the flaw of allowing items with a zero-power to be left included!
	
//	
//	// LDFIXME FIXME TODO how to jump to a cursor position?
//	
//	public ArrayList<Long> orderedDivisorList() {
//		
//		ArrayList<Long> unsortedFactors = new ArrayList<Long>();
//		ArrayList<Long> primes = new ArrayList<Long>();
//		ArrayList<Integer> maxPowers = new ArrayList<Integer>();
//		ArrayList<Integer> currentPowers = new ArrayList<Integer>();
//		
//		int size = 0;
//		
//		for (long prime : this.primePowers.keySet()) {
//			size++;
//			primes.add(prime);
//			maxPowers.add(this.primePowers.get(prime));
//			currentPowers.add(0);
//		}
//		
//		
//		
//		/*
//		 * Our method of systematically enumerating every factor will be to give every
//		 * PRIME factor an index from 0 to maxIndex, and then imagine something we'll
//		 * call the POSITION... it'll be like a car odometer...
//		 */
//		
//		
//		int position = 0;
//		
//		int maxIndex = size - 1;
//		
//		while (position < maxIndex || currentPowers.get(maxIndex) < maxPowers.get(maxIndex)) {
//			
//			// TODO calculate and add to unsortedFactors
//			
//			
//			
//			if(currentPowers.get(position) < maxPowers.get(position)) {
//				
//				currentPowers.set(position, currentPowers.get(position) + 1);
//			} else {
//				position++;
//				for (int powerToWipe = 0; powerToWipe < position; powerToWipe++) {
//					currentPowers.set(powerToWipe, 0);
//				}
//				position = 0;
//			}
//			
//		} 
//		
//	}
//	
//	
	
	public PrimeFactorDecomposition multiply(long multiplier) {
		PrimeFactorDecomposition secondary = new PrimeFactorDecomposition(multiplier);
		ArrayList<PrimeFactorDecomposition> al = new ArrayList<PrimeFactorDecomposition>();
		al.add(this);
		PrimeFactorDecomposition resultant = new PrimeFactorDecomposition(al);
		
		for(long prime : secondary.getDecomposition().keySet()) {
			int power = secondary.getDecomposition().get(prime);
			
			Integer prevPower = resultant.primePowers.get(prime);
			if(prevPower == null) {
				prevPower = 0;
			}

			resultant.primePowers.put(prime, power + prevPower);
		}
		
		return resultant;
	}
	
	// TODO needs to be done in united manner with one above
	// TODO and react well to a divisor that doesn't actually divide into that number
	public PrimeFactorDecomposition divide(long divisor) {
		PrimeFactorDecomposition secondary = new PrimeFactorDecomposition(divisor);
		ArrayList<PrimeFactorDecomposition> al = new ArrayList<PrimeFactorDecomposition>();
		al.add(this);
		PrimeFactorDecomposition resultant = new PrimeFactorDecomposition(al);
		
		for(long prime : secondary.getDecomposition().keySet()) {
			int power = secondary.getDecomposition().get(prime);
			
			Integer prevPower = resultant.primePowers.get(prime);
			if(prevPower == null) {
				prevPower = 0;
			}

			resultant.primePowers.put(prime, prevPower - power);
		}
		System.out.println("happy with this much: " + resultant.getDecomposition().toString());
		resultant.removeZeroPowers();
		System.out.println(resultant.getDecomposition().toString());
		return resultant;		
	}
	
	
	

	// build one given the raw data of primes and powers as two ArrayLists - the input lists are presumed
	// to have corresponding positions be the prime number and its power, but are NOT presumed to be
	// sorted; neither are the two ArrayList class members
	public PrimeFactorDecomposition(ArrayList<Long> primes, ArrayList<Integer> powers) throws Exception {
		
		int size = primes.size();
		
		if (powers.size() != size) {
			System.out.println("Primes list and powers list are different sizes.");
			throw new Exception();
		}
		
		this.primePowers = new HashMap<Long,Integer>();
		
		for(int i = 0; i < size; i++) {
			this.primePowers.put(primes.get(i), powers.get(i));
		}
		
		this.populatePowers();
		
	}
	
	
	
	// instantiate a new pfd based on lowest common multiple of multiple input pfd's - NOT multiplication
	public PrimeFactorDecomposition(ArrayList<PrimeFactorDecomposition> pfdList) {
		
		this.primePowers = new HashMap<Long, Integer>();
		
		for (PrimeFactorDecomposition pfd : pfdList) {			
			HashMap<Long, Integer> hm = pfd.getDecomposition();
			
			for (long prime : hm.keySet()) {				
				int power = hm.get(prime);
				
				if(this.primePowers.get(prime) == null) {					
					this.primePowers.put(prime, power);					
				} else {					
					this.primePowers.put(prime,  Math.max(power, this.primePowers.get(prime)));
				}
			}
			
		}
		
		this.populatePowers();
	}


	
	private void populatePowers() {
		this.primes = new ArrayList<Long>();
		this.powers = new ArrayList<Integer>();
		
		for (long prime : this.primePowers.keySet()) {			
			int power = this.primePowers.get(prime);
			this.primes.add(prime);
			this.powers.add(power);
			
		}	
		
	}



}
