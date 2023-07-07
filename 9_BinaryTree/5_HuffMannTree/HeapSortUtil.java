
//调整大顶堆，堆排序工具类
public class HeapSortUtil {


	//调整成大顶堆
	public static void adjustToMapHeap(int[] arr,int index,int length){
		//入参校验
		if(arr == null || arr.length == 0 || index < 0 || length < 0){
			return;
		}
		
		for(int k = 2 * index + 1; k < length; index = 2 * index + 1){
			int temp = arr[index];
			//比较左右子节点
			if(k + 1 < length && arr[k] < arr[k+1]){
				//如果 左 < 右，则下标后移一位指向右子节点
				k ++;
			}
			//跟父节点比较
			if(arr[k] > arr[index]){
				arr[index] = arr[k];
				//把下标移到左子节点，准备以左子节点为父节点进行下一轮的调整
				index = k;
			}else{
				break;
			}
			//把小值放到左子节点。
			arr[index] = temp;
		
		}

	
	}


}