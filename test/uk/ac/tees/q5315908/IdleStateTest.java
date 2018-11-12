package uk.ac.tees.q5315908;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;
import uk.ac.tees.q5315908.vehicle.VehicleControlSystem;

public class IdleStateTest {

	@Test
	public void testMove() {
		VehicleControlSystem controlSystem = new VehicleControlSystem();
		
		SemiAutonomousVehicle vehicle = controlSystem.getAvailableVehicle();
		
		vehicle.move(new Position(15, 15));
		
		final Position expectedPosition = new Position(15, 15);
		assertEquals(expectedPosition, vehicle.getPosition());
		
		final int expectedCharge = 100 - 20;
		assertEquals(expectedCharge, vehicle.getCharge());
	}

}