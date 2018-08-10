package com.company;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{


    private Socket socket = null;
    private Scanner in = new Scanner(System.in);

    @Override
    public void run() {
        try {
            socket = new Socket("localhost",12345);
            BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            Scanner in = new Scanner(System.in);
            while (true){
                String message = in.next();
                bw.write(message);
                bw.write("\r\n");
                bw.flush();
                if(message.equals("886")||message.equals("再见"))break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() throws IOException {

    }

}
