//NWEN 303 Project 2 - Task 2-5
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian
//Due Date: 19/10/2014 23:59

import java.io.*;
import java.net.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Connection extends Thread {
    private static AtomicInteger count = new AtomicInteger(0);
    
    private Socket client;
    
    public Connection(Socket c) {
	client = c;
	count.getAndIncrement();
    }
    
    public void run() {
	BufferedReader networkBin = null;
	OutputStreamWriter networkPout = null;
    
	try {
	    networkBin = new BufferedReader(new InputStreamReader(client.getInputStream()));
	    networkPout = new OutputStreamWriter(client.getOutputStream());
	    String response = null;
	    
	    while (true) {
		String line = networkBin.readLine();
		System.out.println("Client "+count+": "+line);
		//found the key!
		if (line.equals("Plaintext found!")) {
		    response = ":)";
		    break;
		}
		//did not found the key!
		else if (line.equals("No key found!")) {
		    response = ":(";
		    break;
		}
		networkPout.write(response+"\r\n");
		System.out.println("Server: "+response);
		networkPout.flush();
	    }
	}
	catch (IOException e) {
	    System.err.println(e);
	}
	finally {
	    try {
		if (client != null) {
		    client.close();
		}
	    }
	    catch (IOException ioe) {
		System.err.println(ioe);
	    }
	}	
    }  
}