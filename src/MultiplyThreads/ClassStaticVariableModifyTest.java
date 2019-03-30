package MultiplyThreads;

/**
 * @author kafm
 * @apiNote 类变量多线程修改测试
 */

public class ClassStaticVariableModifyTest {
    private int i = 100;

    public synchronized int increase() {
        return ++i;
    }

    public synchronized void add(){
        System.out.println(Thread.currentThread() + " : " + ++i);
    }

    public static void main(String[] args) {
//        new Thread(new IncreaseThread("111")).start();
//        new Thread(new IncreaseThread("222")).start();
//        new Thread(new IncreaseThread("333")).start();
//        new Thread(new IncreaseThread("444")).start();
//        new Thread(new IncreaseThread("555")).start();
//        new Thread(new IncreaseThread("666")).start();
        ClassStaticVariableModifyTest test = new ClassStaticVariableModifyTest();
        for(int i = 0;i<10;i++)
            new Thread(() -> {
                while (true){
                    test.add();
                }
            }).start();
    }
}

class IncreaseThread implements Runnable {

    private String threadName;
    private static ClassStaticVariableModifyTest test = new ClassStaticVariableModifyTest();

    public IncreaseThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
            while (true) {
                synchronized (test){
                System.out.println(threadName + " : " + test.increase());}
            }
    }
}