package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(portNumber);

	    // Wait for connection
            while (true) {
		// Accept connection
                Socket client = socket.accept();

                InputStream  clientIn = client.getInputStream();
                OutputStream clientOut = client.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead;

		//Read data sent by client socket
                while ((bytesRead = clientIn.read(buffer)) != -1) {
		    // Write data back to client
                    clientOut.write(buffer, 0 , bytesRead);
                    clientOut.flush();
                }

		// Close client connection once finished
                client.close();

            }
        } catch(IOException ioe) {
            System.out.println("We caugt an unexpected exception");
        }
    }
}
