//NWEN 303 Project 2 - Task 1
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian
//Due Date: 19/10/2014 23:59

import java.io.*;
import java.net.*;

public class KKJClient {
    public static void main(String[] args) throws IOException {
        
        if (args.length < 2) {
		System.err.println("Usage: java KKJClient <IP address> <Port number>");
		System.exit(0);
	}

        BufferedReader in = null;
	PrintWriter out = null;
        Socket sock = null;
        
        try {
            sock = new Socket(args[0], Integer.parseInt(args[1]));
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);
         
	    //allow user to input
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            
            String user;

            //get output (response) from the server
            while ((user = stdIn.readLine()) != null) {                   
                if (user != null) {
                    System.out.println("Client: " + user);
                    out.println(user);
                    System.out.println(in.readLine());
                }
            }
        } 
        catch (IOException ioe) {
		System.err.println(ioe);
	}
	finally {
		if (in != null)
			in.close();
		if (out != null)
			out.close();
		if (sock != null)
			sock.close();
	}
    }
}