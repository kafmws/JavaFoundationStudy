package com.company;

public class Main {

    public static void main(String[] args) {
        //内部类对象创建及调用方式
        OuterClass oc = new OuterClass();
        OuterClass.InnerClass ic = oc.new InnerClass();
        System.out.println(ic.getOut());
        //内部类对象创建及调用方式2
        OuterClass.InnerClass oi = new OuterClass().new InnerClass();
        System.out.println(oi.getOut());
        //静态内部类
        Out out = new Out();
        Out.In in = new Out.In();
    }
}
