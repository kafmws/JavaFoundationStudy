package com.company;

import java.util.Random;

public class QSort{

    static int[] getTestCase(int m) {
        Random random = new Random();
        int[] nums = new int[m];
        int bound = 10 * m + 1;
        for (int i = 0; i < m; i++) {
            nums[i] = random.nextInt(bound);
        }
        return nums;
    }

    static void partition(int[] arr, int begin, int end){
        if(begin==end)return;
        if(begin+99999>end){
            serialSortPartition(arr,begin,end);
            return;
        }
        int left = begin + 1, right = end, part = arr[begin], t;
        while (left<right) {
            while (left < right && arr[left] <= part) left++;
            while (right > left && arr[right] > part) right--;
            if (left>=right)break;
            t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;
        }
        if(arr[left]>=part){left--;}
        t = arr[left];arr[left] = part;arr[begin] = t;
        int finalLeft = left;
        Thread tl = new Thread(()->partition(arr,begin, finalLeft -1));
        Thread tr = new Thread(()->partition(arr,finalLeft+1,end));
        tl.start();
        tr.start();
        try {
            tl.join();
            tr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void serialSortPartition(int[] arr, int begin, int end){
        if(begin>=end)return;
        int left = begin + 1, right = end, part = arr[begin], t;
        while (left<right) {
            while (left < right && arr[left] <= part) left++;
            while (right > left && arr[right] > part) right--;
            if (left>=right)break;
            t = arr[left];
            arr[left] = arr[right];
            arr[right] = t;
        }
        if(arr[left]>=part){left--;}
        t = arr[left];arr[left] = part;arr[begin] = t;
        int finalLeft = left;
        serialSortPartition(arr,begin, finalLeft -1);
        serialSortPartition(arr,finalLeft+1,end);
    }
}

class QSortTest{
    public static void main(String[] args) {
        int arrLen = 10000000;
        int[] arr = QSort.getTestCase(arrLen);
        int[] array = new int[arrLen];
        System.arraycopy(arr, 0, array, 0, arrLen);

        long QSortBegin, QSortEnd;
        QSortBegin = System.currentTimeMillis();
        QSort.partition(arr,0,arr.length-1);
        QSortEnd = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("QSort: "+(QSortEnd-QSortBegin)+"ms");

        long sortBegin, sortEnd;
        sortBegin = System.currentTimeMillis();
        QSort.serialSortPartition(array,0,array.length-1);
        sortEnd = System.currentTimeMillis();
        System.out.println("serialQSort: "+(sortEnd-sortBegin)+"ms");

        System.out.println("ratio: "+(sortEnd-sortBegin)*1.0/(QSortEnd-QSortBegin));
    }
}