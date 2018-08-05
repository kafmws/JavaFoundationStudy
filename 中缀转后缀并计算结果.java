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

    private static void parseString() {//�ָ������������

        int i = 0;
        int j = 0;
        while (j != source.length()) {
            while (i < source.length() && source.charAt(i) != '+' && source.charAt(i) != '-' && source.charAt(i) != '��'
                    && source.charAt(i) != '��' && source.charAt(i) != '^' && source.charAt(i) != '('
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

    private static void infixToPostfix () {//��׺ת��׺
        parseString();
        char[] opreations = new char[1000];
        int i = 0;
        int j = 0;
        for (i = 0; i < index; i++) {
            if (!(strings[i].equals("+") || strings[i].equals("-") || strings[i].equals("��") ||
                    strings[i].equals("��"))) {//����
                strings[index2++] = strings[i];
            } else {//������
                char obj = strings[i].charAt(0);
                if (j == 0 || obj == '^' || obj == '(')//��� ��^��(
                    opreations[j++] = obj;
                else if (obj == '��' || obj == '��') {// ��  ��
                    while (opreations[j - 1] == '^') {
                        strings[index2++] = String.valueOf(opreations[--j]);
                    }//��Ҫ����
                    opreations[j++] = obj;//���赯��
                } else if (obj == '+' || obj == '-') {// +   -
                    while (opreations[j - 1] == '^' || opreations[j - 1] == '��' || opreations[j - 1] == '��') {
                        strings[index2++] = String.valueOf(opreations[--j]);
                    }
                    opreations[j++] = obj;//��ջ
                } else if (obj == ')') {
                    while (opreations[j - 1] != '(') {
                        strings[index2++] = String.valueOf(opreations[--j]);
                    }
                    j--;//����������
                } else {//����

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

    public static String calculate(){//�����׺���ʽ
        infixToPostfix ();
        int before;
        int later;//later�洢���
        ArrayList<Object> objects = new ArrayList<>();
        for(int i = 0;i<index2;i++){
            String s = strings[i];
            if(s.length()==1){
                if(!(s.charAt(0)>='0'&&s.charAt(0)<='9')){//�������
                    objects.add(s);
                }else objects.add(new BigDecimal(s));
            }else objects.add(new BigDecimal(s));
        }//objects��˳��źõ�������Ͳ�����
        //
        for(int k=0;k<objects.size()&&objects.size()!=1;k++){//
            Object o = objects.get(k);
            if(o.equals("+")||o.equals("-")||o.equals("��")||o.equals("��")||o.equals("^")){//�������
                before = objects.indexOf(o)-1;
                later = before-1;//ȡ������������
                switch ((String)o){
                    case "+":objects.set(later,((BigDecimal)(objects.get(later))).add((BigDecimal)((objects.get(before)))));break;
                    case "-":objects.set(later,((BigDecimal)objects.get(later)).subtract(((BigDecimal)objects.get(before))));break;
                    case "��":objects.set(later,((BigDecimal)objects.get(later)).multiply(((BigDecimal)objects.get(before))));break;
                    case "��":objects.set(later,((BigDecimal)objects.get(later)).divide(((BigDecimal)objects.get(before))));break;
                    case "^":objects.set(later,((BigDecimal)objects.get(later)).pow((int)objects.get(before)));break;
                    default:break;
                }
                objects.remove(before);objects.remove(o);
                k=0;
            }
        }//�������   objects.get[0] Ϊ���
        BigDecimal result = (BigDecimal)objects.get(0);
        return (String.valueOf(result));
    }

}
