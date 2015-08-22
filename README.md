# Brute Force Cracking
[NWEN303] Implement Brute Force Cracking of Keys by using "bag of tasks" architecture in Java

## Goals:
This program is implement and document a parallel shared memory program that does a brute force search for a cryptographic key. And it is based on the RSA secret-key challenge.

## Requirements:
The implementation will use a “bag of tasks” architecture. There will be a single key manager and a dynamic set of clients.

The system should meet the following minimum requirements:

1. Clients only need to be aware of the location of the key manager.
 
2. Clients can join or leave but will complete the work they have been requested.

3. Clients request work from the key manager and return results to it.

4. Connections between clients and the Masters only exist long enough to request work or to return results.

5. When the key is found, the key manager will shutdown.

It will implement two programs (KeyManager and Client) and evaluate their performance.

## Run it:
###First, you need compile the java program with following command:
$ javac KKJServer.java

$ javac KKJClient.java

###Then, you need running server first with following command:
$ java KKJServer

###Next, you can running client program with:
$ java KKJClient <IP Address> <Port Number>

###We can use Search.java to implement key searching function:
$ javac Search.java

$ java Search <key> <key size> <max key size> <ciphertext>

For example: 
$ java Search 158396074479790 6 15 uOHYfhAZvCUoLULdDmdLQWuRJGXPgbWJzeVIvzi0k5nYGRhWeyiFfA==

###For KeyManager.java and Worker.java

$ javac KeyManager.java
$ javac Worker.java

$ java KeyManager <port number> <initial-key> <keysize> <ciphertext>

$ java Worker <host name> <port number> <chunksize>
