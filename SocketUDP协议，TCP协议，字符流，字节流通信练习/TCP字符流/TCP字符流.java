package TCP字符流;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Client extends Thread {
    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1",12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner in =new Scanner(System.in);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true&&socket!=null) {
            String message = in.nextLine();
            try {
                bw.write(message);//
                bw.write("\r\n");
                bw.flush();
                if(message.equals("886"))break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class Server extends Thread {
    @Override
    public void run() {
        ServerSocket ss =null;
        try {
            ss = new ServerSocket(12345);
            Socket socket = ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message =null;
            while (true) {
                //System.out.println("come");
                if((message=br.readLine())!=null){
                System.out.println("from  "+socket.getInetAddress().getHostAddress()+":  "+message);
                if(message.equals("886"))break;}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



public class TCP字符流 {

    public static void main(String[] args) {
        Thread client = new Client();
        Thread server = new Server();
        server.setDaemon(true);
        server.start();
        client.start();
    }

}
