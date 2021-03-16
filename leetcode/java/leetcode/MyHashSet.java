package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: CodeAlgorithm
 * @description: MyHashSet
 * @author: zwh
 * @create: 2021-03-13 13:00
 **/
 class MyyHashSet {
    
    int capacity = 16;
    int count = 0;
    float LOAD_FACTOR = 0.75f;
    Node[] table;
    
    class Node{
        int hash;
        int value;
        Node next;
        Node(int hash, int value, Node next){
            this.hash = hash;
            this.value = value;
            this.next = next;
        }
    }
    /** Initialize your data structure here. */
    public MyyHashSet() {
        table = new Node[capacity];
    }

    public void add(int key) {
        int n = capacity;
        // 是否扩容
        if(count > capacity * LOAD_FACTOR){
            n = resize();
        }
        Node node = new Node(hash(key), key, null);
        if(table[node.hash & (n-1)] == null){
            table[node.hash & (n-1)] = node;
            count++;
        }else{
            Node p = table[node.hash & (n-1)];
            while(p.next != null && p.value != key) p = p.next;
            if(p.value == key){
                return;
            }
            p.next = node;
            count++;
        }
    }

    public void remove(int key) {
        int idx = hash(key) & (capacity - 1);
        Node p = table[idx];
        if(p == null){
            return;
        }
        if(p.value == key){
            table[idx] = p.next;
            count--;
            return;
        }
        Node bp = p;
        p = p.next;
        while(p != null && p.value != key) {
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

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int idx = hash(key) & (capacity - 1);
        Node p = table[idx];
        while(p != null && p.value != key) p = p.next;
        return p != null;
    }
    
    private int hash(Integer key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    
    private int resize(){
        capacity = (capacity << 1);
        Node[] oldTable = table;
        table = new Node[capacity];
        for(int i = 0;i < capacity >> 1;i++){
            Node n = oldTable[i];
            if(n != null){
                Node p = n;
                while(p != null){
                    if(table[p.hash & (capacity-1)] == null){
                        table[p.hash & (capacity-1)] = new Node(p.hash, p.value, null);
                    }else{
                        Node pn = table[p.hash & (capacity-1)];
                        while(pn.next != null) pn = pn.next;
                        pn.next = new Node(p.hash, p.value, null);
                    }
                    p = p.next;
                }
                table[n.hash & (capacity - 1)] = oldTable[i];
            }
        }
        return capacity;
    }

    public static void main(String[] args) {
        String[] strings= {
                "MyHashSet","remove","add","contains","add","contains","add","add","add","add","contains","remove","contains","contains","contains","add","contains","add","remove","add","add","remove","contains","add","contains","add","add","add","add","remove","add","remove","add","remove","contains","contains","add","add","add","add","add","contains","remove","add","add","add","contains","contains","add","add","contains","remove","add","contains","add","remove","remove","add","contains","add","add","add","add","add","remove","add","add","contains","contains","contains","add","add","contains","contains","add","add","add","contains","contains","add","add","remove","add","add","contains","add","remove","add","contains","contains","remove","remove","add","contains","remove","contains","add","add","contains","add","add"};
        int[] nums = {0,28,82,82,15,82,33,7,93,61,93,15,33,6,15,7,15,89,66,16,7,81,89,98,98,40,88,29,81,17,83,33,44,22,82,82,8,63,13,19,89,41,67,37,17,57,41,30,23,82,23,51,80,81,15,95,45,49,93,7,45,86,74,85,69,7,2,13,92,3,40,32,29,74,37,66,14,91,82,99,84,87,56,49,85,34,32,38,76,34,19,81,83,30,15,41,42,5,19,26,33};
        Set<Integer> set = new HashSet<>(); 
        MyyHashSet myHashSet = new MyyHashSet();
        for(int i = 1;i < strings.length;i++){
            if(nums[i] == 81){
                System.out.println();
            }
            switch (strings[i]){
                case "remove":
                    set.remove(nums[i]);
                    myHashSet.remove(nums[i]);
                    break;
                case "add":
                    set.add(nums[i]);
                    myHashSet.add(nums[i]);
                    break;
                case "contains":
                    if(set.contains(nums[i]) != myHashSet.contains(nums[i])){
                        System.out.println(nums[i]);
                        System.out.println(set.contains(nums[i]));
                        myHashSet.contains(nums[i]);
                    }
                    break;
            }
            if(i == 30){
                System.out.println(i);
            }
            for (Integer integer : set) {
                if(!myHashSet.contains(integer)){
                    System.out.println(integer);
                }
            }
           
        }
    }
}

public class MyHashSet {

    private Set<Integer> set;

    /** Initialize your data structure here. */
    public MyHashSet() {
        set = new HashSet<>();
    }

    public void add(int key) {
        set.add(key);
    }

    public void remove(int key) {
        set.remove(key);
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return set.contains(key);
    }
}
