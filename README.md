# Description

该仓库用于记录各种算法及其实现：

- Greedy：贪心算法
- DP(Dynamic programming)：动态规划
- DC(Divide and conquer)：分治算法

# 贪心算法

## 区间调度问题

给定若干个闭区间，计算这些区间中最多有几个互不相交的区间。

该问题共有多个贪心策略，正确的策略是贪心选择最早结束的活动区间。

![证明贪心即最优](https://happytsing-figure-bed.oss-cn-hangzhou.aliyuncs.com/uestc/advanced_algorithm/image-20221203153348736.png)

假设 $i_1$, $i_2$,...,$i_r$ 的选择上贪心策略和最优策略相同，当选择 $i_{r+1}$ 时，如图所示，如果贪心和最优的选择不同，此时贪心的选择才是最优选择。

由此证明了，贪心策略就是最优策略。

算法：

- 按照结束时间排序
- 遍历所有区间，若不冲突，则选择。