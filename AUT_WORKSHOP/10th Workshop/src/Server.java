import java.io.*;
import java.net.Socket;
import java.net.*;

/** Server class is responsible fo listening to client
 * serverSocket is an object of socket
 * socket is for connecting the client and server
 * @author Zarifi
 **/
public class Server {
    /**
     * @param serverSocket is the object of ServerSocket it is responsible for listening
     *      for incoming connections for clients and creating a socket object to communicate.
     **/
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * this method is for running the server class
     * accept method is mean our program will helted until the client is connected.
     */
    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                System.out.println("Server is Listening . . .");
                Socket socket = serverSocket.accept();
                System.out.println("Connect Successfully !");
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket( 1234);
    Server server = new Server(serverSocket);
    server.startServer();
}


}


