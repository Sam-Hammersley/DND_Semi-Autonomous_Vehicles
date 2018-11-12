package uk.ac.tees.q5315908;

import org.junit.Test;

import uk.ac.tees.q5315908.Position;

import static org.junit.Assert.assertEquals;

public class PositionTest {

	@Test
	public void testDistanceCalc() {
		Position position = new Position(20, 13);
		
		Position other = new Position(10, 32);
		
		assertEquals(29, position.getDistance(other));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructor() {
		new Position(100, 100);
	}

}