import fs from 'fs'

/**
 * Supports writing of debug and error messages (with different prefixes) to
 * either the console or an output file. Configurable via constant fields within
 * the class.
 * 
 * TODO Rewrite the code such that different loggers can be configured and
 * added.
 */
class Logger {

	// Change this to false to disable logging to the console
	private static CONSOLE_ENABLED: boolean = true;

	// Change this to false to disable logging to the file
	private static LOGFILE_ENABLED: boolean= true;
	private static LOGFILE_NAME: string = "log.txt";

	// Strings
	private static DEBUG_PREFIX: string = "[Debug]";
	private static ERROR_PREFIX: string = "[Error]";
	private static LOGGER_STARTED_UP: string = "Logger started up.";
	private static LOGGER_SHUTTING_DOWN: string = "Logger shutting down.";

	private fileOut: fs.WriteStream | null = null;

	constructor() {
		if (Logger.LOGFILE_ENABLED) {
			this.fileOut = fs.createWriteStream(Logger.LOGFILE_NAME);
			this.fileOut.write(Logger.LOGGER_STARTED_UP + "\n");
		}
		if (Logger.CONSOLE_ENABLED) {
			console.log(Logger.LOGGER_STARTED_UP);
		}
	}

	/**
	 * Writes a debug message to the logging system.
	 * 
	 * @param message
	 *            The debug message to write.
	 */
	public writeDebug(message: string): void {
		let logLine: string = Logger.DEBUG_PREFIX + " " + message;
		if (Logger.CONSOLE_ENABLED) {
			console.log(logLine);
		}
		if (Logger.LOGFILE_ENABLED && this.fileOut) {
			this.fileOut.write(logLine + "\n");
		}
	}

	/**
	 * Writes an error message to the logging system.
	 * 
	 * @param error
	 *            The error message to write.
	 */
	public writeError(error: string): void {
		let logLine: string = Logger.ERROR_PREFIX + " " + error;
		if (Logger.CONSOLE_ENABLED) {
			console.log(logLine);
		}
		if (Logger.LOGFILE_ENABLED && this.fileOut) {
			this.fileOut.write(logLine + "\n");
		}
	}

	/**
	 * Shuts down the logging system. After this method is called, the logger
	 * should not be used.
	 */
	public close(): void {
		if (Logger.CONSOLE_ENABLED) {
			console.log(Logger.LOGGER_SHUTTING_DOWN);
		}
		if (Logger.LOGFILE_ENABLED && this.fileOut) {
			this.fileOut.end(Logger.LOGGER_SHUTTING_DOWN + "\n");
		}
	}

}

export { Logger }