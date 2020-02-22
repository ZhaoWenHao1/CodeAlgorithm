import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class stringHandle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext())
        {
            String s = sc.next();
            Vector<Character> vector = new Vector<>();
            Set<Character> set = new HashSet<Character>();
            int sum = 0;
            for(int i = 0;i < s.length();i++)
            {
                if(!set.contains(s.charAt(i)))
                {
                    vector.add(s.charAt(i));
                    set.add(s.charAt(i));
                    sum++;
                }
            }
            System.out.println(sum + "  " + vector.size());
            for(int i = 0;i < sum;i++)
            {
                System.out.print(vector.get(i));
            }
            System.out.println();
        }
    }
}
