package ChatRoomServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class Server implements Runnable {
    private static Collection<Server> manager = new ArrayList<>();
    private Socket listener;
    private BufferedReader br;
    private PrintWriter pw;
    private String message;

    public Server(Socket listener) throws IOException {
        this.listener = listener;
        br = new BufferedReader(new InputStreamReader(listener.getInputStream()));
        pw = new PrintWriter(new OutputStreamWriter(listener.getOutputStream()));
        manager.add(this);
    }

    @Override
    public void run() {
        while(true){
            try {
                message = br.readLine();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
//                try {
//                    br.close();
//                    for(Server server : manager){
//                        if(server.br == br){
//                            manager.remove(server);
//                            System.out.printf("当前%d人在线\n",manager.size()+1);
//                            break;
//                        }
//                    }
//                }
// catch (IOException e1) {
//                    e1.printStackTrace();
//                }
            }
            transmint(message);
        }
    }

    private void transmint(String message){
        for(Server server : manager){
            server.pw.println(message);
            server.pw.flush();
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(12345);
            while (true){
                Socket listener = server.accept();
                System.out.printf("当前%d人在线\n",manager.size()+1);
                new Thread(new Server(listener)).start();
            }
        } catch (IOException e) {
            System.out.println("启动失败");
        }
    }

}
