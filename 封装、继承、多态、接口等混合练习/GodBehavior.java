package com.company;

public abstract interface GodBehavior {
    //神言 所有类均为我的子类
    static abstract  class Description {

        private static String description(concept obj){
            String re = obj.description();
            return re;
        }

        public static void discribe(concept obj){
            if(obj == null){
                System.out.println("null");
            }
            System.out.println(description(obj));
        }
    }
}
