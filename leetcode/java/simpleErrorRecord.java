import java.util.Scanner;

public class simpleErrorRecord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int MAX = 500;
        String[] str = new String[MAX];
        int[] row = new int[MAX];
        int[] count = new int[MAX];
        int sum = 0;
        //String s = null;
        int fl = 0;
        while(sc.hasNext())
        {

            String s = sc.next();
            int rowNum = sc.nextInt();
            //String s = sc.nextLine();
            String[] res = s.split("\\\\");
            /*for(int i = 0;i < res.length;i++)
            {
                System.out.println(res[i]);
            }*/
            int len = res.length;
            String file = res[len-1];
            //int rowNum = Integer.valueOf(res[len-1]);
            int find = 0;
            for(int i = 0;i < sum;i++)
            {
                if(str[i].equals(file))
                {
                    if(row[i] == rowNum)
                    {
                        count[i]++;
                        find = 1;
                        break;
                    }
                }
            }
            if(find == 0)
            {
                str[sum] = file;
                row[sum] = rowNum;
                count[sum++] = 1;
            }
        }

        for(int i =0;i < sum-1;i++)
        {
            for(int j = 0;j < sum-i-1;j++)
            {
                if(count[j] < count[j+1])
                {
                    String ts = str[j];
                    str[j] = str[j+1];
                    str[j+1] = ts;

                    int t = row[j];
                    row[j] = row[j+1];
                    row[j+1] = t;

                    t = count[j];
                    count[j] = count[j+1];
                    count[j+1] = t;
                }
            }
        }


        for(int i = 0;i < sum&& i < 8;i++)
        {
            if(str[i].length() > 16)
            {
                System.out.print(str[i].substring(str[i].length()-16));
            }
            else
                System.out.print(str[i]);
            System.out.println(" "+row[i]+" "+count[i]);
        }
    }
}
