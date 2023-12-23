import java.io.*;
import java.net.*;
import java.util.ArrayList;
/** this class is client handler which handle all the client in chat group and put them in a list.
* @author Zarifi
 **/

public class ClientHandler implements Runnable{

    public final String YELLOW = "\u001B[32m";
    public final String RESET_C = "\u001B[0m";
    public final String RED = "\u001B[31m";
    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;

    public ClientHandler(Socket socket) {
        try{
            this.socket=socket;
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader =new BufferedReader((new InputStreamReader(socket.getInputStream())));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMassage(YELLOW+clientUsername +" " + "has joined."+RESET_C);

        }catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
    String massageFromClient;
    while (socket.isConnected()){
        try {
            massageFromClient= bufferedReader.readLine();
            broadcastMassage(massageFromClient);
        }catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
            break;
        }
    }
    }
    public void broadcastMassage(String massageToSend){
        for(ClientHandler clientHandler: clientHandlers){
            try {
                if(!clientHandler.clientUsername.equals(clientUsername)){
                    clientHandler.bufferedWriter.write(massageToSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }
            }catch (IOException e){
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMassage(RED+clientUsername + " left the group"+RESET_C);

    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        removeClientHandler();
        try {
            if(bufferedReader !=null) {
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
