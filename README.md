# Description

该仓库用于记录各种算法及其实现。

首先，数据结构的本质只有两种：

- 数组（顺序存储）: 数组中间增和删 O(N)，改和查 O(1)
- 链表（链式存储）: 链表中间增和删 O(1)，改和查 O(N)


「队列」、「栈」既可以使用链表也可以使用数组实现。用数组实现，就要处理扩容缩容的问题；用链表实现，没有这个问题，但需要更多的内存空间存储节点指针。

「图」也有两种表示方法，邻接表就是链表，邻接矩阵就是二维数组。邻接矩阵判断连通性迅速，并可以进行矩阵运算解决一些问题，但是如果图比较稀疏的话很耗费空间。邻接表比较节省空间，但是很多操作的效率上肯定比不过邻接矩阵。

「散列表」就是通过散列函数把键映射到一个大数组里。而且对于解决散列冲突的方法，拉链法需要链表特性，操作简单，但需要额外的空间存储指针；线性探查法就需要数组特性，以便连续寻址，不需要指针的存储空间，但操作稍微复杂些。

「树」，用数组实现就是「堆」，因为「堆」是一个完全二叉树，用数组存储不需要节点指针，操作也比较简单；用链表实现就是很常见的那种「树」，因为不一定是完全二叉树，所以不适合用数组存储。为此，在这种链表「树」结构之上，又衍生出各种巧妙的设计，比如二叉搜索树、AVL 树、红黑树、区间树、B 树等等，以应对不同的问题。

对于所有的数据结构，关键在于如何遍历访问？

- 线性遍历访问：以 for/while 迭代为代表
- 非线性遍历访问：以递归为代表。

具体到数据结构，又可以分为两类：线和面。

对于数组就是典型的线性迭代：

```java
void traverse(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        // 迭代访问 arr[i]
    }
}
```

链表兼具线性迭代和递归两种方式：

```java
/* 基本的单链表节点 */
class ListNode {
    int val;
    ListNode next;
}

void traverse(ListNode head) {
    for (ListNode p = head; p != null; p = p.next) {
        // 迭代访问 p.val
    }
}

void traverse(ListNode head) {
    // 递归访问 head.val
    traverse(head.next);
}
```

若链表拥有多个分叉，即为面，以二叉树的遍历为例，是典型的非线性递归遍历结构：

```java
/* 基本的二叉树节点 */
class TreeNode {
    int val;
    TreeNode left, right;
}

void traverse(TreeNode root) {
    // 前序，也就是即将进入左子节点时
    traverse(root.left);
    // 中序，刚从左子节点回来，即将进入右子节点时
    traverse(root.right);
    // 后序，刚从右子节点回来
}
```

当扩展为多叉树时，遍历方式如下：

```java
/* 基本的 N 叉树节点 */
class TreeNode {
    int val;
    TreeNode[] children;
}

void traverse(TreeNode root) {
    // 递归遍历所有的子节点（又称为选择列表）
    for (TreeNode child : root.children)
        // 做选择
        traverse(child);
        // 撤销选择（回溯）
}
```

本文将首先介绍线性数据结构（链表、数组）的处理方法：

- 左右指针（left/right）：两个指针相向而行或相背而行。通常用于在数组中搜索和遍历，很多时候需要数组有序，例如两数之和、反转数组等。
- 快慢指针（fast/slow）：两个指针同向而行，一快一慢。通常用于解决链表中的问题，例如判定链表是否存在循环、寻找链表中点、判断链表是否相交等。
- 滑动窗口（left/right）：滑动窗口本质上是拥有固定距离的两个指针。

随后将介绍二叉乃至多叉树的相关算法：

- 回溯（DFS）
- BFS

可能还会涉及一些图的算法。

最后将对常见的算法类别进行具体练习：

- Greedy：贪心算法
- DC(Divide and conquer)：分治算法
- DP(Dynamic programming)：动态规划

# 滑动窗口

因为滑动窗口很多时候都是在处理字符串相关的问题，因此遇到字符串的问题可以特别留意！

滑动窗口技巧的思路非常简单，就是维护一个窗口，不断滑动，然后更新答案：

```c++
int left = 0, right = 0;

while (left < right && right < s.size()) {
    // 增大窗口
    window.add(s[right]);
    right++;
    
    while (window needs shrink) {
        // 缩小窗口
        window.remove(s[left]);
        left++;
    }
}

```

具体算法如下，其中两处 `...` 表示的更新窗口数据的地方。


