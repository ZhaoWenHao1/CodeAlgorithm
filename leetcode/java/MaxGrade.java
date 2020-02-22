import java.util.Scanner;

public class MaxGrade {
    public static void main(String[] args) {
        int n,m;
        int[] grade = null;
        String s = null;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            n = sc.nextInt();
            m = sc.nextInt();
            grade = new int[n];
            for(int i = 0;i < n;i++)
            {
                grade[i] = sc.nextInt();
            }
            for(int i = 0;i < m;i++)
            {
                s = sc.next();
                int a = sc.nextInt(),b = sc.nextInt();
                if(s.equals("Q"))
                {
                    int maxGrade = -1;
                    if(a > b){
                        int tem = a;
                        a = b;
                        b = tem;
                    }
                    for(int j = a;j <= b;j++)
                    {
                        maxGrade = Math.max(maxGrade,grade[j-1]);
                    }
                    System.out.println(maxGrade);
                }
                else if(s.equals("U"))
                {
                    grade[a-1] = b;
                }
            }
        }
    }
}
