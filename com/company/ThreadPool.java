package com.company;

import java.util.LinkedList;

public class ThreadPool extends ThreadGroup {
    private LinkedList<Runnable> workQueue;
    private boolean isClosed = false;
    private static int threadPoolID;
    private int threadID;
    private static final int maxPoolSize = 200;
    private static final int minPoolSize = 5;

    public static void main(String[] args) {
        ThreadPool tp = new ThreadPool(10);
        for(int i = 0;i<10;i++)
            tp.execute(()->{
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
    }

    public ThreadPool(int poolSize) {
        super("ThreadPool-" + (threadPoolID++));
        if(poolSize>maxPoolSize) poolSize = maxPoolSize;
        if(poolSize<minPoolSize) poolSize = minPoolSize;
        setDaemon(true);
        workQueue = new LinkedList<Runnable>();
        for (int i = 0; i < poolSize; i++)
            new WorkThread().start();
    }

    public synchronized void execute(Runnable task) {
        if (isClosed) {
            throw new IllegalStateException();
        }
        if (task != null) {
            workQueue.add(task);
            notify();
        }
    }

    protected synchronized Runnable getTask() throws InterruptedException {
        while (workQueue.size() == 0) {
            if (isClosed) return null;
            wait();
        }
        return workQueue.removeFirst();
    }

    public synchronized void close() {
        if (!isClosed) {
            isClosed = true;
            workQueue.clear();
            interrupt();
        }
    }

    public void join() {
        synchronized (this) {
            isClosed = true;
            notifyAll();
        }

        Thread[] threads = new Thread[activeCount()];

        int count = enumerate(threads);
        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private class WorkThread extends Thread {
        public WorkThread() {
            super(ThreadPool.this, "WorkThread-" + (threadID++));
        }

        public void run() {
            while (!isInterrupted()) {
                Runnable task = null;
                try {
                    task = getTask();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if (task == null) return;
                try {
                    task.run();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }
}
