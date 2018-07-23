package com.company;

import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;

public class Tree extends Plant {
    private int leavesNum;

    public Tree(int leavesNum, int weedNum){
        this.leavesNum = leavesNum;
        setBirthNum(weedNum);
    }

    public Tree(int leavesNum) {
        setBirthNum(leavesNum);
        this.leavesNum = leavesNum;
    }

    @Override
    public ArrayList<Creature> newBirth() {
        ArrayList<Creature> ac = new ArrayList<>();
        int n = getBirthNum();
        while(n--!=0){
            ac.add(birth());
        }
        return ac;
    }

    @Override
    public Tree birth() {
        double f = Math.random()+0.02;
        int n = (int)(Math.random()*10)%leavesNum;
        int lN = this.leavesNum;
        if(f>0.4)lN -= n;
        else if(f>0.1)lN = lN - n*2 >0?lN - n*2:0;
        return new Tree(this.leavesNum,lN);
    }

    @Override
    public String description() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("This is a Tree with %d leaves and %d weeds",leavesNum,getBirthNum()));
        return sb.toString();
    }
}
