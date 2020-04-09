package com.company;

import java.util.Random;

public class Test {

    private static double serialSum(double[] arr, int begin, int end){
        double sum = 0;
        for(int i = begin;i<end;i++) sum += arr[i];
        return sum;
    }

    public static void main(String[] args) {
        double[] nums = new double[100000000];
        Random random = new Random();
        for(int i = 0;i<nums.length;i++){
            nums[i] = random.nextInt(10000);
        }

        ParallelSum summer = new ParallelSum(4);

        long parallelTime = 0, serialTime = 0;
        long beginTime, endTime;
        beginTime = System.currentTimeMillis();
        System.out.println("sum = "+summer.sum(nums,0,nums.length,20000000));
        endTime = System.currentTimeMillis();
        System.out.println("parallel time: " + (parallelTime = endTime - beginTime)+"ms");

        summer.close();

        beginTime = System.currentTimeMillis();
        System.out.println("sum = "+serialSum(nums,0,nums.length));
        endTime = System.currentTimeMillis();
        System.out.println("serialSum time: " + (serialTime = endTime - beginTime)+"ms");

        System.out.println("ratio: " + serialTime*1.0/parallelTime);
    }
}
