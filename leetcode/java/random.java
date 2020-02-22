import java.util.Scanner;

public class random {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int flag = 0,n = 0;
        int[] nums = null, f = null;
        while(sc.hasNext())
        {
            if(flag == 0)
            {
                n = sc.nextInt();
                nums = new int[n];
                f = new int[1005];
                for(int i = 0;i < 1001;i++)
                    f[i] = 0;
                flag = 1;
            }
            else
            {
                for(int i = 0;i < n;i++)
                {
                    nums[i] = sc.nextInt();
                    f[nums[i]] ++;
                }
                for(int i = 0;i < 1001;i++)
                {
                    if(f[i] > 0)
                        System.out.println(i);
                }
                flag = 0;

            }
        }
    }
}
