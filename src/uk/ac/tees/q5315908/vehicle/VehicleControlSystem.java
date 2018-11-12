package uk.ac.tees.q5315908.vehicle;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;

import uk.ac.tees.q5315908.command.CommandHandler;
import uk.ac.tees.q5315908.command.vehicle.VehicleCommand;

/**
 * This class represents a control system for managing vehicles.
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 */
public final class VehicleControlSystem implements CommandHandler<VehicleCommand> {

	/**
	 * A {@link Set} of vehicles currently operating.
	 */
	private final Set<SemiAutonomousVehicle> vehicles = new HashSet<>();
	
	/**
	 * Whether this controller is shutdown. Commands can't be executed unless this
	 * field is true.
	 */
	private volatile boolean shutdown = false;

	/**
	 * Vehicles mapped to a {@link Deque} of commands which functions like a stack.
	 */
	private final Map<SemiAutonomousVehicle, Deque<VehicleCommand>> history = new HashMap<>();
	
	/**
	 * {@link VehicleCommand}s to be executed.
	 */
	private final BlockingQueue<VehicleCommand> commands = new LinkedBlockingQueue<VehicleCommand>();
	
	/**
	 * Gets the last executed command and reverses it.
	 */
	public void reverseLastCommand(SemiAutonomousVehicle vehicle) {
		if (history.containsKey(vehicle)) {
			history.get(vehicle).pollLast().reverseCommand();
		} else {
			throw new RuntimeException("No history for " + vehicle);
		}
	}
	
	/**
	 * Retrievs the given number of last executed commands for the specified vehicle.
	 * 
	 * @param vehicle the vehicle to get the commands for.
	 * @param count the number of comamnds to get.
	 * 
	 * @return a {@link Queue} of {@link VehicleCommands}.
	 */
	public Queue<VehicleCommand> getLastCommands(SemiAutonomousVehicle vehicle, int count) {
		if (!history.containsKey(vehicle)) {
			return null;
		}
		
		Deque<VehicleCommand> h = history.get(vehicle);
		Queue<VehicleCommand> commands = new LinkedList<>();
		
		for (int i = 0; i < count; i++) {
			commands.add(h.pollLast());
		}
		
		return commands;
	}
	
	/**
	 * Handles the {@link VehicleCommand} by adding it to the queue to be executed.
	 * 
	 * @param command the command to add to the queue.
	 * @return {@code false} if queue is full {@code true} if the command is added.
	 */
	@Override
	public boolean handleCommand(VehicleCommand command) {
		return commands.offer(command);
	}

	/**
	 * Shuts down this control system and returns a {@link List} of commands that
	 * were queued but not executed.
	 * 
	 * @return a {@link List} of commands that were queued but not executed.
	 */
	public List<VehicleCommand> shutdown() {
		shutdown = true;
		
		return new ArrayList<>(commands);
	}

	@Override
	public void run() {
		while (!shutdown) {
			
			try {
				VehicleCommand command = commands.take();
				SemiAutonomousVehicle vehicle = command.getVehicle();
				
				vehicle.getExecutor().submit(() -> {
					command.executeCommand();
					
					// included so the command is added to history after execution.
					history.putIfAbsent(vehicle, new ConcurrentLinkedDeque<>());
					history.get(vehicle).add(command);
				});
			} catch (InterruptedException e) {
				throw new RuntimeException("Interrupted!", e);
			}
		}
	}
	
	/**
	 * Removes the given vehicle from the set.
	 * 
	 * @param vehicle the vehicle to be removed.
	 */
	public boolean removeVehicle(SemiAutonomousVehicle vehicle) {
		return vehicles.remove(vehicle);
	}

	/**
	 * Gets an available {@link SemiAutonomousVehicle}, creates a new instance if
	 * one does not exist just for simplicity in reality we would wait for an
	 * available vehicle.
	 * 
	 * @return an available {@link SemiAutonomousVehicle}.
	 */
	public SemiAutonomousVehicle getAvailableVehicle() {
		SemiAutonomousVehicle vehicle = vehicles
				.stream()
				.filter(v -> v.getState().equals(VehicleState.IDLE))
				.findAny()
				.orElse(new SemiAutonomousVehicle(this));
		
		vehicles.add(vehicle);
		
		return vehicle;
	}
}