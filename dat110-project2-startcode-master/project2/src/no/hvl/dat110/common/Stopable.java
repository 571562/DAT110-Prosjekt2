package no.hvl.dat110.common;

/**
 * The type Stopable.
 */
public abstract class Stopable extends Thread {

	private boolean stop = false;
    /**
     * The Name.
     */
    protected String name;

    /**
     * Instantiates a new Stopable.
     *
     * @param name the name
     */
    public Stopable(String name) {
		this.name = name;
	}

    /**
     * Do stop.
     */
    public synchronized void doStop() {
		stop = true;
	}

    /**
     * Do cont boolean.
     *
     * @return the boolean
     */
    public synchronized boolean doCont() {
		return !stop;

	}

    /**
     * Do process.
     */
    public abstract void doProcess();
	
	public void run() {

		Logger.log(name + " running");
		
		while (doCont()) {

			doProcess();
			
		}

		Logger.log(name + " stopping");

	}
}
