package com.company;

public class Major {
    private String majorName;
    private String majorNum;
    private int majorYear;
    private String description;

    public int getMajorYear() {
        return majorYear;
    }

    public Major(String majorName, String majorNum, int majorYear, String description){
        this.majorName =majorName;
        this.majorNum = majorNum;
        this.majorYear = majorYear;
        this.description=description;
    }

    public String getDescription(){
        return description;
    }
}