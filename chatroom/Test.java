package chatroom;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("key1","val1");
        map.put("key2","val2");
        JsonBuilderFactory factory = Json.createBuilderFactory(map);
        System.out.println(factory.createObjectBuilder().build());
    }
}
