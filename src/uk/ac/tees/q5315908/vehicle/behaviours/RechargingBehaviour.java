package uk.ac.tees.q5315908.vehicle.behaviours;

import java.util.concurrent.TimeUnit;

import uk.ac.tees.q5315908.Position;
import uk.ac.tees.q5315908.command.vehicle.RechargeVehicleCommand;
import uk.ac.tees.q5315908.vehicle.SemiAutonomousVehicle;
import uk.ac.tees.q5315908.vehicle.VehicleState;

public final class RechargingBehaviour implements VehicleBehaviour {

	@Override
	public void move(SemiAutonomousVehicle vehicle, Position position) {
		int distance = vehicle.getPosition().getDistance(position);
		
		if (vehicle.getCharge() >= distance + RechargeVehicleCommand.REQUIRES_CHARGING_LIMIT) {
			
			vehicle.setState(VehicleState.BUSY);
			
			vehicle.setPosition(position);
			vehicle.decreaseCharge(distance);
			
			try {
				TimeUnit.MILLISECONDS.sleep(distance * 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			vehicle.setState(VehicleState.IDLE);
			
			System.out.println("Moved vehicle to " + position + " from charging bay");
		} else {
			System.out.println("Vehicle is not charged enough");
		}
	}
	
}