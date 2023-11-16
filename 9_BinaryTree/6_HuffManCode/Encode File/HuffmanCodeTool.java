
import java.util.*;

/*
 * 此处把第一个Encode ASCII中的加密方法整合到一起，去除原先不必要的检查打印方法
 * */
public class HuffmanCodeTool{

	//声明静态变量Map对象，存储字符及其对应的霍夫曼编码。
	private static Map<Byte,String> huffManCodeMap = new HashMap<>();

	//获取加密后的byte[]
	public static byte[] getEncodeBytes(byte[] contentBytes){
		/*
		 * 一，统计句子中每个字符的个数，并放到 Map中。
		 * 1,首先把每个字符转换成byte，实际是转换成了字符对应的ASCII码。
		 * */
		// 2,以byte值为key，出现次数为value放到Map中。
		Map<Byte,Integer> weightMap = getWeightMap(contentBytes);
		// 3,生成含有权值的节点数组
		List<TreeNode> nodeList = getNodeList(weightMap);

		/*
		 * 二，创建霍夫曼树。
		 * */
		TreeNode root = getHuffmanTree(nodeList);

		/*
		 * 三，生成每个字符及其对应的霍夫曼编码，放到Map中。
		 * 向左一步记作“0”，向右一步记作“1”
		 * */
		StringBuilder builder = new StringBuilder();
		Map<Byte,String> encodeMap = appendCode(root,"",builder);

		/*
		 * 四，把原文加密成霍夫曼编码的字符串。
		 * 生成的霍夫曼编码字符串，实际长度可能比原来的句子还长，
		 * 因此还需要处理，把“0101...”这样的字符串按照byte类型的8位截开，转为byte类型放到数组。
		 * */
		String encodeString = createStringCode(contentBytes,encodeMap);

		byte[] encodeBytes = stringToByteArray(encodeString);

		return encodeBytes;

	
	}



	//根据霍夫曼树创建霍夫曼编码表，重点理解递归的方式拼接编码的思路。
	public static Map<Byte,String> appendCode(TreeNode node,String code,StringBuilder codeBuilder){
		/*
		 * 1,这里一进入方法就先拼接原来的编码，如果是叶子节点的话就在下方"ADD"处加入map，否则即使拼接了也不加入。
		 * 2,必须new一个StringBuilder对象，因为下方递归调用时，每个节点向左走是一个新的StringBuilder，向右又是一个新的。
		 * */
		StringBuilder builderNew = new StringBuilder(codeBuilder);
		builderNew.append(code);  //拼接每个路径必须写在这里，不能写在下方的"WRONG"处，因为中间节点的的路径也要拼

		//节点不为空才可往下走。
		if(node != null){
			//如果结点中的byte ascCode = -1,说明是非叶子节点
			if(node.ascCode == -1){
				//下面不用判断左右节点是否为null，因为递归方法的第一行有“入参校验”，入参是null的话自然就终止递归了。
				//向左递归遍历拼接，因为那条代表0的“树枝”在左节点上面，所以要提前传入参数“0”。
				appendCode(node.left,"0",builderNew);
				//向右递归同理
				appendCode(node.right,"1",builderNew);
			}else{

				//builderNew.append(code);	//WRONG:

				//ADD:
				//走到这里说明是叶子节点，直接把叶子节点的ACSII编码和对应的Huffman code放入map中。
				huffManCodeMap.put(node.ascCode,builderNew.toString());

			}


		}

		return huffManCodeMap;

	}

	


	//获取霍夫曼树
	public static TreeNode getHuffmanTree(List<TreeNode> nodeList){

		//1,转换成数组，方便操作。
		TreeNode[] nodes = nodeList.toArray(new TreeNode[]{});  

		//2,排序，使用堆排序的方法，为了复习。不使用老师所用的Collection.sort(..)
		//2.1 首先需把树调整成大顶堆。
		HeapSortTool.adjust(nodes);
		//2.2 排序
		HeapSortTool.heapSort(nodes);

		//3,把排序好的数组组装成一个霍夫曼编码树
		TreeNode root = buildHuffmanTree(nodes);

		return root;
		
	}


	//计算字符在句子中出现的次数，放到Map中
	public static Map<Byte,Integer> getWeightMap(byte[] byteArray){
		Map<Byte,Integer> weightMap = new HashMap<>();
		int count = 0;
		for(int i = 0; i < byteArray.length; i++){
			//有的话，次数加1
			if(weightMap.containsKey(byteArray[i])){
				count = weightMap.get(byteArray[i]);
				count ++;
				weightMap.put(byteArray[i],count);
			}else{
				//第一次放入Map
				count = 1;
				weightMap.put(byteArray[i],count);
			}

		}
		return weightMap;
	}


	//把字符和次数map包装成树的节点，放到List集合中
	public static List<TreeNode> getNodeList(Map<Byte,Integer> countMap){
		List<TreeNode> list = new ArrayList<>();
		for(Map.Entry<Byte,Integer> entry : countMap.entrySet()){
			list.add(new TreeNode(entry.getKey(),entry.getValue()));	
		}

		return list;
	
	}

