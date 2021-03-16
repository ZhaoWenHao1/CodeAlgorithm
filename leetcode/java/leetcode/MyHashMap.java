package leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: CodeAlgorithm
 * @description: MyHashMap
 * @author: zwh
 * @create: 2021-03-14 14:27
 **/
public class MyHashMap {
    int capacity = 16;
    int count = 0;
    float LOAD_FACTOR = 0.75f;
    Node[] table;

    class Node{
        int hash;
        int key;
        int value;
        Node next;
        Node(int hash, int key, int value, Node next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
    /** Initialize your data structure here. */
    public MyHashMap() {
        table = new Node[capacity];
    }

    public void put(int key, int value) {
        int n = capacity;
        // 是否扩容
        if(count > capacity * LOAD_FACTOR){
            n = resize();
        }
        Node node = new Node(hash(key), key,value, null);
        if(table[node.hash & (n-1)] == null){
            table[node.hash & (n-1)] = node;
            count++;
        }else{
            Node p = table[node.hash & (n-1)];
            while(p.next != null && p.key != key) p = p.next;
            if(p.key == key){
                p.value = value;
                return;
            }
            p.next = node;
            count++;
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int idx = hash(key) & (capacity - 1);
        Node p = table[idx];
        while(p != null && p.key != key) p = p.next;
        if(p == null){
            return -1;
        }else{
            return p.value;
        }
    }
    

    public void remove(int key) {
        int idx = hash(key) & (capacity - 1);
        Node p = table[idx];
        if(p == null){
            return;
        }
        if(p.key == key){
            table[idx] = p.next;
            count--;
            return;
        }
        Node bp = p;
        p = p.next;
        while(p != null && p.key != key) {
            bp = p;
            p = p.next;
        }
        if(p == null){
            return;
        }
        bp.next = p.next;
        count--;
        return;
    }

    private int hash(Integer key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    private int resize() {
        capacity = (capacity << 1);
        Node[] oldTable = table;
        table = new Node[capacity];
        for (int i = 0; i < capacity >> 1; i++) {
            Node n = oldTable[i];
            if (n != null) {
                Node p = n;
                while (p != null) {
                    if (table[p.hash & (capacity - 1)] == null) {
                        table[p.hash & (capacity - 1)] = new Node(p.hash, p.key,p.value, null);
                    } else {
                        Node pn = table[p.hash & (capacity - 1)];
                        while (pn.next != null) pn = pn.next;
                        pn.next = new Node(p.hash,p.key, p.value, null);
                    }
                    p = p.next;
                }
//                table[n.hash & (capacity - 1)] = oldTable[i];
            }
        }
        return capacity;
    }

    public static void main(String[] args) {
        String s = "MyHashMap,put,put,get,get,put,get,remove,get";
        String ops = "[],[1,1],[2,2],[1],[3],[2,1],[2],[2],[2]";
        String[] ss = s.split(",");
        MyHashMap myHashMap = new MyHashMap();
        Map<Integer, Integer> map = new HashMap<>();
        List<String> collect = Arrays.stream(ops.split("\\[|\\]")).filter(stem -> stem.length() >= 1 && (Character.isDigit(stem.charAt(0)))).collect(Collectors.toList());
        for(int i = 1;i < ss.length;i++){
            switch (ss[i]){
                case "MyHashMap":
                    break;
                case "put":
                    String[] nums = collect.get(i-1).split(",");
                    myHashMap.put(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
                    map.put(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]));
                    break;
                case "get":
                    int key = Integer.parseInt(collect.get(i-1));
                    
                    if(map.get(key) != null && myHashMap.get(key) != map.get(key)){
                        System.out.println(key);
                    }
                    break;
                case "remove":
                    int key1 = Integer.parseInt(collect.get(i-1));
                    myHashMap.remove(key1);
                    map.remove(key1);
                    break;
            }
            
        }
    }
}
