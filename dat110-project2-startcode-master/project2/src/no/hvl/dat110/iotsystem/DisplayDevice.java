package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;

/**
 * The type Display device.
 */
public class DisplayDevice {
	
	private static final int COUNT = 10;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		
		// TODO - START
        Client displayClient = new Client("displayDevice", Common.BROKERHOST, Common.BROKERPORT);
        displayClient.connect();
        displayClient.createTopic(Common.TEMPTOPIC);
        displayClient.subscribe(Common.TEMPTOPIC);

        for(int i = 0; i < COUNT; i++) {
            System.out.println("Displaying : " + ((PublishMsg) displayClient.receive()).getMessage());
        }

        displayClient.disconnect();
		// TODO - END
		
		System.out.println("Display stopping ... ");

		
	}
}
