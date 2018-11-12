package uk.ac.tees.q5315908.vehicle.behaviours;

import uk.ac.tees.q5315908.Position;
import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;

public final class BusyBehaviour implements VehicleBehaviour {

	@Override
	public void move(SemiAutonomousVehicle vehicle, Position position) {
		System.out.println("busy, can't move");
	}

}