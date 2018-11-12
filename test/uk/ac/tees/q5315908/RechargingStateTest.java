package uk.ac.tees.q5315908;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;
import uk.ac.tees.q5315908.vehicle.VehicleControlSystem;
import uk.ac.tees.q5315908.vehicle.VehicleState;

public class RechargingStateTest {

	@Test
	public void testMove() {
		VehicleControlSystem controlSystem = new VehicleControlSystem();
		
		SemiAutonomousVehicle vehicle = controlSystem.getAvailableVehicle();
		vehicle.setState(VehicleState.RECHARGING);
		vehicle.decreaseCharge(80);
		vehicle.setPosition(new Position(30, 20));
		
		vehicle.move(new Position(15, 15));
		
		Position expectedPosition = new Position(30, 20);
		assertEquals(expectedPosition, vehicle.getPosition());
		
		int expectedCharge = 20;
		assertEquals(expectedCharge, vehicle.getCharge());
		
		
		vehicle.increaseCharge(80);
		vehicle.move(new Position(15, 15));
		
		expectedPosition = new Position(15, 15);
		assertEquals(expectedPosition, vehicle.getPosition());
		
		expectedCharge = 80;
		assertEquals(expectedCharge, vehicle.getCharge());
	}
	
}