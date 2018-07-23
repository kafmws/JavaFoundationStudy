package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Flower extends Plant {

    private String flowerColor;

    public Flower(String color) {
        setColor(color);
        flowerColor = color;
    }

    public Flower(String color, String flowerColor){
        setColor(color);
        this.flowerColor = flowerColor;
    }

    public void setFlowerColor(String flowerColor) {
        this.flowerColor = flowerColor;
    }

    public String getFlowerColor(){
        return flowerColor;
    }

    @Override
    public Flower birth() {
        int flag = 1;
        ArrayList<String> hs = new ArrayList<>();
        hs.add("red");
        hs.add("white");
        hs.add("yellow");
        hs.add("pink");
        hs.add("blue");
        hs.add("purple");
        for(String s :hs){
            if(s.equals(this.getColor())==true){
                flag = 0;break;
            }
        }
        if(flag == 1){hs.add(this.getColor());}
        String [] s = new String[hs.size()];
        hs.toArray(s);
        return new Flower(this.getColor(),s[(int)(Math.random()*10)%s.length]);
    }

    @Override
    public ArrayList<Creature> newBirth() {
        int n = getBirthNum();
        ArrayList<Creature> ac = new ArrayList<>();
        while(n--!=0){
            ac.add(birth());
        }
        return ac;
    }

    @Override
    public String description() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("This is a %s Plant with %s flower",getColor(),getFlowerColor()));
        return  sb.toString();
    }

}
