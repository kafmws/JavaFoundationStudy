package com.company;

import java.io.*;

public class TXTWordCounter {

    private ThreadPool tp = null;
    private int poolSize;
    private File file;
    static String filename = "D:\\PDF\\红楼梦.txt";

    public TXTWordCounter(int poolSize) {
        this.poolSize = poolSize;
        tp = new ThreadPool(poolSize);
    }

    private void analyze(String filename, int taskSize) {
        file = new File(filename);

        long readBegin = 0, readEnd = 0;

        readBegin = System.currentTimeMillis();

        int fileLen = (int)file.length();
        if(taskSize>fileLen) taskSize = fileLen;
        int size = fileLen / taskSize + 1;
        int finalTaskSize = taskSize;
        int dai = 0, bao = 0;
        int[] dais = new int[size], baos = new int[size];
        for (int i = 0; i < size; i++) {
            int index = i;
            tp.execute(() -> {
                try {
                    byte[] bytes = new byte[finalTaskSize + 2];
                    RandomAccessFile f = new RandomAccessFile(file, "r");
                    f.seek(index * finalTaskSize);
                    f.read(bytes);
                    String s = new String(bytes);
                    int sub = 0;
                    while ((sub = s.indexOf("黛玉",sub))!=-1){
                        dais[index]++;
                        sub++;
                    }
                    sub = 0;
                    while ((sub = s.indexOf("宝玉",sub))!=-1){
                        baos[index]++;
                        sub++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        tp.join();

        for(int i : dais)
            dai += i;
        for(int i : baos)
            bao += i;
        readEnd = System.currentTimeMillis();
        System.out.println("ParallelRead: "+(readEnd-readBegin)+"ms");
        System.out.println("ParallelSearch:\n"+"\t黛玉:"+dai+"\t宝玉:"+bao);

    }

    private void close() {
        if (tp != null)
            tp.close();
    }

    private static void serialCount(String filename){
        String content = null;
        try {
            RandomAccessFile file = new RandomAccessFile(new File(filename),"r");
            byte[] bytes = new byte[(int) file.length()];
            long begin = System.currentTimeMillis();
            file.readFully(bytes);
            long end = System.currentTimeMillis();
            System.out.println("SerialRead: "+(end-begin)+"ms");
            content = new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int index = 0, dai = 0, bao = 0;
        while ((index = content.indexOf("黛玉",index))!=-1){
            dai++;
            index++;
        }
        index = 0;
        while ((index = content.indexOf("宝玉",index))!=-1){
            bao++;
            index++;
        }
        System.out.println("SerialSearch:\n"+"\t黛玉:"+dai+"\t宝玉:"+bao);
    }

    public static void main(String[] args) {

        TXTWordCounter analyzer = new TXTWordCounter(5);

        long parallelTime = 0, serialTime = 0;
        long beginTime, endTime;
        beginTime = System.currentTimeMillis();
        analyzer.analyze(filename, 2000000);//
        endTime = System.currentTimeMillis();
        System.out.println("parallel time: " + (parallelTime = endTime - beginTime)+"ms");

        analyzer.close();

        beginTime = System.currentTimeMillis();
        serialCount(filename);
        endTime = System.currentTimeMillis();
        System.out.println("serialSum time: " + (serialTime = endTime - beginTime)+"ms");

        System.out.println("ratio: " + serialTime*1.0/parallelTime);
    }
}

//    private void analyze(String filename, int taskSize) {
//        file = new File(filename);
//        if(file.length()>Integer.MAX_VALUE){
//            System.out.println("文件太大");
//            System.exit(-1);
//        }
//
//        long readBegin = 0, readEnd = 0;
//
//        readBegin = System.currentTimeMillis();
//
//        int fileLen = (int)file.length();
//        byte[] bytes = new byte[fileLen];
//        if(taskSize>fileLen) taskSize = fileLen;
//        int size = fileLen / taskSize;
//        int finalTaskSize = taskSize;
//        for (int i = 0; i < size; i++) {
//            int index = i;
//            tp.execute(() -> {
//                try {
//                    RandomAccessFile f = new RandomAccessFile(file, "r");
//                    f.seek(index * finalTaskSize);
//                    f.read(bytes,index * finalTaskSize, finalTaskSize);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//        tp.join();
//
//        readEnd = System.currentTimeMillis();
//        System.out.println("ParallelRead: "+(readEnd-readBegin)+"ms");
//
//        txt = new String(bytes);
//        int index = 0, dai = 0, bao = 0;
////        while ((index = txt.indexOf("黛玉",index))!=-1){
////            dai++;
////            index++;
////        }
////        index = 0;
////        while ((index = txt.indexOf("宝玉",index))!=-1){
////            bao++;
////            index++;
////        }
//        int[] dais = new int[5], baos = new int[5];
//        {
//            int taskNum = 5;
//            taskSize = txt.length()/ taskNum;
//            if((taskSize & 1) == 1)taskSize++;
//            int finalSize = taskSize;
//            int[] cnt = new int[taskNum];
//            for (int i = 0; i < taskNum; i++) {
//                int begin = i * taskSize;
//                int finalI = i;
//                tp.execute(() -> {
//                    int end = begin + finalSize + 2;//目标串长度减半再乘以2
//                    int sub = begin;
//                    while ((sub = txt.indexOf("黛玉",sub))<end&&sub!=-1){
//                        dais[finalI]++;
//                        sub++;
//                    }
//                    sub = 0;
//                    while ((sub = txt.indexOf("宝玉",sub))!=-1){
//                        baos[finalI]++;
//                        sub++;
//                    }
//                });
//            }
//            tp.join();
//        }
//        System.out.println("ParallelSearch:\n"+"\t黛玉:"+dai+"\t宝玉:"+bao);
//
//    }