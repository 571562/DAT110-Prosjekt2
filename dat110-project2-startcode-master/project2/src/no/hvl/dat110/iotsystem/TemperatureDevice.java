package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

/**
 * The type Temperature device.
 */
public class TemperatureDevice {

    private static final int COUNT = 10;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        TemperatureSensor sn = new TemperatureSensor();

        // TODO - start
        Client tempClient = new Client("tempDevice", Common.BROKERHOST, Common.BROKERPORT);

        tempClient.connect();

        for (int i = 0; i < COUNT; i++) {
            tempClient.publish(Common.TEMPTOPIC, String.valueOf(sn.read()));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tempClient.disconnect();
        // TODO - end

        System.out.println("Temperature device stopping ... ");


    }
}
