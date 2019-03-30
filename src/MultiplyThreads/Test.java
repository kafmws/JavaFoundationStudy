package MultiplyThreads;

public class Test {

    public static void main(String[] args) {
        new Thread(new Increase("111")).start();
        new Thread(new Increase("222")).start();
        new Thread(new Increase("333")).start();
    }
}

class Increase implements Runnable {

    private String name;
    private static int i = 0;

    public Increase(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Increase.class){
                i++;
                System.out.println(name + " : " + i);
            }
        }
    }
}