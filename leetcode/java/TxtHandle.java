import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtHandle {
    public static void readTxtFileIntoAnotherTxt(String filePath, String targetPath)
        {
            List<String> list = new ArrayList<String>();
            try
            {
                String encoding = "UTF-8";
                File file = new File(filePath);
                File target = new File(targetPath);
                target.createNewFile();
                if (file.isFile() && file.exists())
                { // 判断文件是否存在
                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(file), encoding);// 考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;
                    BufferedWriter out = new BufferedWriter(new FileWriter(targetPath));
                    int find = 0;
                    while ((lineTxt = bufferedReader.readLine()) != null)
                    {
                        if(find == 0)
                        {
                            if(lineTxt.contains("消息对象:Serendipity"))
                            {
                                find = 1;
                            }
                            if(find == 0)
                                continue;
                            else
                            {
                                System.out.println(lineTxt);
                                lineTxt = "\r\n" + lineTxt;
                                out.write(lineTxt); // 写入TXT
                                out.flush();
                            }
                        }
                        else {
                            if(lineTxt.contains("消息对象:"))
                            {
                                break;
                            }
                            else
                            {
                                System.out.println(lineTxt);
                                lineTxt = "\r\n" + lineTxt;
                                out.write(lineTxt); // 写入TXT
                                out.flush();
                            }
                        }

                    }
                    out.close();
                    bufferedReader.close();
                    read.close();
                }
                else
                {
                    System.out.println("找不到指定的文件");
                }
            }
            catch (Exception e)
            {
                System.out.println("读取文件内容出错");
                e.printStackTrace();
            }


        }

    public static void main(String[] args) {
        TxtHandle txtHandle = new TxtHandle();
        TxtHandle.readTxtFileIntoAnotherTxt("C:\\Users\\Asus\\Desktop\\全部消息记录.txt", "C:\\Users\\Asus\\Desktop\\Serendipity.txt");
    }
}
