package counters;

public class TimeCounter {

	 /* variable holding our counters. We support up to 100 counters
	 */
	private static long[] time_counters = new long[100];
	
	/**
	 * Resets the internal counter of counterIndex to zero
	 */
	public static void resetCounter(int counterIndex) {
		if (counterIndex < time_counters.length)
			time_counters[counterIndex] = 0;
	}
	
	/**
	 * Returns the current count for given counterIndex
	 * 
	 * @return the current count for given counterIndex
	 */
	public static long getCount(int counterIndex) {
		if (counterIndex  < time_counters.length )
			return time_counters[counterIndex];
		return 0;
	}
	
	/*
	 * Get the current time and return it
	 */
	public static long startOrEndTimeCounter() {
		return System.nanoTime();
	}
	
	/*
	 * Calculate TotalTime and store it to an index
	 */
	public static boolean calculateTime(long timeStart , long timeEnd,int counterIndex) {
		if (counterIndex  < time_counters.length )
			time_counters[counterIndex] = timeEnd - timeStart;
		return true;
	}
	
	
	/*
	 * Average Time after 100 searches (Calculate the average of the 100 counters)
	 */
	public static long averageTimeOnSearching() {

		long x = 0;

		for (int i = 0; i < time_counters.length; i++) {
			x += TimeCounter.getCount(i);
		}
		long y = x / time_counters.length;

		return y;
	}
	
	public static long[] getCounters() {
		return time_counters;
	}

	public static void setCounters(long[] counters) {
		TimeCounter.time_counters = counters;
	}	
}
