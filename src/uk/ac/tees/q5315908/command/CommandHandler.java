package uk.ac.tees.q5315908.command;

/**
 * Implementations define how to handle commands of type T.
 * 
 * @author Sam Hammersley - Gonsalves (q5315908)
 *
 * @param <T> the type of {@link Command}.
 */
public interface CommandHandler<T extends Command> extends Runnable {

	/**
	 * Handles the given {@link Command}.
	 * 
	 * @param command the command to handle.
	 * @return {{@code true} if handled appropriately.
	 */
	public boolean handleCommand(T command);
	
}