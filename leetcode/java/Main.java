import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Main {
    public int handle(int n) {
        int last = n;
        int sum = 0;
        while (last >= 2) {
            if (last == 2)//先借一瓶 喝完再还
            {
                sum++;
                return sum;
            } else {
                int cur = last / 3; // 换的汽水数
                sum += cur;
                last = cur + last - cur * 3;
            }
        }
        return sum;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        File file = new File("E:/songsList.txt");
        try {
            OutputStream outputStream = new FileOutputStream(file);
            while (sc.hasNext()) {
                String string = sc.nextLine();
                if ("-1".equals(string)) {
                    outputStream.flush();
                    outputStream.close();
                    return;
                }
                String[] strings = string.split("[，。]");
                for (String str : strings) {
                    str = str + "  ";
                    System.out.println(str);
                    outputStream.write(str.getBytes());
                }
                outputStream.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
