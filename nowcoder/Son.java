/**
 * @author happyzhao
 * @data 2020/3/3 13:24
 * @type
 * @question
 */
class Father{
    final protected int f(int a, char c){
        System.out.println("father");
        return 1;
    }
}
public class Son extends Father{
    public int f(char c,int a){
        System.out.println("son");
        return 1;
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.f(1,'c');
        //son.f('c',1);
    }
}
