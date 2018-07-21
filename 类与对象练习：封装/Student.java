package com.company;

public class Student {

    private Major major;
    private String studentName;
    private String studentNum;
    private String description;

    public Student(Major major,String studentName, String studentNum,String description){
        this.major = major;
        this.studentNum = studentNum;
        this.studentName = studentName;
        this.description = description;
    }

    public String getselfDescription(){
        return description;
    }
    public String getAllDescription(){
        StringBuilder re = new StringBuilder();
        re.append("I'm ");
        re.append(studentName);
        re.append("My studentnum is ");
        re.append(studentNum);
        re.append(" My major is "+major.getMajorYear()+"-year-old.");
        re.append(description);
        return  re.toString();
    }
}
