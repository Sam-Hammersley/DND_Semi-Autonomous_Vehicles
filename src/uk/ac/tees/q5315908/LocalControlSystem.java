package uk.ac.tees.q5315908;

import java.util.HashMap;
import java.util.Map;

import uk.ac.tees.q5315908.command.Command;
import uk.ac.tees.q5315908.command.CommandHandler;
import uk.ac.tees.q5315908.command.vehicle.MoveVehicleCommand;
import uk.ac.tees.q5315908.command.vehicle.PrintVehicleStatusCommand;
import uk.ac.tees.q5315908.command.vehicle.RechargeVehicleCommand;
import uk.ac.tees.q5315908.vehicle.VehicleControlSystem;

/**
 * Represents a local control system of a warehouse. 
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 */
public final class LocalControlSystem {
	
	/**
	 * Controls vehicles for the warehouse in which this local control system resides. 
	 */
	private final VehicleControlSystem vehicleControl = new VehicleControlSystem();
	
	/**
	 * {@link Map} mapping types of {@link Command}s to corresponding {@link CommandHandler}s.
	 */
	private final Map<Class<? extends Command>, CommandHandler<? extends Command>> commandHandlers = new HashMap<>();

	/**
	 * Starts the controllers up.
	 */
	public void startUp() {
		commandHandlers.put(RechargeVehicleCommand.class, vehicleControl);
		commandHandlers.put(MoveVehicleCommand.class, vehicleControl);
		commandHandlers.put(PrintVehicleStatusCommand.class, vehicleControl);
		
		//add other command handlers
		
		commandHandlers.values().stream().map(Thread::new).forEach(Thread::start);
	}

	/**
	 * Gets a {@link CommandHandler} for the given type.
	 * 
	 * @param type the type of {@link Command} to get the {@link CommandHandler} for.
	 * @return an appropriate {@link CommandHandler}.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Command> CommandHandler<Command> getHandler(Class<T> type) {
        // warning produced here "uses unchecked or unsafe operations."
        return (CommandHandler<Command>) commandHandlers.get(type);
    }

	/**
	 * Gets the appropriate {@link CommandHandler} for the command and executes it.
	 * 
	 * @param command the command to be executed.
	 * 
	 * @return {@code true} if the command was handled appropriately.
	 */
	public boolean command(Command command) {
		CommandHandler<Command> handler = getHandler(command.getClass());
		
		return handler.handleCommand(command);
	}
	
	/**
	 * Gets the vehicle control system.
	 * 
	 * @return a {@link VehicleControlSystem} instance for this {@link LocalControlSystem}
	 */
	public VehicleControlSystem getVehicleControl() {
		return vehicleControl;
	}

}