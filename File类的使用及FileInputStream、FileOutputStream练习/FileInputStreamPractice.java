package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamPractice {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\HP\\Desktop\\program.txt");
        byte [] buffer = new byte[100];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            int len = fis.read(buffer);
            System.out.println(new String(buffer,0,len));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis!=null)
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
