package com.company;

import java.util.ArrayList;

public class Earthworm extends  Animal {

    public Earthworm(String name,int age){
        super(name,age);
        this.setIsSex(false);
    }

    @Override
    public Animal birth() {
        return new Earthworm(getName()+"son",0);
    }

    @Override
    public ArrayList<Creature> newBirth(){
        ArrayList<Creature> ae = new ArrayList<>();
        ae.add(birth());
        return ae;
    }

    @Override
    public String description() {
        StringBuffer re = new StringBuffer();
        re.append("This is ");
        re.append("an earthworm named "+getName());
        re.append(". It");
        re.append(String.format(" is %d-year-old.",getAge()));
        return re.toString();
    }
}
