import java.util.ArrayList;

class Item {
    private String title;
    private int playingTime;
    private boolean gotIt = false;
    private String comment;

    public Item(){

    }

    public Item(String title, int playingTime, boolean gotIt, String comment) {
        this.title = title;
        this.playingTime = playingTime;
        this.gotIt = gotIt;
        this.comment = comment;
    }
    public void print(){
        System.out.print(title);
    }

    @Override
    public String toString() {
        return String.format("title:%s PlayingTime:%s comment:%s",this.title,this.playingTime,comment);
    }
}
class MP3 extends Item{
    private  String singer;

    MP3(String title, int playingTime, boolean gotIt, String comment,String singer){
        super(title,playingTime,gotIt,comment);
        this.singer=singer;
    }

    public void print(){
        System.out.print("MP3:");
        super.print();
        System.out.print(singer);
    }
    @Override
    public String toString() {
        String s="MP3:"+this.singer+super.toString();
        return s;
    }
}

public class Database {
    ArrayList<Item> listItem = new ArrayList<Item>();
    public void add(Item item){
        listItem.add(item);
    }

    public void list(){
        for (Item item : listItem) {
            item.print();
        }
    }

    public static void main(String[] args) {
        Database db =new Database();
        MP3 t;
        db.add(t = new MP3("Dangerous",6,false,"well","MJ"));
        System.out.print(t.toString());
    }
}
