package comcompany;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    @Override
    public void run() {

        try {
            main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void main() throws IOException {
        ServerSocket server = new ServerSocket(12345);
        int cnt = 0;
        Socket socket = server.accept();
        String message = null;
        PrintWriter send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while(true){
            message = receive.readLine();
            send.println("from  "+socket.getInetAddress().getHostAddress()+":   "+message);
            send.flush();
        }
    }
}
