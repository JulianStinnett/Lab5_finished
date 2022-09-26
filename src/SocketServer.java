import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer implements Runnable {

    private int thisServerPort;

    /**
     * This constructor forces port to be passed in that is necessary for serverSocket startup.
     */
    //constructor below.

    public SocketServer(int iPort){
        thisServerPort = iPort;
    }
    /**
     * This thread listens for connecting clients and receives messages.
     */
    public void run() {

        try(ServerSocket oServerSocket = new ServerSocket(thisServerPort)){
            System.out.println("Server is listening on port " + thisServerPort);

            while(true){

                //Waiting for client to connect to server.
                Socket oSocket = oServerSocket.accept();
                System.out.println("[server] New client connected: " + oSocket.getRemoteSocketAddress());

                //setup a reader
                InputStream input = oSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                //setup writer
                OutputStream output = oSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                //get message from client.
                String sReceivedMessage = reader.readLine();
                String[] numS = sReceivedMessage.split(",");
                int a = Integer.parseInt(numS[0]);
                int b = Integer.parseInt(numS[1]);
                int c = Integer.parseInt(numS[2]);
                System.out.println("[server] message received from client: " + sReceivedMessage);

                // Send reply back to client.
                writer.println("Server received your message: " + (a + b + c));
                writer.flush();
            }
        }
        catch(IOException ex){
            System.out.println("[server] Server exception: " + ex.getMessage());
            ex.printStackTrace();

        }
    }
}