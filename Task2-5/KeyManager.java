//NWEN 303 Project 2 - Task 2-5
//Student Name: Tianfu Yuan
//Student ID: 300228072
//Username: yuantian
//Due Date: 19/10/2014 23:59

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Arrays;
import java.util.*;
import java.net.*;

public class KeyManager {
    
    private static BigInteger bi = null;
    private static int keySize = 4;
    private static byte[] ciphertext = null;
    
    public static void main (String[] args) throws IOException{
	ServerSocket sock = null;
	
	if (args.length < 4) {
            System.err.println("Usage: java KeyManager <port number> <initial-key> <keysize> <ciphertext>");
            System.exit(-1);
        }
        
        int portNumber = Integer.parseInt(args[0]); //portnumber
	bi = new BigInteger(args[1]); //initialkey
        keySize = Integer.parseInt(args[2]); //keysize
        byte[] key = Blowfish.asByteArray(bi, keySize);
        ciphertext = Blowfish.fromBase64(args[3]); //ciphertext
        
        //check initial key
        BigInteger bi = null;
        try {
            bi = new BigInteger(args[1]);
        } catch (NumberFormatException e) {
            System.err.println("Argument" + args[1] + " must be an integer.");
            System.exit(1);
        }

        //check key size
        int keySize = 4;
        try {
            keySize = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.err.println("Argument" + args[2] + " must be an integer.");
            System.exit(1);
        }

        //check ciphertext
        byte[] ciphertext = null;
        try {
            ciphertext = Blowfish.fromBase64(args[3]);
        } catch (IllegalArgumentException e) {
            System.err.println("Argument" + args[3] + " must be base64 format.");
            System.exit(1);
        }
        
	try {
	    sock = new ServerSocket(portNumber);
	    Socket clientSocket = sock.accept(); //accept the connection from worker
	    Connection c = new Connection(clientSocket);
	    c.start();
	}
	catch (IOException e) {
	    System.err.println(e);
	}
	finally {
	    if (sock != null) {
		sock.close();
	    }
	}
    }
    
    //pass initialkey to worker
    public static BigInteger initialkey() {
	return bi;
    }
    
    //pass keysize to worker
    public static int keySize() {
	return keySize;
    }
    
    //pass ciphertext to worker
    public static byte[] ciphertext() {
	return ciphertext;
    }
}
