package no.hvl.dat110.common;

/**
 * The type Logger.
 */
public class Logger {

    /**
     * The constant debug.
     */
    public static boolean debug = true;

    /**
     * Log.
     *
     * @param s the s
     */
    public static void log(String s) {

		if (debug) {
			System.out.println(s);
		}
	}

    /**
     * Lg.
     *
     * @param s the s
     */
    public static void lg(String s) {

		if (debug) {
			System.out.print(s);
		}
	}
}
