package com.company;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UDPDemo {

    public static void main(String[] args) {
        Thread send = new Send();
        Thread recive = new Recive();
        recive.setDaemon(true);
        recive.start();
        send.start();
    }

}

class Send extends Thread{
    @Override
    public void run() {
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true){
            Scanner in = new Scanner(System.in);
            String message = in.nextLine();
            byte [] buffer = message.getBytes();
            DatagramPacket dp = null;
            try {
                dp = new DatagramPacket(buffer,buffer.length,InetAddress.getByName("localhost"),12345);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
            try {
                ds.send(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(message.equals("再见")||message.equals("886")){
                break;
            }
        }
        ds.close();
    }
}

class Recive extends Thread{

    @Override
    public void run(){
        DatagramSocket ds = null;
        try {
            ds = new DatagramSocket(12345);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        byte [] content = new byte[1024*64];
        DatagramPacket dp = new DatagramPacket(content,1024*64);
        while (true){
            try {
                ds.receive(dp);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("from "+ dp.getAddress().getHostAddress() +":  "+ new String(dp.getData(),0,dp.getLength()));
        }
    }
}
