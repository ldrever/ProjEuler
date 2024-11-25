
/* CHECKLIST

- debug mode
- default value
- input safety
- neat comments



*/

/* HISTORY

2024-09-25, LD
--------------
Started with a bottom-up approach and built classes HexagonalLatticePoint (fully happy with) and
HexagonalLatticeTriangle (needs some final polishing, but is finally returning correct angles).


*/

/* PROBLEM SOURCE AND STATEMENT

https://projecteuler.net/problem=883

In this problem we consider triangles drawn on a hexagonal lattice, where each lattice point in the plane has six
neighbouring points equally spaced around it, all distance 1 away.

We call a triangle remarkable if

    All three vertices and its incentre lie on lattice points
    At least one of its angles is 60 degrees
    
Above [see URL] are four examples of remarkable triangles, with 60-degree angles illustrated in red. Triangles A and B have
inradius 1; C has inradius sqrt(3); D has inradius 2.

Define T(r) to be the number of remarkable triangles with inradius <= r. Rotations and reflections, such as triangles
A and B above, are counted separately; however direct translations are not. That is, the same triangle drawn in 
different positions of the lattice is only counted once.

You are given T(0.5) = 2, T(2) = 44, and T(10) = 1302.

Find T(10^6).

*/

/* DISCUSSION

TODO will need to read up on triangle geometry somewhat. But let's warm up by trying to gather some evidence about a 
conjecture I have, based on the diagrams:

Label as a and b the two sides that are constrained to be at a 60-degree angle. 

CONJECTURE 1: THE CLOSEST POINTS TO THE INCENTRE, ON a AND b, LIE ON LATTICE POINTS.

Hmm. Well let's at least define a triangle object... would it make sense to use a system of coordinates such that x
lies along the "East" line and y lies along the "Northeast" one - at 60 rather than the usual 90 degrees away from x?


TODO there will need to be some final deduplication - the reason I've not tried to enforce this early on, e.g. through
saying that side a is no longer than side b, or side a is encountered first if starting at north and moving clockwise
... is that there are cases such as equilateral triangles where even side c could be considered swappable with a or b.

TODO perhaps treat all equilaterals separately? Note that any (remarkable) triangle has either ONE 60-degree angle, or
THREE.

Yes let's do it that way. Perhaps start with a class for triangles; equilateral and non-equilateral can then be separate
subclasses...

*/



package euler;

import java.math.BigInteger;
import java.lang.Math;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Problem0883_RemarkableTriangles {

	public static void main(String args[]) throws Exception {

		// TODO these shld probs be tests...
		// TODO better follow-along debugth...
		HexagonalLatticePoint a = new HexagonalLatticePoint(1,0);
		HexagonalLatticePoint b = new HexagonalLatticePoint(2,0);
		HexagonalLatticeTriangle hlt = new HexagonalLatticeTriangle(a,b);
		
//		System.out.println("At O: " + hlt.getOAngle());
//		System.out.println("At A: " + hlt.getAAngle());
//		System.out.println("At B: " + hlt.getBAngle());
		
		System.out.println(hlt.classify());
		
		
		
		// TODO ok so angles are working at last. next is to classify triangles (degenerate being a class?)
		// based on single 60 corner at origin, single 60 corner elsewhere, fully equilateral, or out of scope
		// with enum maybe?..
		
		// TODO find incentre? detect whether on a lattice point or not?
		
		// TODO might we be better off starting by finding possible incentres?
		
		// TODO search for counterexamples to that conjecture
		
		// todo based on circle geometry, maybe go back to the geometric-decreasing circles after this?
		
		/*
		 * 2024-09-26 - here goes
		 */
	
	for(int hexRadius = 1; hexRadius < 16; hexRadius++) { //for now TODO
		// define hexRadius such that the 6 points that form a hexagon surrounding the origin have
		// a hexRadius of 1, the 12 points surrounding THOSE have a hexRadius of 2, and so on. what
		// we'll do is demand that the incentre lie at the origin, and that the ONE 60' corner of a
		// scalene triangle lie at a point whose hexRadius gradually increases
		
		// TODO equilaterals
		/*
			Let's make the following demands:
			
			1/ our scalene triangle has points N, E and S with incentre O
			2/ the angle at N is > 60', at E is < 60', and at S is = 60'
			3/ S lies on the southern edge of a hexagon surrouding O
			4/ labelling the sides s, e and n opposite points S, E and N...
			5/ ... side lengths inevitably go e < s < n
			6/ that the points S N E go in that order CLOCKWISE around the origin
			
			
			 
			
		
		
		*/
		HexagonalLatticePoint startingCorner = new HexagonalLatticePoint(0, -hexRadius);
			
		
		
	}
		
	}

}


/* OUTPUT



*/

/* GREEN TICK CHECK



*/