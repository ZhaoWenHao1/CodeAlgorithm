import java.util.Scanner;

class node{
    public int data;
    public node next;
    node(int n)
    {
        data = n;
    }
}


public class deleteNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while(sc.hasNext())
        {
            n = sc.nextInt();
            node head = new node(-1);
            node p = head,bp = null;
            for(int i = 0;i < n;i++)
            {
                p.next = new node(i);
                p = p.next;
            }
            p.next = head.next;
            bp = p;
            p = p.next;
            /*System.out.println(bp.data);
            System.out.println(p.data);*/
            int count = 0;
            while(bp != p)
            {
                if(count == 2)
                {
                    //System.out.println(p.data);
                    bp.next = p.next;
                    p = p.next;
                    count = 0;
                }
                else
                {
                    bp = p;
                    p = p.next;
                    count++;
                }


            }
            System.out.println(p.data);
       }
    }
}
