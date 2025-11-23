public class HexagonalLatticeTriangle {
	// one of the points is always at the origin
	private HexagonalLatticePoint a;
	private HexagonalLatticePoint b;



	public HexagonalLatticeTriangle(HexagonalLatticePoint a, HexagonalLatticePoint b) {
		this.a = a;
		this.b = b;
	}

	public boolean isDegenerate() {
		if(a.isOrigin() || b.isOrigin() || a.subtract(b).isOrigin()) { // detects 2 points coinciding
			return true;
		} else {
			return(a.getAngle() % 180 == b.getAngle() % 180); // detects collinearity
		}
	}
	public double getOAngle() throws Exception {
		if (this.isDegenerate()) {
			throw new Exception();
		}

//		System.out.println (a.getAngle());
//		System.out.println (b.getAngle());

		// b's angle, coming anticlockwise from a, expressed positively
		double result = b.getAngle() - a.getAngle();
//		System.out.println("The two points having absolute bearings " + a.getAngle() + " and " + b.getAngle() + ", their initial relative angle is " + result + ".");
		result += 360;

		result %= 360;
//		System.out.println("Or expressed positively, " + result + ".");

		// now ensure it's under 180 (180 itself being degenerate, and already ruled out)
		return result >= 180 ? 360 - result : result;



	}

	// TODO SAY something about exceptions... ALSO BELONGS ON A CL
	// todo may be better just to avoid exceptions by treating degenerates as just another class...

	public double getAAngle() throws Exception {
		HexagonalLatticePoint origin = new HexagonalLatticePoint(0,0);
		HexagonalLatticeTriangle relabelled = new HexagonalLatticeTriangle(origin.subtract(a), b.subtract(a));

		return relabelled.getOAngle();
	}

	public double getBAngle() throws Exception {
		return 180 - this.getAAngle() - this.getOAngle();
	}

	public String classify() throws Exception {
		if (this.isDegenerate()) {
			return "Degenerate";
		}

		if (this.getAAngle() == 60 || this.getBAngle() == 60) {
			return this.getOAngle() == 60 ? "Equilateral" : "Wrong"; // "wrong" as in, the 60-degree angle is not at the origin
		} else {
			return this.getOAngle() == 60 ? "Right" : "Unremarkable";
		}

	}

	// here goes...

	/*

	public HexagonalLatticePoint incentre() throws Exception {
		// the line passing through the origin has equation kx+ly = 0
		double angA = this.a.getAngle();
		double angB = this.b.getAngle();
		double angIncentre = angA + 0.5 * (angB - angA);
		angIncentre += 360;
		angIncentre %= 360;
		angIncentre *= Math.PI / 180; // back to radians

		double k, l;

		if (angIncentre == 90 || angIncentre == 270) {
			//verticality
			k = 1;
			l = 0;
		} else {
			k = -Math.tan(angIncentre);
			l = 1;
		}

	}

	*/

		// the line passing through A has equation mx+ny+P=0
		// TODO might be better off writing a Line class... might need to allow non-Lattice Points...

		// maybe starting with incentres would go better than CALCULATING them...

		// rab if... we're just given the incentre, and its position in relation to a point that's 60'?
		// that nails down two of the lines...
		// ah no. multiple ways of achieving the final line would remain possible

		// or how about this. incentre and 60-degree O-point... somehow know which points on the A-line are
		// 1/ latticepoints and 2/ valid as corners - ie not leading to infinite or "open" non-triangle triangles...
		// then FOR each such... determine the line it's forced to be on, given tangency with incircle... and
		// finally, check latticity of its intersection with the B-line...?

		// also, might be easy to do equilaterals separately... will need to prove that these have ONLY two possible
		// rotations/reflections...

		// then for non-equilaterals... use hexagonal numbers to assign any point to the "Nth ring" away from the origin
		// ... further segregate them to 1 of 6 sectors... well we would just need to do this TEST for each point of ONE
		// sector... then multiply up by 6.

		// scalenes are impossible remember

		// ooh there's further symmerty isn't there... we can do the calculations while insisting that the A-line be
		// shorter than the B one... and then just double up, for the reflected cases...

		// call it the "point of equilaterality"... and then work INWARDS.
		// (it has to exist - however one navigates from incentre to O, just do that, rotated about 60')

		// well, think that's the algorithm nailed down, wouldn't you say?

		// wouldn't be too hard either to work them out in order of inradius either... or can a rank N ever have larger i/r than rank N+1

		// not quite sure if able to just do HALF of each hexagon-side? or is that the same symmetry as saying that the A-line will be
		// the short one? mem teth THREE lines whose relative sizes have to be organized...

		/*
		 * 2024-09-26 - here goes
		 */







}
