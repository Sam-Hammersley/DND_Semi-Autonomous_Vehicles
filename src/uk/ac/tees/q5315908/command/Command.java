package uk.ac.tees.q5315908.command;

public interface Command {

	/**
	 * Executes the command
	 */
	public abstract void executeCommand();
	
	/**
	 * Reverses the command
	 */
	public abstract void reverseCommand();
	
}