import java.net.*;
import java.io.*;

public class jServer{
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("ERROR, no port provided");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;
        try(
            // ServerSocket(port,backlog);
            //backlog = queue
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ){
            String inFromClient = in.readLine();
            if( inFromClient != null){
                System.out.println("Here is the message: "+ inFromClient );
                out.println("I got your message");
            }
            else{
                System.out.println("ERROR writing to socket");
            }
            //close
            try{
                in.close();
                out.close();
                clientSocket.close();
                serverSocket.close();
            }catch(IOException e){
                System.out.println(e);
            }
        }catch(IOException e){
            System.out.println(e);
        }
        

    }
}