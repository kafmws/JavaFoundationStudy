import java.lang.*;
import java.util.*;

class Display {
    private int num=0;
    private int limit=0;

    public Display(int limit) {
        this.limit = limit;
    }
    public void increase(){
        num++;
        if(num==limit) num = 0;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

class Clock {
    private Display hour = new Display(24);
    private Display min = new Display(60);
    private Display sec = new Display(60);

    public Clock(int hour, int min, int sec) {
        int t=sec/60;
        sec%=60;
        min+=t;
        t = min/60;
        min%=60;
        hour+=t;
        hour%=24;
        this.hour.setNum(hour);
        this.min.setNum(min);
        this.sec.setNum(sec);
    }

    public void tick() {
        sec.increase();
        if (sec.getNum() == 0){ min.increase();
        if (min.getNum() == 0) hour.increase();
        }
    }

    @Override
    public String toString() {
        String format = String.format("%02d:%02d:%02d", hour.getNum(), min.getNum(), sec.getNum());
        return format;
    }

}
public class Main {
    public static void main(String[] args) {

        java.util.Scanner in = new java.util.Scanner(System.in);

        Clock clock = new Clock(in.nextInt(), in.nextInt(), in.nextInt());

        clock.tick();

        System.out.println(clock);

        in.close();

    }
}