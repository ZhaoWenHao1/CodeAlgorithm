import java.util.*;

/**
 * @author happyzhao
 * @data 2020/3/21 15:19
 * @type PACKAGE_NAME
 * @question
 */
class MyEntry{
    int x,y;
    MyEntry(int x,int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyEntry entry = (MyEntry)o;

        return x == entry.x &&
                y == entry.y;

    }
    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }
}
public class CanMeasureWater {

    public boolean canMeasureWater(int x, int y, int z) {
        Queue<MyEntry> queue = new LinkedList<MyEntry>();
        Set<MyEntry> set = new HashSet<MyEntry>();
        MyEntry en = new MyEntry(x,y);
        queue.offer(en);
        set.add(en);
        while(!queue.isEmpty())
        {
            // System.out.println(queue.size()+ " "+ set.size());
            MyEntry p = queue.poll();
            // System.out.println(p.x + " " + p.y);
            if(p.x + p.y == z || p.x == z || p.y == z) return true;
            // 倒空
            if(p.x != 0){
                MyEntry tem = new MyEntry(0,p.y);
                if(tem.x+tem.y == z) return true;
                if(!set.contains(tem)){
                    queue.offer(tem);
                    set.add(tem);
                }
            }
            if(p.y != 0){
                MyEntry tem = new MyEntry(p.x,0);
                if(tem.x+tem.y == z) return true;
                if(!set.contains(tem)){
                    queue.offer(tem);
                    set.add(tem);
                }
            }
            // 倒满
            if(p.x != x){
                MyEntry tem = new MyEntry(x,p.y);
                if(tem.x+tem.y == z) return true;
                if(!set.contains(tem)){
                    queue.offer(tem);
                    set.add(tem);
                }
            }
            if(p.y != y){
                MyEntry tem = new MyEntry(p.x,y);
                if(tem.x+tem.y == z) return true;
                if(!set.contains(tem)){
                    queue.offer(tem);
                    set.add(tem);
                }
            }
            // x倒入y
            if(p.x != 0 && p.y < y){
                int move = Math.min(p.x,y-p.y);
                int px = p.x - move, py = p.y + move;
                MyEntry tem = new MyEntry(px,py);
                if(tem.x+tem.y == z) return true;
                if(!set.contains(tem)){
                    queue.offer(tem);
                    set.add(tem);
                }
            }
            // y倒入x
            if(p.y != 0 && p.x < x){
                int move = Math.min(x-p.x,p.y);
                int px = p.x + move, py = p.y - move;
                MyEntry tem = new MyEntry(px,py);
                if(tem.x+tem.y == z) return true;
                if(!set.contains(tem)){
                    queue.offer(tem);
                    set.add(tem);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CanMeasureWater can = new CanMeasureWater();
        System.out.println(can.canMeasureWater(2,6,5));
    }
}
