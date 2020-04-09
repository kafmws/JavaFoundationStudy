package chatroom;

import javax.json.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.stream.Stream;

public class Server {
    private static final int port = 12345;
    private static String sentenceFile = "sentence.dat";

    private static List<ServerThread> clientList = new ArrayList<>();


    //particular
    static Map<String, String> sentences = new HashMap<>(900);

    public static void main(String[] args) {
        try {
            loadSentence();
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket client = serverSocket.accept();
                clientList.add(new ServerThread(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadSentence() {
        try {
            LineNumberReader lineNumberReader =
                    new LineNumberReader(new FileReader(sentenceFile));
            String line = null;
            while ((line = lineNumberReader.readLine()) != null) {
                sentences.put(String.valueOf(lineNumberReader.getLineNumber()), line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerThread {

    @FunctionalInterface
    private interface Action {
        void action(String content);
    }

    private static Map<String, Action> actionMap = new HashMap<>();
    private static final int bufferSize = 10000;

    private Socket clientSocket;
    private BufferedReader in;
    private PrintWriter out;

    String req;
    JsonObjectBuilder resp;
    JsonBuilderFactory factory = Json.createBuilderFactory(null);

     {
        actionMap.put("sentence", (String content) -> {
            JsonArrayBuilder resArray = factory.createArrayBuilder();
            Stream.of(content.split(","))
//                    .mapToInt(Integer::parseInt)
//                    .filter(i->i>0&&i<Server.sentences.size())
                    .forEach(lineNo -> {
                        String line = Server.sentences.get(lineNo);
//                        if(line==null) line = JsonValue.NULL;
//                            resArray.add();
//                        else resArray.add(Json.createObjectBuilder().add());
                    });
//            resp
        });
    }

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());
            new Thread(() -> {
                while (true) {
                    try {
                        req = in.readLine();
                        parse();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parse() {
        int colon = req.indexOf(':');
        String action = req.substring(0, colon);
        String content = req.substring(colon + 1);
        service(action, content);
    }

    private void service(String action, String content) {
        resp = Json.createObjectBuilder();
        actionMap.getOrDefault(action,
                (String c) -> {
//                    resp.append("unknown action :");
//                    resp.
                }
        ).action(content);
        resp.add("status", "ok");
//        out.print();
        out.flush();
    }
}