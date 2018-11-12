package uk.ac.tees.q5315908.vehicle;

import uk.ac.tees.q5315908.Position;
import uk.ac.tees.q5315908.vehicle.behaviours.BusyBehaviour;
import uk.ac.tees.q5315908.vehicle.behaviours.IdleBehaviour;
import uk.ac.tees.q5315908.vehicle.behaviours.RechargingBehaviour;
import uk.ac.tees.q5315908.vehicle.behaviours.VehicleBehaviour;

/**
 * Represents a state the vehicle is in and the behaviours of the vehicle when
 * it's in that state. Restricts {@link VehicleBehaviour} implementations to one
 * instance per behaviour essentially making them singletons with a convenient
 * and unambiguous way of accessing them.
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 */
public enum VehicleState {

	/**
	 * Idle state, can move and recharge.
	 */
	IDLE(new IdleBehaviour()),
	
	/**
	 * Busy state, can recharge if necessary but cannot move.
	 */
	BUSY(new BusyBehaviour()),
	
	/**
	 * Recharging state, cannot move or recharge (it's already charging).
	 */
	RECHARGING(new RechargingBehaviour());
	
	/**
	 * Instance of {@link VehicleBehaviour} for this state.
	 */
	private final VehicleBehaviour behaviour;
	
	/**
	 * Constructs a new {@link VehicleState} with the given behaviour.
	 * 
	 * @param behaviour how the vehicle behaves in this state.
	 */
	private VehicleState(VehicleBehaviour behaviour) {
		this.behaviour = behaviour;
	}

	public void move(SemiAutonomousVehicle vehicle, Position position) {
		behaviour.move(vehicle, position);
	}
	
}