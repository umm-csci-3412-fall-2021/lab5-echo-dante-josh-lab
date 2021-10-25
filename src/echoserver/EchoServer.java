package echoserver;

import java.net.*;
import java.io.*;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(portNumber);

            while (true) {
                Socket client = socket.accept();

                InputStream  clientIn = client.getInputStream();
                OutputStream clientOut = client.getOutputStream();

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = clientIn.read(buffer)) != -1) {
                    clientOut.write(buffer, 0 , bytesRead);
                    clientOut.flush();
                }

                client.close();

            }
        } catch(IOException ioe) {
            System.out.println("We caugt an unexpected exception");
        }
    }
}
