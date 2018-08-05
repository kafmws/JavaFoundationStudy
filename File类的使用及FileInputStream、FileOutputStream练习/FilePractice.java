package com.company;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class FilePractice {

    //递归删除目录下所有文件及目录
    private static void delete(File file){
        File [] files;
        if(file.isFile()){
            if(file.delete())
                System.out.println(file.getAbsolutePath());
        }else if(file.isDirectory()){
            files = file.listFiles();
            for(File f : files){
                delete(f);
            }
            if(file.delete())
                System.out.println(file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
	        File [] files = gotFiles(new File("D:\\Java视频\\day19   集合\\avi"));
	        for(File f : files){
	            System.out.println(f);
            }
    }

    private static File [] getFiles(File file){
        File [] files = null;
        if(file.isFile())return files;
        if(file.isDirectory()){
            files = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return new File(dir,name).isFile()&&name.endsWith(".avi");
                }
            });
        }
        return files;
    }

    private static File [] gotFiles(File file){
        File [] files = null;
        if(file.isFile())return files;
        if(file.isDirectory()){
            //files = file.listFiles((dir)->dir.isDirectory()&&dir.getName().endsWith(".jpg"));
            files = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    System.out.println(pathname);
                    return false;
                }
            });
        }
        return files;
    }


}
