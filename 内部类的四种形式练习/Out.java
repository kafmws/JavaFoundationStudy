package com.company;
//静态内部类
public class Out {
    private int out = 10;

    static class In{
        private int inner = 5;
        private static int in = 6;

        public void setIn(int num){
            in = num;
        }
        public static int getIn(){
            return in;
        }
        public static  int getInner(){
            return new In().inner;
        }
    }
}
