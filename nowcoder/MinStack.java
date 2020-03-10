/**
 * @author happyzhao
 * @data 2020/3/6 17:36
 * @type 华为
 * @question 最小栈
 */
class MinStack {

    int[] nums = null;
    int pos = -1;//当前栈顶下标
    int size = 100;
    int min = Integer.MAX_VALUE;
    /** initialize your data structure here. */
    public MinStack() {
        nums = new int[size];
    }

    public void push(int x) {
        if(pos == size-1)//扩容
        {
            size = size*2;
            int[] tem = new int[size];
            System.arraycopy(nums,0,tem,0,size/2);
            nums = tem;
        }
        nums[++pos] = x;
        min = Math.min(min,x);
    }

    public void pop() {
        if(min == nums[pos])
        {
            min = Integer.MAX_VALUE;
            for(int i = 0;i < pos;i++)
            {
                if(min > nums[i])
                    min = nums[i];
            }
        }
        pos--;

    }

    public int top() {
        return nums[pos];
    }

    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
