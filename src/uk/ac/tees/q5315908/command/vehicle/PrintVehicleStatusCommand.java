package uk.ac.tees.q5315908.command.vehicle;

import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;

public class PrintVehicleStatusCommand extends VehicleCommand {

	public PrintVehicleStatusCommand(SemiAutonomousVehicle vehicle) {
		super(vehicle);
	}

	@Override
	public void executeCommand() {
		System.out.println("charge: " + vehicle.getCharge());
		System.out.println("pos: " + vehicle.getPosition());
		System.out.println("state: " + vehicle.getState());
	}

	@Override
	public void reverseCommand() {
		
	}

}