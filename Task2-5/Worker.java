//NWEN 303 Project 2 - Task 2-5
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian
//Due Date: 19/10/2014 23:59

import java.io.*;
import java.net.*;
import java.math.BigInteger;

public class Worker {
    public static void main(String[] args) throws IOException {
	if (args.length < 3) {
            System.err.println("Usage: java Worker <host name> <port number> <chunksize>");
            System.exit(-1);
        }
        
        KeyManager km = new KeyManager(); //call KeyManager to get the value from it
        
        String hostName = args[0]; //host name
        int portNumber = Integer.parseInt(args[1]); //port number
        int chunksize = Integer.parseInt(args[2]); //chunksize
        
        Socket socket = null;
        String plaintext = null;
        BufferedReader in = null;
	PrintWriter out = null;
	String keyStr = null;
	BigInteger bi = null;
	
        byte[] key = null;
        
        try {
	    socket = new Socket(hostName, portNumber);
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            bi = km.initialkey();
            
	    for (int i = 0; i < chunksize; i++) {
		if (bi != null) {
		    keyStr = bi.toString();
		}else {
		    System.out.println("Error NullPointers at initilakey!");
		}
		
		System.out.print(keyStr);
		//Thread.sleep(100);
		if (keyStr != null) {
		    for (int j = 0; j < keyStr.length(); j++) {
			System.out.print("\b");
		    }
		}
		
		//decrypt the ciphertext
		Blowfish.setKey(key);        
		plaintext = Blowfish.decryptToString(km.ciphertext());
		if (plaintext.equals("May good flourish; Kia hua ko te pai")) {
		    System.out.println("Plaintext found!");
		    System.out.println(plaintext);
		    System.out.println("key is (hex) " + Blowfish.toHex(key) + " " + bi);
		    System.exit(-1);
		} 
            
		// try the next key
		if (bi != null) {
		    bi = bi.add(BigInteger.ONE);
		    key = Blowfish.asByteArray(bi, km.keySize());
		} else {
		    System.out.println("Error NullPointers at initilakey!");
		}
	    }
	    System.out.println("No key found!");
        }
        catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        finally {
	    if (in != null) {
		in.close();
	    }
	    if (out != null) {
		out.close();
	    }
	    if (socket != null) {
		socket.close();
	    }
        }
    }
}
