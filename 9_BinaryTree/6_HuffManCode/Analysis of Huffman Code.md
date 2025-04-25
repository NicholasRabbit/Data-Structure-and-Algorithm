#### 一，霍夫曼编码

[Huffman Code](https://coolshell.cn/articles/7459.html)

**1，什么是霍夫曼编码？**

霍夫曼是一种利用霍夫曼书的特点，把字符进行压缩的数据压缩算法，常用在电讯通信中。

**2，霍夫曼编码如何实现数据压缩？**

例如：一段文字中肯定有不断重复的字符。霍夫曼编码的做法是统计好这些字符出现的次数，把次数作为霍夫曼树的权重，即节点的值。把这些字符根据权重从小到达排序，然后放到霍夫曼树中。

根据霍夫曼树“权重大的离根节点近”的特点，构造字符的编码，出现次数越多的字符其编码越短。从而实现了数据压缩。

一般压缩率在20-90%之间。

参考：<a href="https://www.reddit.com/r/javaexamples/comments/3gatvr/intermediate_huffman_tree_encoding/">Huffman Code Demo</a>

#### 二，思路分析

1，把下面一段文字按霍夫曼编码，通过EditPlus计算共有183各ANSI字符

```txt
While many appliances have existed for centuries, the self-contained electric or gas powered appliances are a uniquely American innovation that emerged in the early twentieth century.
```

2，使用程序计算各个字符的数量，空格也要计算，不算空格的话解码后的文字没有空格导致无法阅读。
可放到Map中，key是byte类型的字符编码(其ANSCII码)，value就是数量，即它的权重。

3，创建一个 CharNode.java类，属性如下。把字符和次数包装进CharNode对象，然后把CharNode放到数组中，根据权重从小到达排序。

```java
public class CharNode{
    int weight;  //次数
    char "A";   //ANSCII字符
    CharNode left;  //左子节点
    charNode right; //右子节点
}
```

4，排号序的数组放到霍夫曼树中。此时各个字符都放到了叶子节点上，设置规则，向左遍历一次设置为0，向右遍历一次设置为1，直到找到字符。得到的值就是霍夫曼编码。

5，把上步中得到的霍夫曼编码和对应字符放到HuffmannCodeMap中。字符为key，霍夫曼编码为value。然后把需要编码的句子逐个字符转化为霍夫曼码，按顺序存储好发送即可。

6，解码的话，通过编码找对应的字符即可。





