package euler;

public class RootSquarePair {
	private long root;
	private long square;
	private long mod;
	
	public RootSquarePair(long root, long mod) {
		this.root = root;
		this.square = root * root;
		this.square %= mod;
		this.mod = mod;			
	}	
	
	public long getRoot() {
		return this.root;
	}
	
	public long getSquare() {
		return this.square;
	}
		
	public String toString() {
		return this.root + "^2 = " + this.square + " (mod " + mod + ")";
	}
	
	public int getSquaresFirstDigit() {
		String str = "000000000" + this.square; // FIXME crude approach
		
		long currentMod = this.mod;
		/*
		System.out.print("Given that we're working mod " + mod);
		System.out.print(", what's the first applicable digit of " + this.square + "?");
		*/
		
		
		while(currentMod > 10) {
			str = str.substring(0, str.length() - 1);
			currentMod /= 10;
		}
		/*
		System.out.println("Well clearly it's " + str);
		*/
		return Integer.parseInt(str);
	}
	
}
