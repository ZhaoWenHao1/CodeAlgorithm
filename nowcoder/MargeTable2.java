import java.util.*;

/**
 * @author happyzhao
 * @data 2020/2/18 21:02
 * @type 华为机试在线训练
 * @question
 */
class Record2  {
    int idx;
    int vaule;

    Record2(int idx, int value) {
        this.idx = idx;
        this.vaule = value;
    }

    public void merge(int value) {
        this.vaule += value;
    }
}
class MyComparator implements Comparator<Record2>{
    @Override
    public int compare(Record2 o1, Record2 o2) {
        return o1.idx - o2.idx;
    }

}
public class MargeTable2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //List<Record> res = new ArrayList<Record>(n);
        Record2[] res = new Record2[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            int find = 0;
            for (int j = 0; j < sum; j++) {
                if (res[j] != null && res[j].idx == a) {
                    res[j].merge(b);
                    find = 1;
                    break;
                }
            }
            if (find == 0) {
                res[sum++] = new Record2(a, b);
            }
        }
        Record2[] ans = new Record2[sum];
        for(int i = 0;i < sum;i++)
        {
            ans[i] = res[i];
        }
        Arrays.sort(ans,new MyComparator());

        for(int i = 0;i < sum;i++)
        {
            System.out.println(ans[i].idx+" "+ans[i].vaule);
        }

        //Collections.sort(List<T> list, Comparator<? super T> c) ;
    }
}
