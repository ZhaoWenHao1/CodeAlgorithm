package leetcode;

import java.util.Scanner;

/**
 * @author happyzhao
 * @data 2020/11/15 19:46
 * @type leetcode
 * @question
 */


public class SegmentTreeMax {
    class Node//节点
    {
        int start;//区间的左端点
        int end;//区间的右端点
        int data;//该节点的值
        int mark = 0;//延迟更新的标记

        public Node(int start, int end)//构造方法中传入左端点和右端点
        {
            this.start = start;
            this.end = end;
        }

        void addMark(int value)//做标记
        {
            this.mark += value;
        }

        void clearMark() {
            this.mark = 0;
        }

        public String toString() {
            return start + "-" + end;
        }
    }

    int[] base;
    Node[] nodes;

    public SegmentTreeMax(int[] nums) {
        this.base = nums;
        nodes = new Node[base.length << 2];
    }

    public void build(int index)//构造一颗线段树，传入下标
    {
        Node node = nodes[index];//取出该下标下的节点
        if (node == null)//根节点需要手动创建
        {
            nodes[index] = new Node(0, this.base.length - 1);
            node = nodes[index];
        }
        if (node.start == node.end)//如果这个线段的左端点等于右端点则这个点是叶子节点
            node.data = base[node.start];
        else//否则递归构造左右子树
        {

            int mid = (node.start + node.end) >> 1;//现在这个线段的中点
            nodes[(index << 1) + 1] = new Node(node.start, mid);//左孩子线段
            nodes[(index << 1) + 2] = new Node(mid + 1, node.end);//右孩子线段
            build((index << 1) + 1);//构造左孩子
            build((index << 1) + 2);//构造右孩子
            node.data = Math.max(nodes[(index << 1) + 1].data, nodes[(index << 1) + 2].data);//这个节点的值等于左右孩子中较小的那个
        }
    }

    /**
     * 查询某个区间的最大值
     *
     * @param index 当前区间的下标
     * @param start 待查询的区间的左端点
     * @param end   待查询的区间的左端点
     * @return 返回当前区间在待查询区间中的部分的最大值
     */
    public int query(int index, int start, int end) {
        Node node = nodes[index];
        if (node.start > end || node.end < start) return Integer.MIN_VALUE;//当前区间和待查询区间没有交集，那么返回一个极小值
        if (node.start >= start && node.end <= end) return node.data;//如果当前的区间被包含在待查询的区间内则返回当前区间的最大值
        pushDown(index); // 在返回左右子树的最小值之前，进行扩展操作
        return Math.max(query((index << 1) + 1, start, end), query((index << 1) + 2, start, end));//递归查询左子树和右子树
    }


    /**
     * 单点更新
     *
     * @param index  当前节点的下标
     * @param update 需要被更新的节点下标
     * @param date   更新增量
     */
    public void updateOne(int index, int update, int date)//更新一个节点
    {
        Node node = nodes[index];//获取这个下标所对应的的节点
        if (node.start == node.end)//
        {
            if (node.start == update) {
                node.data += date;
                return;
            }
        }
        int mid = (node.start + node.end) >> 1;
        if (update <= mid) updateOne((index << 1) + 1, update, date);//待更新节点在左子树
        else updateOne((index << 1) + 2, update, date);//待更新节点在右子树
        node.data = Math.max(nodes[(index << 1) + 1].data, nodes[(index << 1) + 2].data);//更新当前节点的值。
    }

    private void pushDown(int index)//把当前节点的标志值传给子节点
    {
        Node node = nodes[index];//获取该下标的节点
        if (node.mark != 0) {
            nodes[(index << 1) + 1].addMark(node.mark);//更新左子树的标志
            nodes[(index << 1) + 2].addMark(node.mark);//更新右子树的标志
            nodes[(index << 1) + 1].data += node.mark;//左子树的值加上标志值
            nodes[(index << 1) + 2].data += node.mark;//右子树的值加上标志值
            node.clearMark();//清除当前节点的标志值
        }
    }

    /**
     * 区间更新
     *
     * @param index 当前节点的下标
     * @param start 待更新的区间的左端点
     * @param end   待更新的区间的右端点
     * @param data  增量值
     */
    public void update(int index, int start, int end, int data) {
        Node node = nodes[index];//获取当前的节点
        if (node.start > end || node.end < start) return;//如果当前的节点代表的区间和待更新的区间毫无交集，则返回不处理。
        if (node.start >= start && node.end <= end)//如果当前的区间被包含在待查询的区间之内，则当前区间需要被标记上
        {
            node.data += data;
            node.addMark(data);
            return;
        }
        pushDown(index); // 在更新左右子树之前进行扩展操作
        update((index << 1) + 1, start, end, data);//更新左子树
        update((index << 1) + 2, start, end, data);//更新右子树
        node.data = Math.max(nodes[(index << 1) + 1].data, nodes[(index << 1) + 2].data);//更新当前节点的值
    }


    public static void main(String[] args) {
//        int[] nums = {5,9,7,4,6,1};
//        SegmentTreeMax segmentTreeMax = new SegmentTreeMax(nums);
//        segmentTreeMax.build(0);
//        int queryNum = 100;
//        for(int i = 0;i < queryNum;i++){
//            int a = RMQ.nextRandomInt(0,5), b = RMQ.nextRandomInt(0,5);
//            int t = Math.min(a, b);
//            b = a + b - t;
//            a = t;
//            if(segmentTreeMax.query(0,a,b) != RMQ.getMax(nums,a,b)){
//                System.out.println("error: " + segmentTreeMax.query(0,a,b) + " " + RMQ.getMax(nums,a,b));
//            }
//        }

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int len = sc.nextInt();
            int[] nums = new int[len];
            for (int i = 0; i < len; i++) {
                nums[i] = sc.nextInt();
            }
            SegmentTreeMax segmentTreeMax = new SegmentTreeMax(nums);
            segmentTreeMax.build(0);
            int q = sc.nextInt();
            for (int n = 0; n < q; n++) {
                int i = sc.nextInt(), j = sc.nextInt();
//                System.out.println(f[i-1][j-1]);
                System.out.println(segmentTreeMax.query(0, i-1, j-1));
            }
        }
    }


}



