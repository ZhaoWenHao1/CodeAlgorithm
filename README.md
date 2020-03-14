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

