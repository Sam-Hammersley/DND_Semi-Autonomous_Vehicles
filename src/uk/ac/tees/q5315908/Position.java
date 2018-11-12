package uk.ac.tees.q5315908;

/**
 * Represents a position in a warehouse using cartesian coordinates
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 */
public final class Position {

	/**
	 * x and y coordinates on a grid in a warehouse.
	 */
	private int x, y;

	/**
	 * Constructs a new {@link Position} with the given coordinates.
	 * 
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		if (x > 49 || y > 49) {
			throw new IllegalArgumentException("Coordinates are limited to 49,49");
		}
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	/**
	 * Gets the distance between this position and another position.
	 *
	 * @param other The other position.
	 * @return The distance.
	 */
	public int getDistance(Position other) {
		int deltaX = Math.abs(x - other.x);
		int deltaY = Math.abs(y - other.y);	
		
		return deltaX + deltaY;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Position)) {
			return false;
		}
		
		Position other = (Position) obj;
		
		return x == other.x && y == other.y;
	}
	
}