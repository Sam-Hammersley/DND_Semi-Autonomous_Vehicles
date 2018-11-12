package uk.ac.tees.q5315908;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uk.ac.tees.q5315908.command.vehicle.RechargeVehicleCommand;
import uk.ac.tees.q5315908.vehicle.VehicleControlSystem;

public class LocalControlSystemTest {

	@Test
	public void testCommandHandlers() {
		LocalControlSystem controlSystem = new LocalControlSystem();

		assertEquals(controlSystem.getHandler(RechargeVehicleCommand.class).getClass(), VehicleControlSystem.class);
	}
	
}