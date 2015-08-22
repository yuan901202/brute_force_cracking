NWEN 303 Project 2 - Task 1 ReadMe File
Student Name: Tianfu Yuan
Student ID: 300228072
Username: yuantian

The details please check report (NWEN303_P2_TianfuYuan.pdf).

*********************************************************
How to running KKJServer and KKJClient:

$ javac KKJServer.java
$ java KKJServer

$ javac KKJClient.java
$ java KKJClient <IP Address> <Port Number>

*********************************************************
Changes with KKJServer and KKJClient:

The KKJServer add following lines to the original program (EchoServer) (line 22):
    BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

The KKJClient add following lines to the original program (EchoClient) (line 27~38):
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
    
*********************************************************
How to running Search:

$ javac Search.java
$ java Search <key> <key size> <max key size> <ciphertext>

*********************************************************
Changes with Search:

int maxKeySize = Integer.parseInt(args[2]); (line 24)

for (int i=0; i<maxKeySize; i++) {} (line 31)

Thread.sleep(maxKeySize); (line 35)
