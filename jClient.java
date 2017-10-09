import java.io.*;
import java.net.*;
import java.util.Scanner;

public class jClient {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try(
            Socket clientSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            Scanner sc = new Scanner(System.in);

        ) {
            System.out.println("Please enter your message:");
            String mesg = sc.nextLine();
            out.println(mesg);
            System.out.println(in.readLine());
            try{
                in.close();
                out.close();
                clientSocket.close();
            }catch(IOException e){
                System.out.println(e);
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }

       
        
    }
}