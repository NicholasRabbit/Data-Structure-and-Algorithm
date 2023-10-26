
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
		adjust(nodes);
		showArray(nodes);
		//2.2 排序
		heapSort(nodes);
		System.out.println("排序后====================");
		showArray(nodes);

		//3,把排序好的数组组装成一个霍夫曼树
		buildHuffmanTree(nodes);




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


	/*
	 * 循环调整一个中间节点及其下面的所有子节点。
	 * 把二叉树调整成大顶堆
	 * i : 中间节点下标
	 * length : 数组长度
	 * */
	public static void transferToMaxHeap(int i, int length, TreeNode[] nodes){
		
		//保存父节点到临时变量
		TreeNode temp = nodes[i];

		for(int left = 2 * i + 1; left < length; left = 2 * left + 1){
			//1,先比较左右子节点
			//left + 1是右节点下标。
			if(left + 1 < length && nodes[left].weight < nodes[left + 1].weight){
				left ++;
			}
			//2,比较父节点和较大的子节点，子节点大则交换。
			if(nodes[left].weight > temp.weight){
				//交换
				nodes[i] = nodes[left];
				//下标移动到到较大的子节点，以其为父节点进行新一轮的比较。
				i = left;   
			}else{
				break;
			}

			//3,把原父节点移动到子节点
			nodes[i] = temp;

		}

	
	}


	//自动调整整个树为大顶堆。
	public static void adjust(TreeNode[] nodes){
		//i:最后一个中间节点的下标。
		for(int i = nodes.length / 2 - 1; i >= 0; i --){   //注意：这里是 i >= 0，切记不要忘了等号"="，因为根节点也要比较。
			transferToMaxHeap(i,nodes.length,nodes);
		}
	
	}

	/*
	 * 堆排序
	 * 步骤：
	 * 1，首先把最大的根节点发到树的最后，即数组最后一位。
	 * 2，最大的一个放到最后了，然后把剩下的再调整成大顶堆。
	 * 3，重复1，2步骤，最后即可得到一个正序的数组。
	 * */
	public static boolean heapSort(TreeNode[] nodes){
		//入参校验
		if(nodes == null || nodes.length == 0){
			return false;
		}

		for(int len = nodes.length - 1; len > 0; len--){
			TreeNode temp = nodes[0];
			nodes[0] = nodes[len];
			nodes[len] = temp;
			transferToMaxHeap(0,len,nodes);  //这里为什么传入0，而不是新数组的最后一个中间节点？详见堆排序HeapSortTest.java中的解释。
		}

		return true;
	
	}


	//根据正序数组，创建霍夫曼树
	public static TreeNode buildHuffmanTree(TreeNode[] nodes){

		

		//for(int i = 0; i < nodes.length; i++){
			//构建父节点
			TreeNode parentNode = new TreeNode((byte)1,nodes[0].weight + nodes[1].weight);
			//父节点设置左右子节点
			parentNode.left = nodes[0];
			parentNode.right = nodes[1];
			
			nodes[1] = parentNode;

			TreeNode[] copyNodes =  copyToNewArray(nodes,1);
			//
			System.out.println("新数组========");
			showArray(copyNodes);

		//}
		
		return null;
	
	}

	//复制原数组到新数组。此方法是因为本人不想把数组转链表排序，因此练习用的，后期复习霍夫曼编码时不需关心此步骤，专注霍夫曼编码的核心流程。
	public static TreeNode[] copyToNewArray(TreeNode[] srcNodes,int beginIndex){
		//根据推理可知，去除数组前两位元素后组成一个树，再把树的父节点放回去。那么新的数组只是把下标0的节点去掉接口，1的位置放父节点。
		//因此复制的新数组容量比原来减 1。
		TreeNode[] desNodes = new TreeNode[srcNodes.length - 1];
		for(int i = 0; beginIndex < srcNodes.length; beginIndex++){
			desNodes[i] = srcNodes[beginIndex];
			i ++;
		}

		return desNodes;
	
	}
	

	
}



