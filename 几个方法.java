import  java.lang.*;
import  java.util.*;


public class Test{
    /*计算两个多项式的和并按一定规则输出*/
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int [] num = new int [101];
        int n,a,cnt=0,i=0;
        while(cnt<2){
            n=input.nextInt();//幂次
            a=input.nextInt();
            num[n]+=a;
            if(n==0){
                cnt++;
            }
        }//录入
        //输出
        int flag=1,exam=0;
        for(i=100;i>=0;i--){
            if(num[i]!=0){
                exam=1;
                if(flag==0){
                    if(num[i]>0)System.out.print("+");
                }else{
                    flag=0;
                }
                if(num[i]!=1&&num[i]!=-1||(i==0&&num[i]!=0)){
                    System.out.print(num[i]);
                }
                if(num[i]!=0&&i>0){
                    if(num[i]==-1)
                        System.out.print("-");
                    System.out.print("x");
                    if(i>=2){
                        System.out.print(i);
                    }
                }
            }
        }
        if(exam==0){
            System.out.print(0);
        }
    }
    /*计算句子中每个单词长度.为结束不计入*/
    public static void main2(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        int[] num = new int[1000];
        int i, j, k = 0;
        for (i = 0; i < str.length(); i++) {
            if (str.charAt(i) != ' '&&str.charAt(i) != '.')
                for (j = i; j < str.length();j++) {
                    if (str.charAt(j) == ' '||str.charAt(j)=='.') {
                        num[k++] = j - i;
                        i=j-1;
                        break;
                    }
                }
        }
        for(int h=0;h<k;h++){
            System.out.print(num[h]);
            if(h!=k-1){
                System.out.print(" ");
            }
        }
    }
    /*GPS字符串信息处理*/
    public static void main3(String[] args) {
        int k;
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String h="0",m="0",s="0";
        while(str.compareTo("END")!=0){
            String examstr = str.substring(str.length()-2,str.length());
            int examination = str.charAt(1);
            int begin=str.indexOf('$'),end=str.lastIndexOf('*');
            for(k=begin+2;k<end;k++){
                examination = examination^str.charAt(k);
            }//得到验证的exam
            examination%=65536;
            String exam =Integer.toString(examination,16);
            int flag_A=str.indexOf(',',str.indexOf(',')+1)+1;
            int time=str.indexOf('.');
            if(exam.equals(examstr)&&str.charAt(flag_A)=='A'){//有效数据
                h=str.substring(time-6,time-4);
                m=str.substring(time-4,time-2);
                s=str.substring(time-2,time);
            }
            str = input.nextLine();
        }
        int hh=Integer.parseInt(h);
        hh=(hh+8)%24;
        h=Integer.toString(hh);
        System.out.printf("%02d:%02d:%02d",Integer.parseInt(h),Integer.parseInt(m),Integer.parseInt(s));
    }
}