	//前序遍历线索化后的二叉树，即以遍历二叉树的形式遍历数组。
	//下标i的父节点的左子节点下标是2*i + 1,右子节点 2*i + 2
	public static void preList(int i,TreeNode[] nodes){
		//入参校验
		if(i < 0 || i >= nodes.length || nodes.length == 0 || nodes == null )	
			return ;
		//打印当前节点
		System.out.print(nodes[i].weight + "\t");

		//打印左子节点
		int left = 2 * i + 1;
		if(left < nodes.length){
			preList(left,nodes);
		}

		//打印右子节点
		int right = 2 * i + 2;
		if(right < nodes.length){
			preList(right,nodes);
		}

	}



	//根据正序数组，创建霍夫曼树
	public static TreeNode buildHuffmanTree(TreeNode[] nodes){

		//中断循环条件length - 1,即最后剩余两个元素，比较一次后，把父节点放回数组，就终止。
		int length = nodes.length - 1;  //注意：不要把 i < nodes.length - 1写到for的判断式中，因为nodes一直在变化，导致不能比较完。
		for(int i = 0; i < length; i++){
			//(1)构建父节点，父节点设置无效ASCII码为-1
			TreeNode parentNode = new TreeNode((byte)-1,nodes[0].weight + nodes[1].weight);
			//父节点设置左右子节点
			parentNode.left = nodes[0];
			parentNode.right = nodes[1];
			//(2)把父节点放回原数组，替代第二个元素，下面删除第一个元素后，复制到新数组后，就相当于移除了下标0和1上的元素。
			nodes[1] = parentNode;
			//去除原数组下标0的元素
			nodes = copyToNewArray(nodes,1);
			//(3)新数组排序
			HeapSortTool.heapSort(nodes);

		}
		
		return nodes[0];
	
	}

	
	//使用霍夫曼编码加密原句子。生成字符串
	public static String createStringCode(byte[] contentBytes,Map<Byte,String> encodeMap){
		StringBuilder encodeBuilder = new StringBuilder();
		for(int i = 0; i < contentBytes.length; i++){
			//获取各个字符对应的霍夫曼编码，进行拼接
			String huffmamCode = encodeMap.get(contentBytes[i]);
			encodeBuilder.append(huffmamCode);
		}
		return encodeBuilder.toString();
	}

	public static byte[] stringToByteArray(String codeString){
		//入参校验
		if(codeString == null || "".equals(codeString))
			return null;

		//1，计算字符串总共有几个byte长度
		//下面得到结果是上取整，如果除不尽还要加1才是正确的长度。
		int len = codeString.length();
		int size = 0;
		if(len % 8 == 0){
			size = len / 8;
		}else{
			size = len / 8 + 1;
		}
		//还有一种直接的算法
		//int size = (countChars.length + 7) / 8;

		//2，由上步得得byte数组的长度
		byte[] arr = new byte[size]; 
		//byte[] arr = new byte[size + 1];  //这里不写size而写size + 1，因为数组最后一个要存标记，来表示最后一节编码是否0开头。

		//3，按照每8位一节，截断原字符串
		//下面对arr数组循环添加，实际循环字符串也可以。
		int index = 0;  //字符串的初始下标。
		for(int i = 0; i < arr.length; i++){
			String str = "";
			//注意这里是 index + 8, 如果index + 8超过其总长，说明最后剩的不够8位，则有多少就截断多少。
			if(index + 8 > codeString.length()){
				str = codeString.substring(index);
			}else{
				//未超出长度的情况,或者最后刚好剩下8位。例{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15} 0+8=8,8+8=16(=length())
				str = codeString.substring(index,index + 8);  //包前不包后
			}

			//把String转化为byte类型的二进制,放到数组中。
			byte b = (byte)Integer.parseInt(str,2);
			arr[i] = b;

			//截断之后，下标后移8位。
			index += 8;

		}

		return arr;
	}


	//复制原数组到新数组。此方法是因为本人不想把数组转链表排序，因此使用手动复制创建新数组的方式，
	//后期复习霍夫曼编码时不需关心此步骤，专注霍夫曼编码的核心流程。
	public static TreeNode[] copyToNewArray(TreeNode[] srcNodes,int beginIndex){
		//根据推理可知，去除数组前两位元素后组成一个树，再把树的父节点放回去。那么新的数组只是把下标0的节点去掉即可，1的位置放父节点。
		//因此复制的新数组容量比原来减 1。
		TreeNode[] desNodes = new TreeNode[srcNodes.length - 1];
		for(int i = 0; beginIndex < srcNodes.length; beginIndex++){
			desNodes[i] = srcNodes[beginIndex];
			i ++;
		}

		return desNodes;
	
	}

	//getter
	public static Map<Byte,String> getHuffmanCodeMap(){
		return huffManCodeMap;
	}

}
