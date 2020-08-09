import java.util.Scanner;
import java.util.Vector;

/**
 * @author happyzhao
 * @data 2020/4/1 19:00
 * @type PACKAGE_NAME
 * @question  携程t1
 */
public class T1 {

    static long countDolphin(int n, int m, int[] birthYear, int x) {
        Vector<Integer> vec = new Vector<>(),tvec = new Vector<>();
        vec.add(0);
        for(int i = 1;i <= x;i++){
            for(int j = 0;j < vec.size();j++){
                int v = vec.get(j);
                vec.set(j,v+1);
                if(vec.get(j) > m)
                    continue;
                else{
                    for(int k = 0;k < birthYear.length;k++){
                        if(vec.get(j) == birthYear[k]){
                            tvec.add(0);
                            break;
                        }
                    }
                    tvec.add(v+1);
                }
            }
            Vector t = tvec;
            tvec = vec;
            vec = t;
            tvec.clear();
        }
        /*System.out.println(vec);
        System.out.println(vec.size());*/
        return vec.size()*m;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long res;

        int _n;
        _n = Integer.parseInt(in.nextLine().trim());

        int _m;
        _m = Integer.parseInt(in.nextLine().trim());

        int _birthYear_size = 0;
        _birthYear_size = Integer.parseInt(in.nextLine().trim());
        int[] _birthYear = new int[_birthYear_size];
        int _birthYear_item;
        for(int _birthYear_i = 0; _birthYear_i < _birthYear_size; _birthYear_i++) {
            _birthYear_item = Integer.parseInt(in.nextLine().trim());
            _birthYear[_birthYear_i] = _birthYear_item;
        }

        int _x;
        _x = Integer.parseInt(in.nextLine().trim());

        res = countDolphin(_n, _m, _birthYear, _x);
        System.out.println(String.valueOf(res));
    }
}
