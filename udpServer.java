import java.io.*;
import java.net.*;

public class udpServer
{
   public static void main(String args[]) throws Exception
      {
        if (args.length != 1) {
            System.err.println("ERROR, no port provided");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);
        DatagramSocket serverSocket = new DatagramSocket(portNumber);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        boolean running = true;
            while(running)
            {
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                  serverSocket.receive(receivePacket);
                  String sentence = new String( receivePacket.getData());
                  System.out.println("Here is the message: " + sentence);
                  InetAddress IPAddress = receivePacket.getAddress();
                  int port = receivePacket.getPort();
                  String mesgOut = "I got your message";
                  sendData = mesgOut.getBytes();
                  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                  serverSocket.send(sendPacket);
            }
      }
}