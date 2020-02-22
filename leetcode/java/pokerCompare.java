import java.util.Scanner;

public class pokerCompare {
    static int[] handle(String s,int[] joker) {
        String[] strings = s.split(" ");
        //int[] joker = new int[15];// 0和14分别表示大王和小王
        for (int i = 0; i < strings.length; i++) {
            int num = -1;
            if (strings[i].length() == 1)//非小王和10
            {
                char c = strings[i].charAt(0);

                if (c >= '1' && c <= '9') {
                    num = c - '0';
                } else if (c == 'J') {
                    num = 11;
                } else if (c == 'Q') {
                    num = 12;
                } else if (c == 'K') {
                    num = 13;
                } else
                    System.out.println("error");
            } else if (strings[i].length() == 2) {
                num = 10;
            } else {
                if (strings[i].equals("joker"))
                    num = 0;
                else
                    num = 14;
            }
            joker[num]++;
        }
        return joker;
    }

    static int[] judge(int[] joker) {
        int[] res = new int[2];//res[0] type  res[1] 最大的
        if (joker[0] == 1 && joker[14] == 1) {
            res[0] = 5;
            res[1] = 14;//王炸
            return res;
        }
        int[] idx = {14, 0, 2, 1, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3};
        for (int i = 2; i < 15 ; i++) {
            if (joker[idx[i]] == 4) {
                res[0] = 5;
                res[1] = idx[i];
                return res;
            }
            if (joker[i] == 3) {
                res[0] = 4;
                res[1] = i;
                return res;
            }
            if (joker[i] == 2) {
                res[0] = 2;
                res[1] = i;
                return res;
            }

        }
        if (joker[1] == 1 && joker[13] == 1 && joker[12] == 1 && joker[11] == 1 && joker[10] == 1) {
            res[0] = 3;
            res[1] = 1;
            return res;
        }
        for (int i = 13; i >= 7; i--) {
            int shunzi = 1;
            for (int j = 0; j < 5; j++) {
                if (joker[i - j] != 1) {
                    shunzi = 0;
                    break;
                }
            }
            if (shunzi == 1) {
                res[0] = 3;
                res[1] = i;
                return res;
            }
        }
        res[0] = 1;
        //int[] idx = {14, 0, 2, 1, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3};
        for (int i = 0; i < 15; i++) {
            if (joker[idx[i]] == 1) {
                res[1] = idx[i];
                return res;
            }
        }
        res[0] = 0;//错误
        //myPrint(joker);
        return res;

    }
    static void myPrint(int[] joker)
    {
        if(joker == null)
        {
            return;
        }
        for(int i = 0;i < 15;i++)
        {
            int j = joker[i];
            for(int k = 0;k < j;k++)
            {
                System.out.print(i+" ");
            }
        }
        //System.out.println(" j ");
    }
    static int compare(int a,int b) //除大小王的比较
    {
        if(a >= 3)
        {
            if(b >= 3)
            {
                return a > b ? 0 : 1;
            }
            else
                return 1;
        }
        else
        {
            if(b >= 3)
                return 0;
            else
                return a > b ? 0 : 1;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] strings = s.split("-");
            int ta = 0, tb = 0;
            int[] ja = new int[15],jb = new int[15];
            handle(strings[0],ja);
            handle(strings[1],jb);
            //myPrint(ja);
            //myPrint(jb);
            int[] resa = judge(ja),resb = judge(jb);
            if(resa[0] == 0 || resb[0] == 0)
            {
                System.out.println("ERROR");
            }
            if(resa[0] == 5 && resb[0] == 5)
            {
                if(resa[1] == 14)
                {
                    System.out.println(strings[0]);
                    return;
                }
                if(resb[1] == 14)
                {
                    System.out.println(strings[1]);
                    return;
                }
                int ans = compare(resa[1],resb[1]);
                System.out.println(strings[ans]);
                return;
            }
            else if(resa[0] == 5 || resb[0] == 5)
            {
                if(resa[0] == 5)
                {
                    System.out.println(strings[0]);
                }
                else
                {
                    System.out.println(strings[1]);
                }
                return;
            }
            else if(resa[0] == resb[0])
            {
                if(resa[0] == 1)
                {
                    if(resa[1] == 14 || resb[1] == 14)
                    {
                        int ans = resa[1] == 14 ? 0 : 1;
                        System.out.println(strings[ans]);
                        return;
                    }
                    else if(resa[1] == 0 || resb[1] == 0)
                    {
                        int ans = resa[1] == 0 ? 0 : 1;
                        System.out.println(strings[ans]);
                        return;
                    }
                    else {
                        int ans = compare(resa[1],resb[1]);
                        System.out.println(strings[ans]);
                        return;
                    }
                }
                else
                {
                    int ans = compare(resa[1],resb[1]);
                    System.out.println(strings[ans]);
                }
            }
            else
            {
                System.out.println("ERROR");
            }

        }
    }
}
