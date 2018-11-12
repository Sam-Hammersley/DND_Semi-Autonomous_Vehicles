package uk.ac.tees.q5315908.command.vehicle;

import uk.ac.tees.q5315908.command.Command;
import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;

/**
 * Represents a command to give to a vehicle.
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 */
public abstract class VehicleCommand implements Command {

	/**
	 * The vehicle to execute this command.
	 */
	protected final SemiAutonomousVehicle vehicle;

	/**
	 * Constructs new instances of {{@link VehicleCommand}. Protected access
	 * modifier to prevent instantiation anywhere other than concrete
	 * implementations.
	 * 
	 * @param vehicle the vehicle to execute this command.
	 */
	protected VehicleCommand(SemiAutonomousVehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * Gets the vehicle this command is to be executed for.
	 * 
	 * @return the vehicle executing this command.
	 */
	public SemiAutonomousVehicle getVehicle() {
		return vehicle;
	}

}