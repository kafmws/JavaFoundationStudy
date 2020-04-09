package com.company;

public class ParallelSum {

    private ThreadPool tp = null;
    private int poolSize;

    public ParallelSum(int poolSize){
        tp = new ThreadPool(poolSize);
        this.poolSize = poolSize;
    }

    public double sum(double[] arr, int begin, int end, int taskSize){//[,)
        int size = (end-begin)/taskSize;
        final int padding = 16;//false share
        double[] sum = new double[size*padding];
        for(int i = 0;i<size - 1;i++){
            int index = i*padding;
            int start = begin + i * taskSize, tail = start + taskSize;
            tp.execute(new Runnable() {
                @Override
                public void run() {
                    double localSum = 0;
                    for(int j = start;j < tail;j++){
                        localSum = localSum + arr[j];
                    }
                    sum[index] = localSum;
                }
            });
        }
        double allSum = 0;
        for(int i = begin+(size-1)*taskSize;i<end;i++){
            allSum = allSum + arr[i];
        }
        tp.join();
        for(int i = 0;i < size - 1;i++){
            allSum = allSum + sum[i*padding];
        }
        return allSum;
    }

    public void close(){
        if(tp!=null)
            tp.close();
    }
}
