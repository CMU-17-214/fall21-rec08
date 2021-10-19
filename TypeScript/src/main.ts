import { Logger } from "./Logger";

/**
 * The entry class to the logging application.
 */
class Main {

	/**
	 * Creates a logger. Writes some debug and error messages.
	 * 
	 * @param args
	 */
	public runProgram(): void {
		let logger: Logger = new Logger();

		logger.writeDebug("This is a debug message.");
		logger.writeError("This is an error message.");

		for (let i = 0; i < 3; i++) {
			for (let j = 0; j < 5; j++) {
				// Create a generic case when debug messages are printed
				logger.writeDebug(`i=${i}, j=${j}`);

				// Create a specific case when an "error" is detected
				if (i == 1 && j == 3) {
					logger.writeError(`Illegal state detected! i=${i}, j=${j}`);
				}
			}
		}

		// Shut down the logger
		logger.close();
	}
}

new Main().runProgram()