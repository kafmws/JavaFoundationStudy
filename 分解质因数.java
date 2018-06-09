import java.lang.*;
import java.util.*;

class judgement {
    public static boolean judge(int num) {
        boolean flag=true;
        if(num<2){
            flag=false;
            return flag;
        }else {
            for(int i=2;i<=Math.sqrt(num);i++){
                if(num%i==0) {
                    flag=false;
                    break;
                }
            }
        }
        return flag;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int num = input.nextInt(), i,t;
        if(judgement.judge(num)==false){
            t=num;
            System.out.print(num+"=");
            while(t!=1){
                for(i=2;i<num;i++){
                    if(judgement.judge(i)&&t%i==0){
                        System.out.print(i);
                        t/=i;
                        if(t!=1)System.out.print("x");
                        break;
                    }
                }
            }
        }else{
            System.out.print(num+"="+num);
        }
    }
}