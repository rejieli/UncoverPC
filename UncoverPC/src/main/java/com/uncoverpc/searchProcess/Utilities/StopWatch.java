package com.uncoverpc.searchProcess.Utilities;

public class StopWatch {
	
	// Beginning time
	private long beginTime;

	/**
	 * Starts the stopWatch
	 */
	public void start() {
		beginTime = System.currentTimeMillis();
	}

	/**
	 * Stops the stop watch
	 * 
	 * @return formatted time since starting
	 */
	public String stop() {
		return convertAndFormatTime((long) (System.currentTimeMillis() - beginTime));
	}

	/**
	 * converts time and formats it
	 * 
	 * @param totalMilliseconds, long
	 * @return formatted time
	 */
	private String convertAndFormatTime(long totalMilliseconds) {
		// Calculating time left
		// Converting to hours, minutes, seconds, milliseconds
		long hours = (totalMilliseconds - (totalMilliseconds % 3600000)) / 3600000;
		totalMilliseconds = totalMilliseconds - (hours * 3600000);
		long minutes = (long) (totalMilliseconds - (totalMilliseconds % 60000)) / 60000;
		totalMilliseconds = totalMilliseconds - (minutes * 60000);
		long seconds = (long) (totalMilliseconds - (totalMilliseconds % 1000)) / 1000;
		totalMilliseconds = totalMilliseconds - (seconds * 1000);
		long milliseconds = (long) totalMilliseconds;

		// Formatting return
		if (hours > 0) {
			return hours + "h : " + minutes + "m : " + seconds + "s : " + milliseconds + "ms";
		} else if (minutes > 0) {
			return minutes + "m : " + seconds + "s : " + milliseconds + "ms";
		} else if (seconds > 0) {
			return seconds + "s : " + milliseconds + "ms";
		} else {
			return milliseconds + "ms";
		}
	}
}
