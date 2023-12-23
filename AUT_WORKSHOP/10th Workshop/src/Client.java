
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username){
        try {
            this.socket=socket;
            this.bufferedWriter= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader =new BufferedReader((new InputStreamReader(socket.getInputStream())));
            this.username=username;
        }catch (IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
    public  void sendMassage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String massageToSend = scanner.nextLine();
                bufferedWriter.write(username + ": " + massageToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }
        public void listenForMassage(){
    new Thread(new Runnable() {
        @Override
        public void run() {
            String msgFromGroupChat;
            while(socket.isConnected()){
                try {
                    msgFromGroupChat= bufferedReader.readLine();
                    System.out.println(msgFromGroupChat);
                }catch (IOException e){
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }

        }
    }).start();

    }
    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if(bufferedReader !=null) {
                bufferedReader.close();
            }
            if(bufferedWriter != null){
                bufferedWriter.close();
            }
            if(socket != null){
                socket.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username=scanner.nextLine();
        Socket socket=new Socket( "LocalHost",1234);
        Client client=new Client(socket, username);
        client.listenForMassage();
        client.sendMassage();
    }
}
