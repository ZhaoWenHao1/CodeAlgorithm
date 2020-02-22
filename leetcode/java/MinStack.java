import java.util.Vector;

class MinStack {

    Vector<Integer> stk = null;
    Vector<Integer> sort = null;
    /** initialize your data structure here. */
    public MinStack() {
        stk = new Vector<>();
        sort = new Vector<>();
    }
    
    public void push(int x) {
        stk.add(x);

//        sort.add(x);
//        for(int i = sort.size();i > 0;i--)
//        {
//            if(sort.get(i) > sort.get(i-1))
//            {
//                int tmp = sort.get(i-1);
//                sort.set(i-1, sort.get(i));
//                sort.set(i, tmp);
//            }
//            else
//                break;
//        }
        //对sort向量进行排序
        // 二分查找，再插入

    }
    
    /*public void pop() {
        
    }
    
    public int top() {
        
    }
    
    public int getMin() {
        
    }*/

    /*public int binarySearch(int x)
    {
        int low = 0, high = sort.size();
        int mid ;
        while(low < high)
        {
            mid = (low + high)/2;
            if(sort.get(mid) < x)
            {
                low = mid
            }
        }
    }*/

}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */