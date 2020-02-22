import javax.security.sasl.SaslClient;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/**
 * @author happyzhao
 * @data 2020/2/19 17:21
 * @type 华为机试在线训练
 * @question 查找兄弟单词
 */
public class FindBroWord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            int n = sc.nextInt();
            String[] dir = new String[1000], compent = new String[1000];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                String word = sc.next();
                char[] cw = word.toCharArray();
                Arrays.sort(cw);
                String tcom = new String(cw);
                dir[i] = word;
                compent[i] = tcom;
                sum++;
            }

            String query = sc.next();
            int idx = sc.nextInt();

            char[] cq = query.toCharArray();
            Arrays.sort(cq);
            String qcom = new String(cq);
            Vector<String> vec = new Vector<String>();
            int ans = 0;
            for (int i = 0; i < sum; i++) {
                if (compent[i].equals(qcom)) {
                    if (!dir[i].equals(query)) {
                        vec.add(dir[i]);
                        ans++;
                    }
                }
            }
            if(vec.size() == 0) {
                System.out.println(0);
                return;
            }
            //对vec进行字典排序
            //基排序
            Vector<Vector<String>> sorVec = new Vector<Vector<String>>();
            for (int i = 0; i < 26; i++) {
                sorVec.add(new Vector<String>());
            }
            int len = query.length();

            for (int i = len - 1; i >= 0; i--) {
                //分
                for (int j = 0; j < vec.size(); j++) {
                    int ch = vec.get(j).charAt(i) - 'a';
                    sorVec.get(ch).add(vec.get(j));
                }
                //收
                for (int j = 0; j < 26; j++) {
                    for (int k = 0; k < sorVec.get(j).size(); k++) {
                        vec.add(sorVec.get(j).get(k));
                    }
                    sorVec.get(j).clear();
                }
            }
            System.out.println(ans);
            if(idx <= vec.size())
                System.out.println(vec.get(idx - 1));
        }
    }
}
