import java.util.Arrays;
import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/3/28 23:24
 * @type PACKAGE_NAME
 * @question
 */
class TrieNode {
    char val;
    TrieNode[] next = new TrieNode[26];
    TrieNode(char c){
        this.val = c;
    }
    TrieNode(){}
}
public class TrieTree {
    int insert(TrieNode root, String word){
        int cont = 0;
        TrieNode p = root;
        for(int i = word.length()-1; i >= 0;i--){
            int c = word.charAt(i)-'a';
            if(p.next[c] == null){
                p.next[c] = new TrieNode(word.charAt(i));
                cont++;

            }
            p = p.next[c];
        }
        return cont == 0 ? 0 : word.length()+1;
    }
    public int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, (s1,s2)->s2.length() - s1.length());
        TrieNode root = new TrieNode();
        int ans = 0;
        for(String word: words){
            ans += insert(root,word);
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] strings = new String[n];
        for(int i = 0;i < n;i++){
            strings[i] = sc.next();
        }
        TrieTree trieTree = new TrieTree();
        System.out.println(trieTree.minimumLengthEncoding(strings));
    }
}
