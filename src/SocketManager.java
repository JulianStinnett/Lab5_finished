import java.util.Scanner;

public class SocketManager {
    public static void main(String[] args) {

        // Get port for this server to listen on.
        System.out.print("Enter port for this server to listen on: ");
        int iPort = new Scanner(System.in).nextInt();

        // Get IP address of server to connect to.
        System.out.print("Enter IP address of server to connect to: ");
        String sOtherServerIp = new Scanner(System.in).nextLine();

        // Get port for the other server.
        System.out.print("Enter port of server to connect to: ");
        int iOtherServerPort = new Scanner(System.in).nextInt();


        SocketServer oServer = new SocketServer(iPort);
        Thread oServerThread = new Thread(oServer);
        oServerThread.start();

        while(true){

            //get message to send.
            System.out.print("Enter three numbers seperated by commas: ");
            String sMessage = new Scanner(System.in).nextLine();
            SocketClient oClient = new SocketClient();
            String sReceivedMessage =
                    oClient.connectForOneMessage(sOtherServerIp, iOtherServerPort, sMessage);
            System.out.println("[client] reply from server: " + sReceivedMessage);

        }

    }
}