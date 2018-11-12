package uk.ac.tees.q5315908.command.vehicle;

import java.util.concurrent.TimeUnit;

import uk.ac.tees.q5315908.Position;
import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;
import uk.ac.tees.q5315908.vehicle.VehicleState;

/**
 * Commands a vehicle to recharge.
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 */
public class RechargeVehicleCommand extends VehicleCommand {
	
	/**
	 * The {@link Position} of the charging bay.
	 */
	public static final Position CHARGING_BAY_POSITION = new Position(5, 5);
	
	/**
	 * The limit at which a vehicle needs to recharge.
	 */
	public static final int REQUIRES_CHARGING_LIMIT = 10;
	
	/**
	 * The {@link Position} that the vehicle was previously in before moving.
	 */
	private final Position previousPosition;
	
	public RechargeVehicleCommand(SemiAutonomousVehicle vehicle) {
		super(vehicle);
		this.previousPosition = vehicle.getPosition();
	}

	@Override
	public void executeCommand() {
		vehicle.setPosition(CHARGING_BAY_POSITION);
		vehicle.setState(VehicleState.RECHARGING);

		for (int i = vehicle.getCharge(); i < SemiAutonomousVehicle.MAX_CHARGE; i++) {

			try {
				vehicle.increaseCharge(1);
				System.out.println("Charging: " + vehicle + " " + vehicle.getCharge());
				TimeUnit.MILLISECONDS.sleep(100);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		vehicle.setState(VehicleState.IDLE);
	}

	@Override
	public void reverseCommand() {
		vehicle.setPosition(previousPosition);
	}

}