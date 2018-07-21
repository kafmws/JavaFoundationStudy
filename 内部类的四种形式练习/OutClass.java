package com.company;

//匿名内部类
public class OutClass {

    private int out;

    public int getOut(OutClass oc){
        return  oc.out;
    }


    public static void main(String[] args) {
        System.out.println(new OutClass(){
            @Override
            public int getOut(OutClass oc){
                return 10;
            }
        }.getOut(new OutClass()));
    }
}
