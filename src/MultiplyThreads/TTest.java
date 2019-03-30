package MultiplyThreads;

public class TTest {

    public static void main(String[] args) {
        new Thread(new IncreaseT("111")).start();
        new Thread(new IncreaseT("222")).start();
        new Thread(new IncreaseT("333")).start();
    }
}

class IncreaseT implements Runnable {

    private String name;
    private static volatile int i = 0;

    public IncreaseT(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
                i++;
                System.out.println(name + " : " + i);
        }
    }
}
