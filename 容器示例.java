import java.util.*;
import java.lang.*;

public class Notebook {
    private ArrayList<String> note = new ArrayList<String>();

    public void add(String s){
        note.add(s);
    }

    public void add(String s,int index){
        note.add(index,s);
    }

    public String getindex(int index){
        return note.get(index);
    }

    public void remove(int index){
        note.remove(index);
    }

    public void remove(String s){
        for (int i = 0; i<note.size(); i++) {
            if (note.get(i).equals(s)) {
                note.remove(s);
                break;
            }
        }
    }

    public void show(){
        for (String s:note) {
            System.out.print(s+" ");
        }
    }

    public String [] toArray(){
        String [] s = new String[note.size()];
        note.toArray(s);
        return s;
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner in = new  Scanner(System.in);
        Notebook note = new Notebook();
        note.add("first");
        note.add("second");
        note.add("third");
        note.add("fourth");
        String s = in.next();
        note.add(s);
        s=null;
        note.show();
    }
}