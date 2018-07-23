package com.company;

import java.util.ArrayList;

public class Dog extends Animal {
    public Dog(String name, sexType sex ,int age){
        super(name, age);
        setIsSex(true);
        super.setSex(sex);
    }

    @Override
    public ArrayList<Creature> newBirth(){
        int n = (int)Math.max(Math.random(),Math.random())*10;
        ArrayList<Creature> ad = new ArrayList<>();
        sexType type;
        while(n--!=0){
        type = sexType.female;
        if (Math.random() > 0.49999)
            type = sexType.male;
        ad.add(type == sexType.male?new Dog( getName()+ "son", type,0):new Dog(getName() + "dter", type,0));
        }
        return ad;
    }
    @Override
    public Dog birth(){
        sexType type = sexType.female;
        if (Math.random() > 0.49999)
            type = sexType.male;
        return type == sexType.male?new Dog( getName()+ "son", type,0):new Dog(getName() + "dter", type,0);
    }

    @Override
    public String description() {
        StringBuffer re = new StringBuffer();
        re.append("This is ");
        re.append("a dog named "+getName());
        if(isSex()){
            if(getSex() == sexType.male)
                re.append(". He");
            else if(getSex() == sexType.female)
                re.append(". She");
            }
        else
            re.append(". It");
        re.append(String.format(" is %d-year-old.",getAge()));
        return re.toString();
    }

    public void bark(){
        System.out.println("汪");
    }

}