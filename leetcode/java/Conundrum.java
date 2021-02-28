import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: CodeAlgorithm
 * @description: 猜字谜
 * @author: zwh
 * @create: 2021-02-27 14:17
 **/
public class Conundrum {
    DirTreeNode root;
    
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        root = new DirTreeNode();
        for(String word: words){
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            StringBuilder sb = new StringBuilder();
            for(int i = 0;i < chars.length;i++){
                if(i == 0 || chars[i] != chars[i-1]){
                    sb.append(chars[i]);
                }
            }
            add(root, sb.toString());
        }
        List<Integer> ans = new ArrayList<>(puzzles.length);
        for(String puzzle: puzzles){
            char[] chars = puzzle.toCharArray();
            Arrays.sort(chars);
            ans.add(find(root, new String(chars), 0, puzzle.charAt(0)));
        }
        return ans;
        
    }
    
    void add(DirTreeNode root, String word){
        DirTreeNode cur = root;
        for(char c : word.toCharArray()){
            int idx = c - 'a';
            if(cur.child[idx] == null){
                cur.child[idx] = new DirTreeNode();
            }
            cur = cur.child[idx];
        }
        cur.frequency++;
    }
    
    int find(DirTreeNode node, String puzzle, int pos, char firstChar){
        if(node == null){
            return 0;
        }
        if(pos == 7){
            return node.frequency;
        }
        int sum = find(node.child[puzzle.charAt(pos) - 'a'], puzzle, pos + 1, firstChar);
        if(puzzle.charAt(pos) != firstChar){
            sum += find(node, puzzle, pos + 1, firstChar);
        }
        return sum;
    }
}

class DirTreeNode {
    
    int frequency;
    DirTreeNode[] child;
    
    public DirTreeNode(){
        frequency = 0;
        child = new DirTreeNode[26];
    }
}
