package no.hvl.dat110.messagetransport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The type Messaging server.
 */
public class MessagingServer {

	private ServerSocket welcomeSocket;

    /**
     * Instantiates a new Messaging server.
     *
     * @param port the port
     */
    public MessagingServer(int port) {
		
		try {
		
			this.welcomeSocket = new ServerSocket(port);
			
		} catch (IOException ex) {
			
			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

    /**
     * Accept connection.
     *
     * @return the connection
     */
// accept an incoming connection from a client
	public Connection accept () {
		
		Connection connection = null;
		
		// TODO
		// accept TCP connection on welcome socket and create connection
		
		try {
			
			Socket connectionSocket = welcomeSocket.accept();
			
			connection = new Connection(connectionSocket);
			
		} catch (IOException ex) {
			
			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
			// TODO: closing welcomeSocket
		}
		
		return connection;

	}

    /**
     * Stop.
     */
    public void stop() {
		
		if (welcomeSocket != null) {
			
			try {
			welcomeSocket.close();
			} catch (IOException ex) {
				
			System.out.println("Messaging server: " + ex.getMessage());
			ex.printStackTrace();
		}
		}
	}

}
