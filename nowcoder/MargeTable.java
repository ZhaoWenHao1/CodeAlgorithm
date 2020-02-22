import java.util.*;

/**
 * @author happyzhao
 * @data 2020/2/18 18:19
 * @type 华为机试在线训练
 * @question 合并表记录
 */
class Record implements Comparable{
    int idx ;
    int vaule ;
    Record(int idx,int value)
    {
        this.idx = idx;
        this.vaule = value;
    }
    public void merge(int value)
    {
        this.vaule += value;
    }
    @Override
    public int compareTo(Object o) {
        Record obj = (Record)o;
        //按照idx排序
        return this.idx - obj.idx;//升序
        //return obj.idx - this.idx;//降序

        //如果按照idx排序，如果idx相同 按照value升/降序排列
        /*if(this.idx!=obj.idx)
        {
            return this.idx - obj.idx;
        }
        else
        {
            return this.vaule - obj.vaule;//按照value升序
            //return obj.vaule - this.vaule;//按照value降序
        }*/
    }
}
public class MargeTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //List<Record> res = new ArrayList<Record>(n);
        Record[] res = new Record[n];
        int sum = 0;
        for(int i = 0;i < n;i++)
        {
            int a = sc.nextInt(), b = sc.nextInt();
            int find = 0;
            for(int j = 0;j < sum;j++)
            {
                if(res[j] != null && res[j].idx == a)
                {
                    res[j].merge(b);
                    find = 1;
                    break;
                }
            }
            if(find == 0)
            {
                res[sum++] = new Record(a,b);
            }
        }
        Record[] ans = new Record[sum];
        for(int i = 0;i < sum;i++)
        {
            ans[i] = res[i];
        }
        Arrays.sort(ans);

        List<Record> list = new ArrayList<Record>();
        for(int i = 0;i < sum;i++)
        {
            list.add(ans[i]);
        }
        Collections.sort(list);
        for(int i = 0;i < sum;i++)
        {
            System.out.println(ans[i].idx+" "+ans[i].vaule);
        }
    }
}
