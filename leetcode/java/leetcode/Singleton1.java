/**
 * @author happyzhao
 * @data 2020/3/14 20:09
 * @type PACKAGE_NAME
 * @question  最简单的单例模式 饿汉式 加载类时就初始化
 */
public class Singleton1 {
    private static Singleton1 uniqueInstance = new Singleton1();
    private Singleton1(){}
    public Singleton1 getUniqueInstance(){
        return uniqueInstance;
    }
}
