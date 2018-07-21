package com.company;
//局部内部类
public class Outer {

    private int out;

    public void print(){
        class Inner{

            private int in;

            private final int tset=3;

            private int getIn(){
                return in;
            }
        }
        System.out.println(new Inner().getIn());
    }
}