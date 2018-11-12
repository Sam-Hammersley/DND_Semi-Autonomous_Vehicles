package uk.ac.tees.q5315908.vehicle.behaviours;

import uk.ac.tees.q5315908.Position;
import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;

/**
 * Abstraction of vehicle behaviour. Defines the functions of a vehicle behaviour.
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 */
public interface VehicleBehaviour {

	public void move(SemiAutonomousVehicle vehicle, Position position);
	
}