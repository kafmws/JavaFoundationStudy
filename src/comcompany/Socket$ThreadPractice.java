package comcompany;

public class Socket$ThreadPractice {
    public static void main(String[] args) {

    }
}

class ServerTest{

    public static void main(String[] args) {
        Thread server = new Server();
        //server.setDaemon(true);
        server.start();
    }
}

class Client1 {
    public static void main(String[] args) {

        Thread client = new Client();
        client.start();
    }
}

class Client2 {
    public static void main(String[] args) {

        Thread client = new Client();
        client.start();
    }
}