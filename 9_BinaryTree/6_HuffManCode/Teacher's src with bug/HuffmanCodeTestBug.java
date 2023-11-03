package com.test.huffman.test;

import com.test.huffman.CharMapAndByteTool;

import java.io.*;
import java.util.*;

/*
 * 个人注：
 * 注意：老师源码把霍夫曼长字符串最后的一段转为byte再解码的逻辑错误。如果最后是0开头的，如"011"，那么byte是3，还原老师没有补前面的0，
 * 按照老师的代码逻辑会得到“11”，而老师的例子中恰好最后是“11100”，前面没有0，导致没有发现这个bug。
 * 本代码本人已添加打印语句，并通过修改原文，测试除了这个问题。
 * 见，66行
 * */
public class HuffmanCodeTestBug {

	public static void main(String[] args) {
		

		//String content = "i like like like java do you like a java";	//老师的原文，生成的编码正好后面最后一个是"111000"。
		String content = "i like like like java do you like a ja";   //这里删掉两个，就出问题了。
		byte[] contentBytes = content.getBytes();
		System.out.println(contentBytes.length); //40
		
		byte[] huffmanCodesBytes= huffmanZip(contentBytes);
		System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodesBytes) + " 长度= " + huffmanCodesBytes.length);
		
		
		//测试一把byteToBitString方法
		//System.out.println(byteToBitString((byte)1));
		byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
		
		System.out.println("原来的字符串=" + new String(sourceBytes)); // "i like like like java do you like a java"

		

		
	}
	


	//完成数据的解压
	//思路
	//1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
	//   重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
	//2.  赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"
	
	
	//编写一个方法，完成对压缩数据的解码
	/**
	 * 
	 * @param huffmanCodes 赫夫曼编码表 map
	 * @param huffmanBytes 赫夫曼编码得到的字节数组
	 * @return 就是原来的字符串对应的数组
	 */
	private static byte[] decode(Map<Byte,String> huffmanCodes, byte[] huffmanBytes) {
		
		//1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
		StringBuilder stringBuilder = new StringBuilder();
		//将byte数组转成二进制的字符串
		for(int i = 0; i < huffmanBytes.length; i++) {
			byte b = huffmanBytes[i];
			//判断是不是最后一个字节
			boolean flag = (i == huffmanBytes.length - 1);
			stringBuilder.append(byteToBitString(!flag, b));
		}

		System.out.println("length of decode string ==> " + stringBuilder.length());   //解码后的字符串长度变小了。。。解码错误

		//把字符串安装指定的赫夫曼编码进行解码
		//把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
		Map<String, Byte>  map = new HashMap<String,Byte>();
		for(Map.Entry<Byte, String> entry: huffmanCodes.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		
		//创建要给集合，存放byte
		List<Byte> list = new ArrayList<>();
		//i 可以理解成就是索引,扫描 stringBuilder 
		for(int  i = 0; i < stringBuilder.length(); ) {
			int count = 1; // 小的计数器
			boolean flag = true;
			Byte b = null;
			
			while(flag) {
				//1010100010111...
				//递增的取出 key 1 
				String key = stringBuilder.substring(i, i+count);//i 不动，让count移动，指定匹配到一个字符
				b = map.get(key);
				if(b == null) {//说明没有匹配到
					count++;
				}else {
					//匹配到
					flag = false;
				}
			}
			list.add(b);
			i += count;//i 直接移动到 count	
		}
		//当for循环结束后，我们list中就存放了所有的字符  "i like like like java do you like a java"
		//把list 中的数据放入到byte[] 并返回
		byte b[] = new byte[list.size()];
		for(int i = 0;i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
		
	}
 	
	/**
	 * 将一个byte 转成一个二进制的字符串, 如果看不懂，可以参考我讲的Java基础 二进制的原码，反码，补码
	 * @param b 传入的 byte
	 * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
	 * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
	 */
	private static String byteToBitString(boolean flag, byte b) {
		//使用变量保存 b
		int temp = b; //将 b 转成 int
		//如果是正数我们还存在补高位
		if(flag) {
			temp |= 256; //按位与 256  1 0000 0000  | 0000 0001 => 1 0000 0001
		}
		String str = Integer.toBinaryString(temp); //返回的是temp对应的二进制的补码
		if(flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}
	
	//使用一个方法，将前面的方法封装起来，便于我们的调用.
	/**
	 * 
	 * @param bytes 原始的字符串对应的字节数组
	 * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
	 */
	private static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes = getNodes(bytes);
		//根据 nodes 创建的赫夫曼树
		Node huffmanTreeRoot = createHuffmanTree(nodes);
		//对应的赫夫曼编码(根据 赫夫曼树)
		Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
		huffmanCodes.forEach((k,v) -> {
			System.out.println(k + "=" + v);
		});
		//根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		return huffmanCodeBytes;
	}
	
	
	//编写一个方法，将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
		
		//1.利用 huffmanCodes 将  bytes 转成  赫夫曼编码对应的字符串
		StringBuilder stringBuilder = new StringBuilder();
		//遍历bytes 数组 
		for(byte b: bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}
		
		System.out.println("length of encode string ==> " + stringBuilder.toString().length());
		CharMapAndByteTool.printBytesString(stringBuilder.toString());
		
		//将 "1010100010111111110..." 转成 byte[]
		
		//统计返回  byte[] huffmanCodeBytes 长度
		//一句话 int len = (stringBuilder.length() + 7) / 8;
		int len;
		if(stringBuilder.length() % 8 == 0) {
			len = stringBuilder.length() / 8;
		} else {
			len = stringBuilder.length() / 8 + 1;
		}
		//创建 存储压缩后的 byte数组
		byte[] huffmanCodeBytes = new byte[len];
		int index = 0;//记录是第几个byte
		for (int i = 0; i < stringBuilder.length(); i += 8) { //因为是每8位对应一个byte,所以步长 +8
				String strByte;
				if(i+8 > stringBuilder.length()) {//不够8位
					strByte = stringBuilder.substring(i);
				}else{
					strByte = stringBuilder.substring(i, i + 8);
				}	
				//将strByte 转成一个byte,放入到 huffmanCodeBytes
				huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte, 2);
				index++;
		}
		return huffmanCodeBytes;
	}
	
	//生成赫夫曼树对应的赫夫曼编码
	//思路:
	//1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
	//   生成的赫夫曼编码表{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
	static Map<Byte, String> huffmanCodes = new HashMap<Byte,String>();
	//2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个StringBuilder 存储某个叶子结点的路径
	static StringBuilder stringBuilder = new StringBuilder();
	
	
	//为了调用方便，我们重载 getCodes
	private static Map<Byte, String> getCodes(Node root) {
		if(root == null) {
			return null;
		}
		//处理root的左子树
		getCodes(root.left, "0", stringBuilder);
		//处理root的右子树
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}
	
	/**
	 * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
	 * @param node  传入结点
	 * @param code  路径： 左子结点是 0, 右子结点 1
	 * @param stringBuilder 用于拼接路径
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		//将code 加入到 stringBuilder2
		stringBuilder2.append(code);
		if(node != null) { //如果node == null不处理
			//判断当前node 是叶子结点还是非叶子结点
			if(node.data == null) { //非叶子结点
				//递归处理
				//向左递归
				getCodes(node.left, "0", stringBuilder2);
				//向右递归
				getCodes(node.right, "1", stringBuilder2);
			} else { //说明是一个叶子结点
				//就表示找到某个叶子结点的最后
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}
	
	//前序遍历的方法
	private static void preOrder(Node root) {
		if(root != null) {
			root.preOrder();
		}else {
			System.out.println("赫夫曼树为空");
		}
	}
	
	/**
	 * 
	 * @param bytes 接收字节数组
	 * @return 返回的就是 List 形式   [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
	 */
	private static List<Node> getNodes(byte[] bytes) {
		
		//1创建一个ArrayList
		ArrayList<Node> nodes = new ArrayList<Node>();
		
		//遍历 bytes , 统计 每一个byte出现的次数->map[key,value]
		Map<Byte, Integer> counts = new HashMap<>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) { // Map还没有这个字符数据,第一次
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}
		
		//把每一个键值对转成一个Node 对象，并加入到nodes集合
		//遍历map
		for(Map.Entry<Byte, Integer> entry: counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
		
	}
	
	//可以通过List 创建对应的赫夫曼树
	private static Node createHuffmanTree(List<Node> nodes) {
		
		while(nodes.size() > 1) {
			//排序, 从小到大
			Collections.sort(nodes);
			//取出第一颗最小的二叉树
			Node leftNode = nodes.get(0);
			//取出第二颗最小的二叉树
			Node rightNode = nodes.get(1);
			//创建一颗新的二叉树,它的根节点 没有data, 只有权值
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			parent.left = leftNode;
			parent.right = rightNode;
			
			//将已经处理的两颗二叉树从nodes删除
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			//将新的二叉树，加入到nodes
			nodes.add(parent);
			
		}
		//nodes 最后的结点，就是赫夫曼树的根结点
		return nodes.get(0);
		
	}
	

}


//创建Node ,待数据和权值
class Node implements Comparable<Node>  {
	Byte data; // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
	int weight; //权值, 表示字符出现的次数
	Node left;//
	Node right;
	public Node(Byte data, int weight) {
		
		this.data = data;
		this.weight = weight;
	}
	@Override
	public int compareTo(Node o) {
		// 从小到大排序
		return this.weight - o.weight;
	}
	
	public String toString() {
		return "Node [data = " + data + " weight=" + weight + "]";
	}
	
	//前序遍历
	public void preOrder() {
		System.out.println(this);
		if(this.left != null) {
			this.left.preOrder();
		}
		if(this.right != null) {
			this.right.preOrder();
		}
	}
}
