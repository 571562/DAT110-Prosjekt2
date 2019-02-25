package no.hvl.dat110.iotsystem;

/**
 * The type Temperature sensor.
 */
public class TemperatureSensor {

	private static final int RANGE = 20;

    /**
     * Read int.
     *
     * @return the int
     */
    public int read() {

		long seconds = System.currentTimeMillis();

		double temp = RANGE * Math.sin(seconds / 1000);

		return (int) Math.ceil(temp);
	}
}
