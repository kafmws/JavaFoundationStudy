package com.company;

import java.util.*;
public class Main {

    public static void main(String[] args) {
        Major sEM = new Major("SoftwareEngineerMajor","001",4,"This is SoftwareEngineerMajor,which majors in software development.");
        Major cSM = new Major("ComputerScienceMajor","002",4,"ComputerScienceMajor,which majors in computer‘s science.");
        Student ws = new Student(sEM,"王舜","04173001","永言配命，自求多福。");
        Student mmj = new Student(cSM,"马铭骏","04173004","我是马铭骏。");
        Student wzp = new Student(cSM,"王泽鹏","04173101","北冥有鱼，化而为鹏。");
        System.out.println(ws.getselfDescription());
        System.out.println(mmj.getAllDescription());
        System.out.println(cSM.getDescription());
    }
}
