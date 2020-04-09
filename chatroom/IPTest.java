package chatroom;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;

public class IPTest {
    public static void main(String[] args) {
        //112.32.228.166
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            ServerSocket serverSocket = new ServerSocket(4500);
            System.out.println(serverSocket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
