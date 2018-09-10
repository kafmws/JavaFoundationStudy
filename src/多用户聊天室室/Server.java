package 多用户聊天室室;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class Server extends Thread {

    private static Collection<Server> manager = new ArrayList<>();
    private Socket listener;
    private PrintWriter out;
    private BufferedReader in;
    private String message;
    private String user;

    public Server(Socket socket) throws IOException {
        this.listener = socket;
        out = new PrintWriter(new OutputStreamWriter(listener.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(listener.getInputStream()));
        manager.add(this);
        user = in.readLine();
        transmit(user+"加入聊天室");
        transmit(String.format("当前%d人在线", manager.size()));
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12580);
        while (true) {
            Socket listener = serverSocket.accept();
            new Server(listener).start();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                message = in.readLine();
                if(message.indexOf('@')==-1)
                    transmit(message);
                else
                    transmitOnlyOne(message);
            } catch (IOException e) {
                for(Server server : manager){
                    if(server.in == in){
                        try {
                            server.listener.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        manager.remove(server);
                        transmit(String.format("当前%d人在线",manager.size()));
                    }
                }
            }
        }
    }



    private void transmit(String s){
        message = s;
        System.out.println(message);
        for (Server server : manager) {
            server.out.println(message);
            server.out.flush();
        }
    }

    private void transmitOnlyOne(String s){
        String name = s.substring(s.indexOf('@')+1,s.lastIndexOf(' ')).trim();
        System.out.println(message);
        for (Server server : manager) {
            if(server.user.equals(name)){
            server.out.println(message);
            server.out.flush();
            break;
            }
        }
    }

}
