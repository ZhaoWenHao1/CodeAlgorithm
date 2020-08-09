package alibaba;

import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/5/29 8:59
 * @type alibaba
 * @question
 */
public class Main {
    static int upper(double l){
        int len;
        // 向上取整
        if(l - (int)l > 0.0){
            len = (int)l + 1;
        }
        else{
            len = (int)l;
        }
        return len;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 0;i < t;i++){
            long n = in.nextInt(), m = in.nextInt(), w2 = in.nextInt(), w3 = in.nextInt();
            if(m <= n){
                System.out.println(0);
                continue;
            }
            double l = Math.log(m/n)/Math.log(2);
            int len = upper(l);
            long min = len * w2;

            for(int j = len-1;j >= 0;j--){
                 double last = m/(n*Math.pow(2,j));
                 double c2 = Math.log(last)/Math.log(3);
                 int len2 = upper(c2);
                 long cost = j * w2 + len2 * w3;
                 min = Math.min(min, cost);
            }
            System.out.println(min);


            /*long pos = n, cost = 0;
            while(pos < m){
                if(pos*3 < m){
                    long c1 = pos/w2, c2 = (2*pos)/w3;
                    if(c1 > c2){
                        pos *= 2;
                        cost += w2;
                    }
                    else {
                        pos *= 3;
                        cost += w3;
                    }
                }
                else{
                    if(pos * 2 >= m){
                        if(w2 < w3){
                            pos *= 2;
                            cost += w2;
                        }
                        else{
                            pos *= 3;
                            cost += w3;
                        }
                    }
                    else{
                        if(w2 * 2 < w3){
                            pos *= 4;
                            cost += 2*w2;
                        }
                        else{
                            pos *= 3;
                            cost += w3;
                        }
                    }
                }

            }
            System.out.println(cost);*/
        }
    }
}
