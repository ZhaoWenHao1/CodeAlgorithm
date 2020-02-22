import java.util.Scanner;

public class MyCode4{
    public static void main(String[] args){
        int[] num1=new int[10];
        int count=0, temp;
        Scanner sc = new Scanner(System.in);

        while(count<10){
            temp = sc.nextInt();
            if(temp==0)	break;
            num1[count] = temp;
            count++;
        }

        int num2, n;
        for(int i=0;i<count;i++){
            num2=0;
            n=0;

            while(num1[i]>=2){
                if(num1[i]==2){
                    num2++;
                    num1[i]=0;
                }else if(n==3){
                    num2++;
                    num1[i]=num1[i]-3+1;
                    n=0;
                }
                n++;
            }

            System.out.println(num2);
        }
    }
}