```java
/* 滑动窗口算法框架 */
void slidingWindow(string s) {
    // 用合适的数据结构记录窗口中的数据
    unordered_map<char, int> window;
    
    int left = 0, right = 0;
    while (right < s.size()) {
        // c 是将移入窗口的字符
        char c = s[right];
        window.add(c)
        // 增大窗口
        right++;
        // 进行窗口内数据的一系列更新
        ...

        /*** debug 输出的位置 ***/
        // 注意在最终的解法代码中不要 print
        // 因为 IO 操作很耗时，可能导致超时
        printf("window: [%d, %d)\n", left, right);
        /********************/
        
        // 判断左侧窗口是否要收缩
        while (left < right && window needs shrink) {
            // d 是将移出窗口的字符
            char d = s[left];
            window.remove(d)
            // 缩小窗口
            left++;
            // 进行窗口内数据的一系列更新
            ...
        }
    }
}
```

# 回溯算法（DFS）

其实回溯算法和 DFS 具有细微的差别：

- 回溯算法：本质上是在遍历树枝
- DFS：本质上是在遍历节点

抽象地说，解决一个回溯问题，实际上就是遍历一棵 **决策树** 的过程，树的每个叶子节点存放着一个合法答案。你把整棵树遍历一遍，把叶子节点上的答案都收集起来，就能得到所有的合法答案。

站在回溯树的一个节点上，你只需要思考 3 个问题：

1、路径：也就是已经做出的选择。

2、选择列表：也就是你当前可以做的选择。

3、结束条件：也就是到达决策树底层，无法再做选择的条件。

其代码的框架如下：

```python
result = []
def backtrack(路径, 选择列表):
    if 满足结束条件:
        result.add(路径)
        return
    
    for 选择 in 选择列表:
        排除不合法的选择
        做选择
        backtrack(路径, 选择列表)
        撤销选择
```

其核心就是 for 循环里面的递归，在递归调用之前「做选择」，在递归调用之后「撤销选择」，特别简单。

# BFS

BFS 的核心思想就是把一些问题抽象成图，从一个点开始，向四周开始扩散。一般来说，我们写 BFS 算法都是用「队列」这种数据结构，每次将一个节点周围的所有节点加入队列。

BFS 相对 DFS 的最主要的区别是：**BFS 找到的路径一定是最短的，但代价就是空间复杂度可能比 DFS 大很多**，至于为什么，我们后面介绍了框架就很容易看出来了。

由于 BFS 的这个特性，因此 BFS 出现的场景通常是让你在一幅「图」中找到从起点 `start` 到终点 `target` 的最近距离。

这个广义的描述可以有各种变体，比如走迷宫，有的格子是围墙不能走，从起点到终点的最短距离是多少？如果这个迷宫带「传送门」可以瞬间传送呢？

再比如说两个单词，要求你通过某些替换，把其中一个变成另一个，每次只能替换一个字符，最少要替换几次？

再比如说连连看游戏，两个方块消除的条件不仅仅是图案相同，还得保证两个方块之间的最短连线不能多于两个拐点。你玩连连看，点击两个坐标，游戏是如何判断它俩的最短连线有几个拐点的？

# 贪心算法

贪心算法通常将求解过程分为若干个步骤，每个步骤均选择当下的最优解，即局部最优解，可以使用贪心算法解决的问题往往满足全局最优解即为局部最优解的性质。

贪心算法的基本步骤如下：

- 步骤1：从某个初始解出发；
- 步骤2：采用迭代的过程，当可以向目标前进一步时，就根据局部最优策略，得到一部分解，缩小问题规模；
- 步骤3：将所有解综合起来，得到最优解

## 区间调度问题

给定若干个闭区间，计算这些区间中最多有几个互不相交的区间。

该问题共有多个贪心策略，正确的策略是贪心选择**最早结束的活动区间**。

