package uk.ac.tees.q5315908;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;
import uk.ac.tees.q5315908.vehicle.VehicleControlSystem;

public class VehicleControlTest {

	@Test
	public void testGetAvailableVehicle() {
		VehicleControlSystem control = new VehicleControlSystem();
		
		assertNotNull(control.getAvailableVehicle());
	}
	
	@Test
	public void testRemoveVehicle() {
		VehicleControlSystem control = new VehicleControlSystem();
		
		SemiAutonomousVehicle vehicle = control.getAvailableVehicle();
		
		assertTrue(control.removeVehicle(vehicle));
	}

}