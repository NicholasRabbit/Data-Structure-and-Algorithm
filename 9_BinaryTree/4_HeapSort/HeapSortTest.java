
import java.util.Arrays;

/*
 * 堆排序
 * 它的最好，最坏，平均时间复杂度时O(nlog(n))，是不稳定的排序方法？？
 * 
 * 为什么时间复杂度是O(nlog(n))?
 * 按最坏的时间来算的话，堆排序首先得调整成大顶堆，然后把最大的根节点放到最后，最后一个节点A放到根节点，一共调整n次，所以时间复杂度是O(n)
 * 而调整完之后，根节点A不是最大的了，需要重新调整，最坏情况是放到最底层，耗费时间log(n)，也是树的高度。
 * 所以时间复杂度是O(nlog(n))
 *
 * 什么是堆？
 * 堆是一种完全二叉树(Compleate Binary Tree),完全二叉树包括满二叉树。具体见个人笔记。
 *
 * 什么是大顶堆，小顶堆?
 * 大顶堆(Max heap)：在完全二叉树中父节点的值 >= 子节点，注意不比较左右子节点的大小。
 * 小顶堆(Min heap)：父节点 <= 子节点小。也是不比较左右子节点大小
 *
 * 如果是正序排序，需要把二叉树先变成大顶堆，倒序则是小顶堆。
 *
 * */
public class HeapSortTest{

	
	public static void main(String[] args){
		//注意：确保数组形成的二叉数是完全二叉树
		int[] arr = {12,6,5,27,9,8,3,65};
		//一，先前序遍历数组二叉树，跟后面的做对比
		preList(0,arr);
		
		System.out.println();

		//二，手动分步调整
		//第一次：“3”是最后一个非叶子节点的下标
		transferToMaxHeap(arr,3,8);
		preList(0,arr);
		
		System.out.println();
		//第二次：
		transferToMaxHeap(arr,2,8);
		preList(0,arr);
		
		System.out.println();
	
		//第三次：
		transferToMaxHeap(arr,1,8);
		preList(0,arr);

		System.out.println();
		//第四次：这次调整最后一个根节点
		transferToMaxHeap(arr,0,8);
		preList(0,arr);
		
		System.out.println();
			
		//三，自动调整大顶堆，并排序
		int[] array = {12,6,5,27,9,8,3,65};  //防止之前结果干扰，新建一个一样的数组。
		System.out.println("====自动调整====");
		preList(0,array);
		autoAdjust(array);
		System.out.println("打印数组==>" + Arrays.toString(array));
	}

	
	public static void autoAdjust(int[] array){
		//三，自动调整
		//中间节点个数，同时也是最后一个中间节点的下标
		
		int c = array.length / 2 - 1;
		for(int i = c; i >= 0; i--){
			transferToMaxHeap(array,i,array.length);  //B: O(n)
		}
	
		

		System.out.println();

		/*
		* 四，最后进行从小到达排序
		* 思路：
		* 1,因为之前已调整为大顶堆，最大的树在顶部，只需把最大的数和最后一个数交换即可;
		* 2,交换完成后，再把除最后一个节点外的树调整为大顶堆，再按步骤1进行调整，注意，这里的length要递减1，以排除最后一个节点。
		*/
		for(int k = array.length - 1; k > 0; k--){  //k指向最后一个节点
			//I : 根节点和最后一个节点交换。
			int temp = array[0];
			array[0] = array[k];
			array[k] = temp;
			//II : 交换完成后，排除最后一个节点，再调整为大顶堆，再循环到I进行交换
			transferToMaxHeap(array,0,k);  //这里对数组进行排序时，从下标0开始。
		}
		
	
	}
	
	//前序遍历二叉树数组
	public static void preList(int index,int[] arr){
		//入参校验
		if(index < 0 || index >= arr.length || arr == null || arr.length == 0)
			return;
		//前序遍历：根左右，即先打印根节点
		System.out.print(arr[index] + "\t");
		//打印左节点
		if(2*index + 1 < arr.length){
			preList(2*index + 1,arr);
		}
		//打印右节点
		if(2*index + 2 < arr.length){
			preList(2*index + 2,arr);
		}

	
	}

	/*
	 * 把完全二叉树调整为大顶堆
	 * 先分步调整，从根节点开始判断。
	 * 1,首先从哪个节点开始调整？
	 * 从最后一个中间节点开始，这个节点和左右节子点比较，如果子节点大则换位置。
	 * 通过二叉树中间节点的公式:(n-1)/2，可算出中间节点个数，同时这个公式的结果也是最后一个中间节点的下标。
	 * 第一步先调整这个节点
	 * i : 中间节点的下标，length : 数组长度,在不断的调整中长度是减少的。
	 * */
	public static void transferToMaxHeap(int[] arr,int i,int length){

		//递增条件 k = 2*k + 1表示调整是沿着树一直向左走的。
		
		//保存父节点的值到临时变量
		int temp = arr[i];

		/*
		* 注意：这个for循环实际是一直沿着树向左下找进行比较的。
		* 结合个人画图重点理解第三次中的两次交换。
		*/
		for(int k = 2*i + 1; k < length ; k = 2*k + 1){
		
			//先比较左右子节点，左 < 右则下标移动
			if(k + 1 < length && arr[k] < arr[k+1]){
				k++;  //k指向右子节点，供下面和父节点比较，左右节点并不交换值，只要保证父节点是这三个节点最大值即可。
			}
			
			//上步完成后，k指向的节点再跟父节点比较，比父节点大则交换。
			if(arr[k] > temp){
				//把较大的左子节点值赋予父节点。
				arr[i] = arr[k];
				/*
				* A:下标移动，向左子节点，继续循环调整。结合个人本节画的图理解。
				*/
				i = k; 
			}else{
				//一直循环，直到所有父节点都比子节点大时才会执行此语句，并中断。
				break;
			}
			
			//在A处交换成功后，把父节点的值移到最后
			arr[i] = temp;

		}
		
		
	}

}
