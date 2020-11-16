package leetcode;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/15 10:22
 * @type PACKAGE_NAME
 * @question
 */
class Node{
    int n;
    Node next;
    Node left;
    Node right;
    Node(int v){
        this.n = v;
    }
}
public class Main {
    public static void main(String[] args) {
        Node node = new Node(1);
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
    }
}
