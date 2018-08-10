package comcompany;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {

    @Override
    public void run() {

        try {
            main();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void main() throws IOException {
        Socket socket = new Socket("localhost",12345);
        PrintWriter send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
        BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        Scanner input = new Scanner(System.in);
        String message =null;
        while(true){
            message = input.nextLine();
            send.println(message);
            send.flush();
            message = receive.readLine();
            System.out.println(message);
        }
    }
}
