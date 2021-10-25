package echoserver;

import java.net.*;
import java.io.*;
public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
	    String server;

	    if(args.length == 0) {
		    server = "127.0.0.1";
	    }
	    else {
		    server = args[0];
	    }

	    try {
		    Socket socket = new Socket(server, portNumber);
		    InputStream socketReceive = socket.getInputStream();
		    OutputStream socketSend = socket.getOutputStream();

		    byte[] buffer = new byte[4096];
		    int bytesRead;

		    while ((bytesRead = System.in.read(buffer)) != -1) {
			    socketSend.write(buffer, 0, bytesRead);
			    socketSend.flush();

			    bytesRead = socketReceive.read(buffer);
			    System.out.write(buffer, 0, bytesRead);
			    System.out.flush();
		    }

		    socket.shutdownOutput();

		    while ((bytesRead = socketReceive.read(buffer)) != -1 ) {
			    System.out.write(buffer, 0, bytesRead);
			    System.out.flush();
		    }
		    socket.close();
	    } catch(Exception e) {
		    System.out.println(e);
	    }
    }

}

