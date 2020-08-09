package t3;


import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/4/1 20:22
 * @type PACKAGE_NAME
 * @question
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] ss = {"surprise", "happy", "ctrip", "travel", "wellcome","student","system","program","editor"};
        int resIdx = -1;

        while(sc.hasNext()){
            String s = sc.next();
            int len = s.length();

            if(len <= 2)
            {
                System.out.println();
                continue;
            }
            int count = 0;
            int[] f = new int[12];
            for(int i = 0;i < ss.length;i++){

                int idx = 0,t;
                for(int j = 0;j < len;j++){
                    if(idx < ss[i].length() && (t = ss[i].indexOf(s.charAt(j),idx)) != -1){
                        count++;
                        idx = t+1;
                        f[t] = 1;
                    }
                }
                if(count >= ss[i].length()-2){
                    resIdx = i;
                    break;
                }
            }

            if(resIdx == -1)
                System.out.println("null");
            else{
                if(len < ss[resIdx].length()-2 || len > ss[resIdx].length()+2)
                    System.out.println();
                else
                {
                    System.out.println(ss[resIdx]);
                }

            }
        }
    }
}