![证明贪心即最优](https://happytsing-figure-bed.oss-cn-hangzhou.aliyuncs.com/uestc/advanced_algorithm/image-20221203153348736.png)

假设 $i_1$, $i_2$,...,$i_r$ 的选择上贪心策略和最优策略相同，当选择 $i_{r+1}$ 时，如图所示，如果贪心和最优的选择不同，此时贪心的选择才是最优选择。

由此证明了，贪心策略就是最优策略。

算法：

- 按照结束时间排序
- 遍历所有区间，若不冲突，则选择。

> 当区间含有权重时，需要使用动态规划。

## 硬币兑换问题

给定若干个数值的硬币，计算最少需要多少个硬币，可以兑换出给定金额。

eg1:

- currency = 1, 5, 10, 25, 100
- total = 140

此时贪心选择面额最大的硬币即可，即：100, 25, 10, 5

但若硬币的面额特殊一点，就无法使用贪心算法。

eg2:

- currency = 1, 5, 10, 25, 70, 100
- total = 140

此时贪心算法会得到 eg1 的结果，但最优解是：70, 70

其原因是，货币之间的兑换关系不同，这种情况需要使用动态规划解决。

## 背包问题

给定 n 个物品和一个容量为 C 的背包，物品 i 的重量是 Wi,其价值为 Vi,背包问题是如何选择入背包的物品，使得装入背包的物品的总价值最大，注意和0/1背包的区别，在当前背包问题中 **可以将物品的一部分装入背包，但不能重复装入** 。

正确的贪心策略是选择单位价值最大的物品放入背包。

注意：若为 01 背包，即要么全部放入，要么不选择，则不能使用贪心算法，而必须动态规划。

## 最优装载

有一批集装箱要装上一艘载重量为 C 的轮船。其中集装箱 i 的重量为 Wi。最优装载问题要求确定在 **装载体积不受限制** 的情况下，将尽可能多的集装箱装上轮船。

贪心策略：由于体积不限，因此采用重量最轻者先装的贪心选择策略。

> 该问题和背包问题本质上是相同的

## 单源最短路径

给定 **带权有向图**  G =(V,E)，其中每条边的权是非负实数。另外，还给定V中的一个顶点，称为源。现在要计算从源到所有其他各顶点的最短路长度。这里路的长度是指路上各边权之和。这个问题通常称为单源最短路径问题。

Dijkstra 算法是解单源最短路径问题的贪心算法。

其基本思想是，设置顶点集合S并不断地作贪心选择来扩充这个集合。一个顶点属于集合S当且仅当从源到该顶点的最短路径长度已知。
		
- 初始时，S中仅含有源
- 设u是G的某一个顶点，把从源到u且中间只经过S中顶点的路称为从源到u的特殊路径，并用数组dist记录当前每个顶点所对应的最短特殊路径长度
- Dijkstra算法每次从V-S中取出具有最短特殊路长度的顶点u，将u添加到S中，同时对数组dist作必要的修改。
- 一旦S包含了所有V中顶点，dist就记录了从源到所有其他顶点之间的最短路径长度。

## 最小生成树

给定 **带权无向连通图** G =(V,E)，即一个网络。E 中每条边 (v,w) 的权为 c[v][w]。如果 G 的子图 G’ 是一棵 **包含 G 的所有顶点的树** ，则称 G’ 为 G 的生成树。

生成树上各边权的总和称为该生成树的耗费。在 G 的所有生成树中，**耗费最小的生成树称为 G 的最小生成树**

构造最小生成树的 Prim 算法和 Kruskal 算法均为贪心算法设计策略，都利用了最小生成树的性质：

设 G=(V,E) 是连通带权图，U是V的真子集。如果 (u,v)∈E，且 u∈U，v∈V-U，且在所有这样的边中，(u,v) 的权 c[u][v]最小，那么一定存在 G 的一棵最小生成树，它以(u,v)为其中一条边，该性质称为 **MST 性质**。

### Prim 算法
### Kruskal 算法

#TODO 参考 ppt 贪心算法实例合集

# 分治算法

> Ref：[分治算法详解及经典例题](https://www.cnblogs.com/chihaoyuIsnotHere/p/10129475.html)

分治算法的设计思想为：将一个难以直接解决的大问题，分割成若干个规模较小的相同问题，逐个击破，分而治之。

分治策略是：对于一个规模为n的问题，若该问题可以容易地解决（比如说规模 $n$ 较小）则直接解决，否则将其分解为 $k$ 个规模较小的子问题，这些子问题互相独立且与原问题形式相同，递归地解这些子问题，然后将各子问题的解合并得到原问题的解。这种算法设计策略叫做分治法。

如果原问题可分割成 $k$ 个子问题（$1<k≤n$），且这些子问题都可解并可利用这些子问题的解求出原问题的解，那么这种分治法就是可行的。由分治法产生的子问题往往是原问题的较小模式，这就为使用递归技术提供了方便。在这种情况下，反复应用分治手段，可以使子问题与原问题类型一致而其规模却不断缩小，最终使子问题缩小到很容易直接求出其解。这自然导致递归过程的产生。分治与递归像一对孪生兄弟，经常同时应用在算法设计之中，并由此产生许多高效算法。

Q：分治算法使用哪些情况？

A：当问题满足如下条件时，可以选用分治算法：

- 该问题的规模缩小到一定程度就可以容易解决
- 该问题可以分解为若干个规模较小的相同问题，即该问题具有 **最优子结构** 性质
- 利用该问题分解出的子问题的解可以 **合并** 为该问题的解
- 该问题所分解出的各个 **子问题是相互独立** 的，即子问题之间不包含公共的子子问题

## 二分查找

二分查找的前提是查找的数组时有序的。


## 归并排序

给定若干个乱序数字，从小到大排序后输出。

首先我们判断分治算法是否适合于解决归并排序，逐条对应：

- 比较两个数字大小很容易
- 将 n 个数字排序和 n/2 个数字排序相同，即该问题可以分解为若干个规模较小的相同问题。
- 子问题的解可以合并为原问题的解
- 子问题互相独立，不存在重叠子问题

![归并示例](https://images2015.cnblogs.com/blog/1024555/201612/1024555-20161218163120151-452283750.png)


## 快速排序

给定若干个乱序数字，从小到大排序后输出。


## 求最大连续和

给定长为 n 的序列，求最大连续和。例如（1，-2，3，10，-4，7，2，-5），最大连续和为 18。

该问题的基本思路是使用枚举法，三重嵌套循环，复杂度为 $n^3$，使用分治法可将复杂度降至 $nlogn$

连续子序列的最大和主要由这三部分子区间里元素的最大和得到：

- 第 1 部分：子区间 [left, mid]；
- 第 2 部分：子区间 [mid + 1, right]；
- 第 3 部分：横跨两个区间，此时 nums[mid] 一定会被选取。

对这三个部分求最大值即可。

说明：考虑第 3 部分跨越两个区间的连续子数组的时候，可以从中间向两边扩散，扩散到底 选出最大值。

![求最大连续和](https://pic.leetcode-cn.com/1621840913-dcvfVD-image.png)


## 汉诺塔问题

有 A,B,C 三根柱子，A 上面有 n 个盘子，我们想把 A 上面的盘子移动到 C 上，但是要满足以下三个条件：

- 每次只能移动一个盘子;
- 盘子只能从柱子顶端滑出移到下一根柱子;
- 盘子只能叠在比它大的盘子上。

![汉诺塔问题](https://pic.leetcode-cn.com/7043dec266338d396d186805072660d5f9517e2c1008af80d461b553c5b32470-p1.png)

对应分治算法的条件：

- 移动一个盘子很容易
- 移动 n 个盘子，和移动 n - 1 个盘子相同，即该问题可以分解为若干个规模较小的相同问题
- 子问题的解可以合并为原问题的解
- 子问题互相独立，不存在重叠子问题

假设 n = 1,只有一个盘子，很简单，直接把它从 A 中拿出来，移到 C 上；

如果 n = 2 呢？这时候我们就要借助 B 了，因为小盘子必须时刻都在大盘子上面，共需要 3 步：

- A1 -> B
- A2 -> C
- B -> C

此时就实现了将两个盘子从 A 移动到 C，但是 n > 2 呢？ 思路是相同的，只要把 n 个盘看作两个部分：1 个盘子 和 n-1 个盘子。

你可能会问：“那 n - 1 个盘子是怎么从 A 移到 C 的呢？”

注意，当你在思考这个问题的时候，就将最初的 n 个盘子从 A 移到 C 的问题，转化成了将 n - 1 个盘子从 A 移到 C 的问题， 依次类推，直至转化成 1 个盘子的问题时，问题也就解决了。这就是分治的思想。


# 动态规划

在谈动态规划之前，首先看看如下两个定义：

- 最优子结构：问题的最优解中，包含着子问题的最优解，反过来说，可以通过子问题的最优解，推导出问题的最优解
- 重叠子问题：用来求解原问题的递归算法反复地解同样的子问题，而不是总是在产生新的子问题。对两个子问题来说，如果它们确实是相同的子问题，只是作为不同问题的子问题出现的话，则他们是重叠的。

当拥有最优子结构，且 **子问题相互独立** 时，能且只能使用分治算法；当拥有最优子结构，且 **存在重叠子问题** 时，动态规划是更好的算法。