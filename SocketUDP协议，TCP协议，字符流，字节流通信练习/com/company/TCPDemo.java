package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class Clientt{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",12345);
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedOutputStream bw = new BufferedOutputStream(socket.getOutputStream());
        Scanner in = new Scanner(System.in);
        while (true){
            String message =in.nextLine();
            bw.write(message.getBytes());
            bw.flush();
            if(message.equals("再见")||message.equals("886"))break;
        }
        in.close();
        bw.close();
        socket.close();
    }
}

class Severe{
    public static void main(String[] args) throws IOException {
        ServerSocket sever = new ServerSocket(12345);
        Socket socket = sever.accept();
//            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedInputStream br = new BufferedInputStream(socket.getInputStream());
        while (true){
            //String message = br.readLine();
            byte [] buf = new byte[10000];
            int length = br.read(buf);
            String message = new String(buf,0,length);
            System.out.println("from  "+ socket.getInetAddress().getHostAddress() + ":  " +message);
            if(message.equals("再见")||message.equals("886"))break;
        }
        //sever.close();
    }
}

public class TCPDemo {

}
