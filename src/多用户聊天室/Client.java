package 多用户聊天室;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    private String message = null;
    private String name;
    private Socket socket;
    private PrintWriter pw;
    private BufferedReader br;
    private Scanner input = new Scanner(System.in);

    public Client() {
        try {
            socket = new Socket("localhost",12345);
        } catch (IOException e) {
            System.out.println("连接服务器失败");
            System.exit(-1);
        }
        try {
            pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("请输入您的昵称:");
        name = input.nextLine();
        send(name);
        new Thread(new ReceiveThread(br)).start();
    }

    @Override
    public void run() {
        while (true) {
            send();
        }
    }

    private void send(){
            send(name + " :" + input.nextLine());
    }

    private void send(String message){
        pw.println(message);
        pw.flush();
    }

    public static void main(String[] args) {
        new Thread(new Client()).start();
    }

}

class ReceiveThread implements Runnable {

    private String meg = null;
    private BufferedReader br;

    public ReceiveThread(BufferedReader br) { this.br = br; }

    @Override
    public void run() {
        while(true){
            receive();
        }
    }

    private void receive(){
        try {
            meg = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(meg);
    }

}
