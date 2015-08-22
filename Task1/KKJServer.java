//NWEN 303 Project 2 - Task 1
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian
//Due Date: 19/10/2014 23:59

import java.net.*;
import java.io.*;

public class KKJServer {
    public static void main(String[] args) throws IOException {
        
        ServerSocket sock = null;

        try { 
            sock = new ServerSocket(0);
	    System.out.println("Waiting for connections on " + sock.getLocalPort());
            String input, output;
            
	    while (true) {
		Socket client = sock.accept();
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		Connection c = new Connection(client);
		c.start();
	    }
        } 
        catch (IOException ioe) {
		System.err.println(ioe);
	}
	finally {
		if (sock != null)
			sock.close();
	}
    }
}