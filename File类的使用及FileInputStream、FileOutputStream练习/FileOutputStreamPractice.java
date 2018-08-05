package com.company;

import java.io.*;

public class FileOutputStreamPractice {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\HP\\Desktop\\program.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            String hello = "hello world";
            try {
                fos.write(hello.getBytes(), 0, hello.length());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}