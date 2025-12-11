
//堆排序工具
public class HeapSortTool {

	//堆排序集成方法
	public static boolean heapSort(TreeNode[] nodes){
		//第一步，先把普通二叉树调整成大顶堆
		adjust(nodes);
		//第二步，排序
		moveMaxValueToEnd(nodes);

		return true;
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
	public static boolean moveMaxValueToEnd(TreeNode[] nodes){
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


}
