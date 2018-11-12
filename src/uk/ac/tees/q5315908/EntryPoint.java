package uk.ac.tees.q5315908;

import java.util.Scanner;

import uk.ac.tees.q5315908.command.Command;
import uk.ac.tees.q5315908.command.CommandParser;

public final class EntryPoint {

	public static void main(String[] args) {
		LocalControlSystem controlSystem = new LocalControlSystem();
		controlSystem.startUp();

		try (Scanner scanner = new Scanner(System.in)) {
			
			System.out.println("Enter a command. Usage: \"print|recharge|move x,y\"");
			while (scanner.hasNextLine()) {

				Command command = CommandParser.parseInput(controlSystem, scanner.nextLine());
				
				controlSystem.command(command);
			}
		}
	}
	
}