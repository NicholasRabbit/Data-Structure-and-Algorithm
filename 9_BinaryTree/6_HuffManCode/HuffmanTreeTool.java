
import java.util.*;

public class HuffmanTreeTool{

	//获取霍夫曼树
	public static TreeNode getTree(Map<Byte,Integer> countMap){

		//1,先获取节点集合
		List<TreeNode> list = getList(countMap);
		System.out.println("node list==>" + list);
		System.out.println("====================");
		
		//转换成数组，方便操作。
		TreeNode[] nodes = list.toArray(new TreeNode[]{});  

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
		TreeNode root = buildHuffmanTree(nodes);
		//前序遍历，查看结果
		root.preList();




		return null;
		
	}

	//把字符和次数map包装成树的节点，放到List集合中
	public static List<TreeNode> getList(Map<Byte,Integer> countMap){
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
			//(2)把父节点放回原数组，替代第二个元素，下面删除第一个元素后，就相当于移除了下标0和1上的元素。
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



