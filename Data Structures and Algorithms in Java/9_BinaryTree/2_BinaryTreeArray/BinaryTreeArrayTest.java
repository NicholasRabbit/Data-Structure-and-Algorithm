

/*
* 顺序存储二叉树范例：
*
* 1,把一个数组按二叉数的遍历形式遍历,可使用前序，中序，后序进行遍历;
* 2,用途：堆排序的时候会用到此思路。
*
* 思路：
* 首先找出父节点和左右子树下标之间的关系（下标为n，从0开始，对应数组的下标）：
* 1，父节点的下标为n，则其左节点的下标为：2*n + 1;
* 2，其右节点的下标为：2*n + 2;
* 3，下标为n的节点，其父节点为：(n - 1) / 2，结果取整；
* 4，顺序二叉树通常针对的是完全二叉树;

* What is full binary tree?
* A full Binary tree is a special type of binary tree in which every parent node/internal node has either two or no children.
*/
public class BinaryTreeArrayTest {

	public static void main(String[] args){
		
		int[] array = {1,2,3,4,5,6,7};
		
		BinaryTreeArray bta = new BinaryTreeArray(array);
		bta.preShow(0);
	
	}


	
}

class BinaryTreeArray {
	
	private int[] array;

	public BinaryTreeArray(){
	
	}
	public BinaryTreeArray(int[] array){
		this.array = array;
	}

	
	//按照二叉树前序遍历的方式遍历数组。
	public void preShow(int index){
		//判断数组是否为空
		if(array == null || array.length == 0 || index  < 0 || index >= array.length){
			System.out.println("数组为空,或下标越界！");
			return;
		}
		//前序遍历先打印当前的节点。
		System.out.println(array[index]);
		//向左递归，首先要判断下标是否越界
		if(2*index + 1 < array.length){
			preShow(2*index + 1);
		}
		//向右递归
		if(2*index + 2 < array.length){
			preShow(2*index + 2);
		}

	}

}
