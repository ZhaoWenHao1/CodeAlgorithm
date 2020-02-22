import java.util.Scanner;

public class Main {
    public int handle(int n)
    {
        int last = n;
        int sum = 0;
        while(last >= 2)
        {
            if(last == 2)//先借一瓶 喝完再还
            {
                sum++;
                return sum;
            }
            else
            {
                int cur = last/3; // 换的汽水数
                sum += cur;
                last = cur + last - cur*3;
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        /*int[] num = new int[10];//空瓶子数
        int count = 0, temp;
        Scanner sc = new Scanner(System.in);
        while(count<10){
            temp = sc.nextInt();
            if(temp==0)	break;
            num[count] = temp;
            count++;
        }
        for(int i = 0;i < count;i++)
        {
            System.out.println(num[i]);
        }*/

        Main sudaBottle = new Main();


        Scanner sc=new Scanner(System.in);
        int n;
        while(sc.hasNext())
        {
            n=sc.nextInt();
            System.out.println(sudaBottle.handle(n));
        }
    }
}
