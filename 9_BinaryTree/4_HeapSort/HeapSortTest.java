
import java.util.*;

/*
 * 堆排序
 *
 * 什么是堆？
 * 堆是一种完全二叉树(Compleate Binary Tree),完全二叉树包括满二叉树。具体见个人笔记。
 *
 * 什么是大顶堆，小顶堆?
 * 大顶堆(Max heap)：在完全二叉树中父节点的值 >= 子节点，注意不比较左右子节点的大小。
 * 小顶堆(Min heap)：父节点 <= 子节点小。也是不比较左右子节点大小
 *
 * 如果是正序排序，需要把二叉树先变成大顶堆，倒叙则是小顶堆。
 *
 * */
public class HeapSortTest{

	
	public static void main(String[] args){
		//注意：确保数组形成的二叉数是完全二叉树
		int[] arr = {12,6,5,27,9,8,3};
		transferToMaxHeap(arr,2,8);
		System.out.println("first==>" + Arrays.toString(arr));
		
		
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

		/*
		 * 递增条件 k = 2*k + 1表示调整是沿着数一直向左走的。
		 * */

		
		//保存父节点的值到临时变量
		int temp = arr[i];

		for(int k = 2*i + 1; k < arr.length ; k = 2*k + 1){
			
			//比较左右子节点，左<右则下标移动
			if(k + 1 < length && arr[k] < arr[k+1]){
				k++;
			}
			//跟父节点比较，比父节点大则交换
			if(arr[k] > temp){
				arr[i] = arr[k];
				i = k; //下标移动，继续调整。A
			}else{
				break;
			}
			
			//在A处交换成功后，把父节点的值移到最后
			arr[i] = temp;

		}
		
		
	}

}
