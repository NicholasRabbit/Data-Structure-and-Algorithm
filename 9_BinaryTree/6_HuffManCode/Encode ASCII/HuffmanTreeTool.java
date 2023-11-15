
import java.util.*;

public class HuffmanTreeTool{
	
	//声明静态变量Map对象，存储字符及其对应的霍夫曼编码。
	private static Map<Byte,String> huffManCodeMap = new HashMap<>();

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
		System.out.println("node list==>" + nodeList);
		System.out.println("====================");
		TreeNode[] nodes = nodeList.toArray(new TreeNode[]{});  

		//2,排序，使用堆排序的方法，为了复习。不使用老师所用的Collection.sort(..)
		preList(0,nodes);  //先前序遍历打印，方便和排序后的对比
		System.out.println();
		//2.1 首先需把树调整成大顶堆。
		System.out.println("调整后====================");
		HeapSortTool.adjust(nodes);
		showArray(nodes);
		//2.2 排序
		HeapSortTool.heapSort(nodes);
		System.out.println("排序后====================");
		showArray(nodes);

		//3,把排序好的数组组装成一个霍夫曼编码树
		System.out.println("霍夫曼树前序遍历====================");
		TreeNode root = buildHuffmanTree(nodes);
		//前序遍历，查看结果
		root.preList();

		return root;
		
	}


	//把字符和次数map包装成树的节点，放到List集合中
	public static List<TreeNode> getNodeList(Map<Byte,Integer> countMap){
		List<TreeNode> list = new ArrayList<>();
		for(Map.Entry<Byte,Integer> entry : countMap.entrySet()){
			list.add(new TreeNode(entry.getKey(),entry.getValue()));	
		}

		return list;
	
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

	//打印数组
	public static void showArray(TreeNode[] nodes){
		for(int i = 0; i < nodes.length; i++){
			System.out.println("nodes[" + i + "]=" + nodes[i]);
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
			//查看结果
			//System.out.println("新数组========");
			//showArray(nodes);
			
			//(3)新数组排序
			HeapSortTool.heapSort(nodes);

		}
		
		return nodes[0];
	
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
	

	
}



