package com.company;
//成员内部类
public class OuterClass {

    private int out = 10;
    public static  final  int CONST = 5;

    private static  int getCONST(){
        return CONST;
    }

    class InnerClass{

        private int out=1;
        private static final int test = 5;

        public int getOut(){
            System.out.println(OuterClass.this.out);
            return out;
        }
        public int innerGetCONST(){
            return getCONST();
        }
    }
    InnerClass ic = new InnerClass();
    InnerClass p = this.new InnerClass();
}
