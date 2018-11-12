package uk.ac.tees.q5315908.vehicle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.ac.tees.q5315908.Position;
import uk.ac.tees.q5315908.command.vehicle.RechargeVehicleCommand;

/**
 * Represents a semi-autonomous vehicle. 
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 */
public final class SemiAutonomousVehicle {
	
	/**
	 * Charge when fully charged.
	 */
	public static final int MAX_CHARGE = 100;
	
	/**
	 * Used to execute commands.
	 */
	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	
	/**
	 * The {@link VehicleControlSystem} this vehicle is controlled by.
	 */
	private final VehicleControlSystem controlSystem;
	
	/**
	 * Current charge of this vehicle.
	 */
	private int charge = MAX_CHARGE;

	/**
	 * The current position of this vehicle. Starts in charging bay.
	 */
	private Position position = RechargeVehicleCommand.CHARGING_BAY_POSITION;

	/**
	 * The current vehicle state.
	 */
	private VehicleState state = VehicleState.IDLE;
	
	/**
	 * Constructs a new {@link SemiAutonomousVehicle}.
	 * 
	 * @param controlSystem the control system this vehicle is controlled by.
	 */
	public SemiAutonomousVehicle(VehicleControlSystem controlSystem) {
		this.controlSystem = controlSystem;
	}
	
	public VehicleControlSystem getControlSystem() {
		return controlSystem;
	}

	/**
	 * Sets the current state of the vehicle.
	 * 
	 * @param state the new state.
	 */
	public void setState(VehicleState state) {
		this.state = state;
	}

	/**
	 * Gets the vehicles current state.
	 * 
	 * @return the current state of the vehicle.
	 */
	public VehicleState getState() {
		return state;
	}
	
	/**
	 * Sets the current position of the vehicle.
	 * 
	 * @param position the new position.
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

	/**
	 * Gets the current position of the vehicle.
	 * 
	 * @return the current position of this vehicle.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Invokes the move function of the current state.
	 * 
	 * @param position the new position.
	 */
	public void move(Position position) {
		state.move(this, position);
	}

	/**
	 * Gets the charge of this vehicle.
	 * 
	 * @return the current charge.
	 */
	public int getCharge() {
		return charge;
	}
	
	/**
	 * Decreases the charge by the amount
	 * 
	 * @param amount
	 */
	public void decreaseCharge(int amount) {
		this.charge -= amount;
	}
	
	/**
	 * Increases the charge by the amount
	 * 
	 * @param amount
	 */
	public void increaseCharge(int amount) {
		this.charge += amount;
	}
	
	/**
	 * Gets the {@link #executor} for this vehicle.
	 * 
	 * @return an {@link ExecutorService}.
	 */
	public ExecutorService getExecutor() {
		return executor;
	}

}