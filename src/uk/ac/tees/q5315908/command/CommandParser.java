package uk.ac.tees.q5315908.command;

import java.util.HashMap;
import java.util.Map;

import uk.ac.tees.q5315908.LocalControlSystem;
import uk.ac.tees.q5315908.Position;
import uk.ac.tees.q5315908.command.vehicle.MoveVehicleCommand;
import uk.ac.tees.q5315908.command.vehicle.PrintVehicleStatusCommand;
import uk.ac.tees.q5315908.command.vehicle.RechargeVehicleCommand;
import uk.ac.tees.q5315908.command.vehicle.VehicleCommand;
import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;

/**
 * A class to parse string as a command.
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 *
 * @param <T> the type of command this parser creates.
 */
public abstract class CommandParser<T extends Command> {
	
	/**
	 * Prevents instantiation in anywhere else but implementing classes and this class.
	 */
	private CommandParser() {
		
	}
	
	/**
	 * Implementations define how the string should be parsed to produce a command parser.
	 * 
	 * @param vehicle the vehicle to be wrapped in the command created.
	 * @param input the input arguments.
	 * @return some implementation of {@link CommandParser}.
	 */
	public abstract T parse(LocalControlSystem localControlSystem, String[] input);
	
	/**
	 * Maps command identifiers to a {@link CommandParser}, no if statements for determining command type
	 */
	private static final Map<String, CommandParser<?>> PARSERS = new HashMap<>();
	
	/**
	 * Static constructor to add implementations.
	 */
	static {
		PARSERS.put("recharge", new RechargeCommandParser());
		
		PARSERS.put("move", new MoveCommandParser());
		
		PARSERS.put("print", new PrintVehicleCommandParser());
	}
	
	/**
	 * Parses a string and creates a {@link VehicleCommand} object.
	 * 
	 * @param local control system context
	 * @param input the input to be parsed.
	 * @return a {@link VehicleCommand}.
	 */
	public static Command parseInput(LocalControlSystem localControlSystem, String input) {
		String[] split = (input = input.trim().toLowerCase()).split(" ");
		String[] args = split.length > 1 ? split[1].split(",") : null;

		if (!PARSERS.containsKey(split[0])) {
			throw new RuntimeException("No parser for " + split[0]);
		}
		
		return PARSERS.get(split[0]).parse(localControlSystem, args);
	}
	
	/**
	 * Implementation of {@link CommandParser} that parses text into a {@link MoveVehicleCommand}.
	 * 
	 * @author Sam Hammersley - Gonsalves (q5315908)
	 */
	static final class MoveCommandParser extends CommandParser<MoveVehicleCommand> {

		@Override
		public MoveVehicleCommand parse(LocalControlSystem localControlSystem, String[] input) {
			SemiAutonomousVehicle vehicle = localControlSystem.getVehicleControl().getAvailableVehicle();
			
			return new MoveVehicleCommand(vehicle, new Position(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
		}
		
	}
	
	/**
	 * Implementation of {@link CommandParser} that parses text into a {@link RechargeCommandParser}.
	 * 
	 * @author Sam Hammersley - Gonsalves (q5315908)
	 */
	static final class RechargeCommandParser extends CommandParser<RechargeVehicleCommand> {

		@Override
		public RechargeVehicleCommand parse(LocalControlSystem localControlSystem, String[] input) {
			SemiAutonomousVehicle vehicle = localControlSystem.getVehicleControl().getAvailableVehicle();
			
			return new RechargeVehicleCommand(vehicle);
		}
		
	}
	
	/**
	 * Implementation of {@link CommandParser} that parses text into a {@link PrintVehicleStatusCommand}.
	 * 
	 * @author Sam Hammersley - Gonsalves (q5315908)
	 */
	static final class PrintVehicleCommandParser extends CommandParser<PrintVehicleStatusCommand> {

		@Override
		public PrintVehicleStatusCommand parse(LocalControlSystem localControlSystem, String[] input) {
			SemiAutonomousVehicle vehicle = localControlSystem.getVehicleControl().getAvailableVehicle();
			
			return new PrintVehicleStatusCommand(vehicle);
		}
		
	}
	
}