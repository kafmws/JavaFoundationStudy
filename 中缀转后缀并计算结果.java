package com.example.hp.uipractice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Calculate {

    private static String source;
    private static int index = 0;
    private static String[] strings = new String[1000];
    private static int index2 = 0;

    public static void setSource(String s) {
        source = s;
    }

    private static void parseString() {//分割数字与操作符

        int i = 0;
        int j = 0;
        while (j != source.length()) {
            while (i < source.length() && source.charAt(i) != '+' && source.charAt(i) != '-' && source.charAt(i) != '×'
                    && source.charAt(i) != '÷' && source.charAt(i) != '^' && source.charAt(i) != '('
                    && source.charAt(i) != ')') {
                i++;
            }
            if (i == source.length()) {
                strings[index++] = source.substring(j);
                break;
            }
            strings[index++] = source.substring(j, i);
            j = i;
            strings[index++] = source.substring(j, j + 1);
            j++;
            i = j;
        }
//        for (int k = 0; k < index; k++)
//            System.out.print(strings[k]);
//        System.out.println();
    }

    private static void infixToPostfix () {//中缀转后缀
        parseString();
        char[] opreations = new char[1000];
        int i = 0;
        int j = 0;
        for (i = 0; i < index; i++) {
            if (!(strings[i].equals("+") || strings[i].equals("-") || strings[i].equals("×") ||
                    strings[i].equals("÷"))) {//数字
                strings[index2++] = strings[i];
            } else {//操作符
                char obj = strings[i].charAt(0);
                if (j == 0 || obj == '^' || obj == '(')//最初 、^、(
                    opreations[j++] = obj;
                else if (obj == '×' || obj == '÷') {// ×  ÷
                    while (opreations[j - 1] == '^') {
                        strings[index2++] = String.valueOf(opreations[--j]);
                    }//需要弹出
                    opreations[j++] = obj;//无需弹出
                } else if (obj == '+' || obj == '-') {// +   -
                    while (opreations[j - 1] == '^' || opreations[j - 1] == '×' || opreations[j - 1] == '÷') {
                        strings[index2++] = String.valueOf(opreations[--j]);
                    }
                    opreations[j++] = obj;//入栈
                } else if (obj == ')') {
                    while (opreations[j - 1] != '(') {
                        strings[index2++] = String.valueOf(opreations[--j]);
                    }
                    j--;//丢弃左括号
                } else {//报错

                }
            }
        }
        while (j-- != 0) {
            strings[index2++] = String.valueOf(opreations[j]);
        }
//        for (int k = 0; k < index2; k++)
//            System.out.print(strings[k] + " ");
//        System.out.println();
    }

    public static String calculate(){//计算后缀表达式
        infixToPostfix ();
        int before;
        int later;//later存储结果
        ArrayList<Object> objects = new ArrayList<>();
        for(int i = 0;i<index2;i++){
            String s = strings[i];
            if(s.length()==1){
                if(!(s.charAt(0)>='0'&&s.charAt(0)<='9')){//是运算符
                    objects.add(s);
                }else objects.add(new BigDecimal(s));
            }else objects.add(new BigDecimal(s));
        }//objects按顺序放好的运算符和操作数
        //
        for(int k=0;k<objects.size()&&objects.size()!=1;k++){//
            Object o = objects.get(k);
            if(o.equals("+")||o.equals("-")||o.equals("×")||o.equals("÷")||o.equals("^")){//是运算符
                before = objects.indexOf(o)-1;
                later = before-1;//取得两个操作数
                switch ((String)o){
                    case "+":objects.set(later,((BigDecimal)(objects.get(later))).add((BigDecimal)((objects.get(before)))));break;
                    case "-":objects.set(later,((BigDecimal)objects.get(later)).subtract(((BigDecimal)objects.get(before))));break;
                    case "×":objects.set(later,((BigDecimal)objects.get(later)).multiply(((BigDecimal)objects.get(before))));break;
                    case "÷":objects.set(later,((BigDecimal)objects.get(later)).divide(((BigDecimal)objects.get(before))));break;
                    case "^":objects.set(later,((BigDecimal)objects.get(later)).pow((int)objects.get(before)));break;
                    default:break;
                }
                objects.remove(before);objects.remove(o);
                k=0;
            }
        }//计算完毕   objects.get[0] 为结果
        BigDecimal result = (BigDecimal)objects.get(0);
        return (String.valueOf(result));
    }

}
