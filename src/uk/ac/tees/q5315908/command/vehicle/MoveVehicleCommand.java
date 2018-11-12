package uk.ac.tees.q5315908.command.vehicle;

import uk.ac.tees.q5315908.Position;
import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;

/**
 * Commands a {@link SemiAutonomousVehicle} to move to a specified
 * {@link Position} and keeps a record of the position the vehicle moved from.
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 */
public class MoveVehicleCommand extends VehicleCommand {

	/**
	 * The {@link Position} to move to.
	 */
	private final Position position;

	/**
	 * The {@link Position} that the vehicle was previously in before moving.
	 */
	private final Position previousPosition;

	public MoveVehicleCommand(SemiAutonomousVehicle vehicle, Position position) {
		super(vehicle);
		this.position = position;
		this.previousPosition = vehicle.getPosition();
	}

	@Override
	public void executeCommand() {
		if (vehicle.getPosition().getDistance(position) > SemiAutonomousVehicle.MAX_CHARGE) {
			throw new RuntimeException("Can't travel that far.");
		}
		
		vehicle.move(position);
	}

	@Override
	public void reverseCommand() {
		vehicle.move(previousPosition);
	}

}