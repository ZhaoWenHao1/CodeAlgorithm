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