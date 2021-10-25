package echoserver;

import java.net.*;
import java.io.*;
public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
	    String server;
		
	    // If no argument provided, make server local host
	    // Else set server to inital argument
	    if(args.length == 0) {
		    server = "127.0.0.1";
	    }
	    else {
		    server = args[0];
	    }

	    try {

		    // Create socket
		    Socket socket = new Socket(server, portNumber);
		    InputStream socketReceive = socket.getInputStream();
		    OutputStream socketSend = socket.getOutputStream();

		    byte[] buffer = new byte[4096];
		    int bytesRead;

		    // Read data in from user input
		    while ((bytesRead = System.in.read(buffer)) != -1) {

			    // Send data to server 
			    socketSend.write(buffer, 0, bytesRead);
			    socketSend.flush();

			    // Receive data from server and write it out
			    bytesRead = socketReceive.read(buffer);
			    System.out.write(buffer, 0, bytesRead);
			    System.out.flush();
		    }


		    // Tell server we won't be sending anymore output
		    socket.shutdownOutput();


		   // Read whatever data the server has left
		    while ((bytesRead = socketReceive.read(buffer)) != -1 ) {
			    System.out.write(buffer, 0, bytesRead);
			    System.out.flush();
		    }

		    // Close socket connection
		    socket.close();
	    } catch(Exception e) {
		    System.out.println(e);
	    }
    }

}

