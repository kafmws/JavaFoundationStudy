package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Plant extends Creature {

    private String color;
    private int birthNum;

    @Override
    public void die() { setSoul(false); }

    @Override
    public ArrayList<Creature> newBirth() {
        return null;
    }

    @Override
    public Plant birth() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    protected final String getColor() {
        return color;
    }

    protected void setColor(String color) {
        this.color = color;
    }

    protected final int getBirthNum() {
        return birthNum;
    }

    protected final void setBirthNum(int birthNum) {
        while(birthNum<0){
            System.out.println("Please input leagel data.");
            birthNum = new Scanner(System.in).nextInt();
        }
        this.birthNum = birthNum;
    }
}
