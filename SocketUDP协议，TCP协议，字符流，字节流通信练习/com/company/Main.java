package com.company;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        Thread sever = new Thread(new Sever());
        sever.setDaemon(true);
        sever.start();
//        int n =0;
//        while(n++<10000);
        Thread client = new Thread(new Client());
        client.start();
    }
}
