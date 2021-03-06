# CodeAlgorithm
Code written when solving algorithm problems.



## leetcode

#### [29. 两数相除](https://leetcode-cn.com/problems/divide-two-integers/)

给定两个整数，被除数 `dividend` 和除数 `divisor`。将两数相除，要求不使用乘法、除法和 mod 运算符。 

- 首先明确int取值范围是[-2<sup>32</sup>,2<sup>32</sup>-1]，如果将被除数和除数都转换为正数再运算时，且被除数为-2<sup>32</sup>时就会导致溢出，所以应该将被除数和除数都转换为负数再进行运算。
- 处理一切特殊情况，除数为±1时，商为2<sup>32</sup>时
- 设被除数和除数同时转换为负数后为a,b，问题转换为求a除以b的结果，不断地翻倍扩大b为tb，即tb += tb，直至tb+tb小于a，或者tb+tb溢出,那么先得到了-tb除以b的商，然后再算a-tb除以b的商

#### [69. x 的平方根](https://leetcode-cn.com/problems/sqrtx/)

1. 袖珍计算器算法 O(1) O(1) 时间 空间

   ![1584114984986](https://i.loli.net/2020/03/14/6bIGWFzkAQvaEhy.png)

2. 二分查找 O(logN) O(1)

   当 x≥2 时，它的整数平方根一定小于 x/2 且大于 0，即 0<a<x/2。由于 a 一定是整数，此问题可以转换成在有序整数集中寻找一个特定值，因此可以使用二分查找。


3. 递归+位操作

   <img src="https://i.loli.net/2020/03/14/Y3ao7rp4MvwHFqG.png" alt="1584116589963"  />

![1584116605402](https://i.loli.net/2020/03/14/ih8uWMLsPfkljIG.png)

4. 牛顿法

   ![1584116746354](https://i.loli.net/2020/03/14/PtQvXogCked7WrI.png)

#### [283. 移动零](https://leetcode-cn.com/problems/move-zeroes/)

	给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

示例:

​		输入: [0,1,0,3,12]
​		输出: [1,3,12,0,0]

时间O(n)，空间O(1)

```java
public void moveZeroes(int[] nums) {
        int idx = 0;// 表示已整理数组的长度，0-idx都已整理好，为正确答案
        for(int i = 0;i < nums.length;i++){
            if(nums[i] != 0){
                nums[idx++] = nums[i];
            }
        }
        for(int i = idx;i < nums.length;i++){
            nums[i] = 0;
        }
    }
```

#### [365. 水壶问题](https://leetcode-cn.com/problems/water-and-jug-problem/)

你允许：

- 装满任意一个水壶
- 清空任意一个水壶
- 从一个水壶向另外一个水壶倒水，直到装满或者倒空

根据题目可知，对于两个壶，共可进行六种操作：

1. 把 X 壶的水灌进 Y 壶，直至灌满或倒空；
2. 把 Y 壶的水灌进 X 壶，直至灌满或倒空；
3. 把 X 壶灌满；
4. 把 Y 壶灌满；
5. 把 X 壶倒空；
6. 把 Y 壶倒空。

而如果直接用计算的方式来算出结果似乎是不可能的，最简单的方法就是搜索，每次有六种操作，相当于一个六叉树，但是由于该问题会导致递归层数太多，可将递归转换为队列，再用set记录访问过的状态（**自定义类一定要重写equals方法和hashCode方法**，否则无法判断状态是否已经访问过）

#### [945. 使数组唯一的最小增量](https://leetcode-cn.com/problems/minimum-increment-to-make-array-unique/)

**方法一：计数**
**思路**

由于 A[i] 的范围为 [0, 40000)，我们可以用数组统计出每个数出现的次数，然后对于每个重复出现的数，我们暴力地将它递增，直到它增加到一个没有重复出现的数为止。但这样的方法的时间复杂度较大，可以达到 O(N^2)O(N 
2)，例如数组 A 中所有元素都是 1 的情况。

因此，**我们不能对重复出现的数暴力的进行递增**，而是用以下的做法：当我们找到一个没有出现过的数的时候，将之前某个重复出现的数增加成这个没有出现过的数。注意，这里 「之前某个重复出现的数」 是可以任意选择的，它并不会影响最终的答案，因为将 P 增加到 X 并且将 Q 增加到 Y，与将 P 增加到 Y 并且将 Q 增加到 X 都需要进行 (X + Y) - (P + Q) 次操作。

例如当数组 A 为 [1, 1, 1, 1, 3, 5] 时，我们发现有 3 个重复的 1，且没有出现过 2，4 和 6，因此一共需要进行 (2 + 4 + 6) - (1 + 1 + 1) = 9 次操作。

算法

首先统计出每个数出现的次数，然后从小到大遍历每个数 x：

- 如果 x 出现了两次以上，就将额外出现的数记录下来（例如保存到一个列表中）；

- 如果 x 没有出现过，那么在记录下来的数中选取一个 v，将它增加到 x，需要进行的操作次数为 x - v。

我们还可以对该算法进行优化，使得我们不需要将额外出现的数记录下来。还是以 [1, 1, 1, 1, 3, 5] 为例，当我们发现有 3 个重复的 1 时，我们先将操作次数减去 1 + 1 + 1。接下来，当我们发现 2，4 和 6 都没有出现过时，我们依次将操作次数增加 2，4 和 6。这种优化方法在方法二中也被使用。

注意事项

虽然 A[i] 的范围为 [0, 40000)，但我们有可能会将数据递增到 40000 的两倍 80000。这是因为在最坏情况下，数组 A 中有 40000 个 40000，这样要使得数组值唯一，需要将其递增为 [40000, 40001, ..., 79999]，因此用来统计的数组需要开到 80000。

[code](https://github.com/ZhaoWenHao1/CodeAlgorithm/blob/master/leetcode/java/leetcode/MinIncrementForUnique.java)
#### [820. 单词的压缩编码](https://leetcode-cn.com/problems/short-encoding-of-words/)
字典树！！！
模糊搜索！！

#### [887. 鸡蛋掉落](https://leetcode-cn.com/problems/super-egg-drop/)[hard]

https://leetcode-cn.com/problems/super-egg-drop/solution/ji-dan-diao-luo-by-leetcode-solution/



#### [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

#### [15. 三数之和](https://leetcode-cn.com/problems/3sum/)

#### [18. 四数之和](https://leetcode-cn.com/problems/4sum/)
