public class HexagonalLatticePoint {

	// 2024-09-25, LD: Happy with this.

	/*
	 * Work in a hexagonal lattice where each lattice point in the plane has six neighbouring points
	 * equally spaced around it, all distance 1 away. This is a class for dealing with one point,
	 * relative to an origin-point.
	 *
	 * It is convenient to use a co-ordinate system based on the directions East and "NorthEast" (it's
	 * 60 degrees off East, not 45 degrees).
	 */
	private int east;
	private int northEast;

	public HexagonalLatticePoint (int newEast, int newNorthEast) {
		this.east = newEast;
		this.northEast = newNorthEast;
	}

	public int getEast() {
		return this.east;
	}

	public int getNorthEast() {
		return this.northEast;
	}



	/*
	 * At times we will also need to find a point's "true" rectilinear co-ordinates - so we need
	 * to break NorthEast into its 0.5 East component and 0.5*sqrt(3) North component (consider
	 * some basic trigonometry on the unit-sided equilateral triangle, if this is not apparent).
	 */
	public double getTrueEast() {
		return this.east + this.northEast * 0.5;
	}

	public double getTrueNorth() {
		return this.northEast * 0.5 * Math.sqrt(3);
	}



	/*
	 * Define a point's Angle to use DEGREES, be strictly POSITIVE, to start from EAST and go ANTICLOCKWISE.
	 * I.e. these values will be in the interval [0, 360).
	 */
	public double getAngle() {

		double result = Math.atan2(this.getTrueNorth(), this.getTrueEast()); // much nicer than atan() and manual zero-east handling
		result *= 180 / Math.PI;
		result += 360;
		return result % 360;
	}



	/*
	 * Miscellaneous useful methods
	 */
	public boolean isOrigin() {
		return (this.east == 0 && this.northEast == 0);
	}

	public HexagonalLatticePoint add(HexagonalLatticePoint toAdd) {
		return new HexagonalLatticePoint(this.getEast() + toAdd.getEast(), this.getNorthEast() + toAdd.getNorthEast());
	}

	public HexagonalLatticePoint subtract(HexagonalLatticePoint toSubtract) {
		return new HexagonalLatticePoint(this.getEast() - toSubtract.getEast(), this.getNorthEast() - toSubtract.getNorthEast());
	}
}
