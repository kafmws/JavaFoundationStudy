package 多用户聊天室;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server implements Runnable {

    private boolean exit = true;
    private static Map<String, Socket> users = new HashMap<>();
    private static List<PrintWriter> pws = new ArrayList<>();

    private BufferedReader bufferedReader;

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(12345);
        } catch (IOException e) {
            System.out.println("服务器开启失败");
            System.exit(-1);
        }
        while (true) {
            Socket listener = null;
            String name = null;
            BufferedReader br = null;
            try {
                listener = server.accept();
                br = new BufferedReader(new InputStreamReader(listener.getInputStream()));
                name = br.readLine();
                pws.add(new PrintWriter(new OutputStreamWriter(listener.getOutputStream())));
            } catch (IOException e) {
                e.printStackTrace();
            }
            users.put(name, listener);
            System.out.printf(name+"加入了聊天室,目前共%d人\n",users.size());
            new Thread(new Server(br)).start();
        }
    }

    @Override
    public void run() {
        String message = null;
        while (true&&exit){
            try {
                message = bufferedReader.readLine();
            } catch (IOException e) {
                exit = false;

                e.printStackTrace();
            }
            for (PrintWriter pw : pws){
                pw.println(message);
                pw.flush();
            }
        }
    }

    public Server(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }
}
