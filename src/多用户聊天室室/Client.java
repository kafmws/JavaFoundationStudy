package 多用户聊天室室;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private static Scanner input = new Scanner(System.in);
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String name;
    private String message;


    public Client(String name, Socket socket) throws IOException {
        this.name = name;
        this.socket = socket;
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.println(name);
        out.flush();
        new Receive(this).start();
    }

    public static void main(String[] args) throws IOException {
        System.out.print("请输入昵称: ");
        String name = input.nextLine();
        Client client = new Client(name, new Socket("localhost", 12345));
        client.start();
    }

    @Override
    public void run() {
        sendMessage();
    }

    private void sendMessage() {
        while (true) {
            message = input.nextLine();
                out.println(name + ":  " + message);
//            out.println(message);
                out.flush();

        }
    }


    static class Receive extends Thread {

        private Client client;

        public Receive(Client client) {
            this.client = client;
        }

        private void receiveMessage() throws IOException {
            while (true) {
                String message = client.in.readLine();
                System.out.println(message);
            }
        }

        @Override
        public void run() {
            try {
                receiveMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
