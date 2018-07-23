package com.company;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//            concept thing = new Dog("happy", sexType.male, 4);
//            GodBehavior.Description.discribe(thing);
//            Dog happy = (Dog) thing;
//            Dog happy2;
//            do {
//                happy2 = happy.birth();
//            } while (happy2 == null);
//            GodBehavior.Description.discribe(happy2);
//            thing = new Earthworm("earth",1);
//            GodBehavior.Description.discribe(((Earthworm) thing).birth());
//            Plant p = new Flower("gold");
//            GodBehavior.Description.discribe(p.birth());
                Flower flower = new Flower("gold");
                flower.setBirthNum(4);
                Flower [] flowers = new Flower[4];
                flower.newBirth().toArray(flowers);
                for(Flower f : flowers){
                    GodBehavior.Description.discribe(f);
                }
                Tree tree = new Tree(8,3);
                GodBehavior.Description.discribe(tree);
                Tree [] trees = new Tree[3];
                tree.newBirth().toArray(trees);
                for(Tree t : trees){
                    GodBehavior.Description.discribe(t);
                }
    }
}