package uk.ac.tees.q5315908.vehicle.behaviours;

import java.util.concurrent.TimeUnit;

import uk.ac.tees.q5315908.Position;
import uk.ac.tees.q5315908.command.vehicle.RechargeVehicleCommand;
import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;
import uk.ac.tees.q5315908.vehicle.VehicleState;

public final class IdleBehaviour implements VehicleBehaviour {

	@Override
	public void move(SemiAutonomousVehicle vehicle, Position position) {
		int distanceToCharge = vehicle.getPosition().getDistance(RechargeVehicleCommand.CHARGING_BAY_POSITION);
		int distance = vehicle.getPosition().getDistance(position);
		
		if (vehicle.getCharge() - distanceToCharge < RechargeVehicleCommand.REQUIRES_CHARGING_LIMIT 
				|| distance > vehicle.getCharge()) {
			System.out.println("Recharging before moving to destination");
			vehicle.getControlSystem().handleCommand(new RechargeVehicleCommand(vehicle));
		}
		
		vehicle.setState(VehicleState.BUSY);
		
		vehicle.setPosition(position);
		vehicle.decreaseCharge(distance);
		
		try {
			TimeUnit.MILLISECONDS.sleep(distance * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		vehicle.setState(VehicleState.IDLE);
		
		System.out.println("Moved vehicle to " + position);
	}

}