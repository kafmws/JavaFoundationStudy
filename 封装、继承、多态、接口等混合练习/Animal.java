package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Animal extends Creature{
    private boolean isSex=true;
    private String name;
    private int age;
    private sexType sex;

    public Animal(String name,int age){
        this.name = name;
        setAge(age);
    }

    public void setIsSex(Boolean status){
        isSex = status;
    }

    public final void setSex(sexType type){
        if(isSex){
            this.sex = type;
        }
    }

    private final void setAge(int age){
        while(age<0){
            System.out.print("请输入合法的年龄：");
            age = new Scanner(System.in).nextInt();
        }
            this.age =age;
    }

    public final int getAge() {
        return age;
    }

    public final sexType getSex() {
        if(isSex())
            return sex;
        else
            return null;
    }

    public final String getName() {
        return name;
    }

    @Override
    public void die() {
        setSoul(false);
    }

    @Override
    public ArrayList<Creature> newBirth(){
        return  null;
    }

    @Override
    public Animal birth() {
        return null;
    }

    @Override
    public String description() {
        return null;
    }

    public final boolean isSex() {
        return isSex;
    }

}
