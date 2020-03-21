/**
 * @author happyzhao
 * @data 2020/3/14 19:00
 * @type PACKAGE_NAME
 * @question
 */
public class Singleton {
    private volatile static Singleton uniqueInstance;
    private Singleton(){
    }
    public static Singleton getInstance(){
        if(uniqueInstance == null){
            synchronized (Singleton.class){
                if(uniqueInstance == null){
                    uniqueInstance = new Singleton();
                }
            }
        }
        return uniqueInstance;
    }
}
