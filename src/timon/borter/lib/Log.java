package timon.borter.lib;

/**
 * Imports.
 */
import java.time.LocalDateTime;

/**
 * Controls the log. Writes messages with a timestamp and specific title.
 * 
 * @author Timon Borter
 * 
 */
public class Log {
	/**
	 * Write an error message.
	 * 
	 * @param error
	 *            The message to write.
	 */
	public static void writeError(String error) {
		// Create timestamp and title
		System.out.println(LocalDateTime.now() + " ERROR:");

		// Write down message
		System.out.println("\t" + error);
	}

	/**
	 * Write an information message.
	 * 
	 * @param information
	 *            The message to write.
	 */
	public static void writeInformation(String information) {
		// Create timestamp and title
		System.out.println(LocalDateTime.now() + " INFORMATION:");

		// Write down message
		System.out.println("\t" + information);
	}
}
