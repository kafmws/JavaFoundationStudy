package 实验;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

public class Server extends Thread {

    private Socket listener;
    private PrintWriter out;
    private BufferedReader in;
    private String message;

    private Collection<Server> manager = new ArrayList<>();

    public Server(Socket socket) throws IOException {
        this.listener = socket;
        out = new PrintWriter(new OutputStreamWriter(listener.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(listener.getInputStream()));
        manager.add(this);
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        while (true) {
            Socket listener = serverSocket.accept();
            new Server(listener).start();
        }
    }

    @Override
    public void run() {
        try {
            transmit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void transmit() throws IOException {
        while (true) {
            message = in.readLine();
            System.out.println(message);
            for (Server server : manager) {
                server.out.println(message);
                server.out.flush();
            }
        }
    }


}
