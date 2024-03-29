#### 一,  二叉排序树删除节点思路

![1700203742010](note-images/1700203742010.png)

**第一种情况：删除的是叶子节点**

步骤：

1. 首先找到这个节点；
2. 判断是否是叶子节点。
   是叶子节点的话分为两种情况：
   - 二叉树只有一个根节点，它同时也是一个叶子节点，此时暂时不删除(实际程序处理时优先判断此情况)。
   - 一般的叶子节点；
3. 获取叶子节点的父节点，然后判断叶子节点是其左节点还是右节点，确定后即可进行删除。

**第二种情况：删除只有一个子节点的节点**

例如: 图中 14

步骤：

1，找到这个目标节点；

2， 找到其父节点；

3，找到这个目标节点的子节点，用该子节点替换目标节点即可。注意这个子节点有可能是左节点或右节点。

**第三种情况：删除有两个子节点的姐节点**

1，需要考虑这两个子节点下面还有节点，即还有子树的情况。

2，根据二叉排序树的特点：父节点比所有左子树上的值都小，父节点的右子树上的值都比父节点大。

因此从右子树找到最小值的节点放到目标节点，或者从左子树找到最大的节点放上去。

