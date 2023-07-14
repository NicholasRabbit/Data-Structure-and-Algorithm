
//复习，调整大顶堆，堆排序工具类
public class HeapSortUtil {

	public static void autoAdjust(int[] arr){
		//中间节点个数
		int k = arr.length / 2 - 1;
		for(int i = k; i >= 0; i--){
			adjustToMaxHeap(arr,i,arr.length);
		}
		
	}

	
	//调整成大顶堆
	public static void adjustToMaxHeap(int[] arr,int i,int length){
		//入参校验
		if(arr == null || arr.length == 0 || i < 0 || length < 0){
			return;
		}
		
		int temp = arr[i];

		for(int k = 2 * i + 1; k < length; k = 2 * k + 1){
			
			//比较左右子节点
			if(k + 1 < length && arr[k] < arr[k+1]){
				//如果 左 < 右，则下标后移一位指向右子节点
				k ++;
			}
			//跟父节点比较
			if(arr[k] > temp){
				arr[i] = arr[k];
				//把下标移到左子节点，准备以左子节点为父节点进行下一轮的调整
				i = k;
			}else{
				break;
			}
			//把小值放到左子节点。
			arr[i] = temp;
		
		}

	}

	//堆排序，大顶堆排序后得到的是正序数组
	public static void heatSort(int[] arr){
		
		for(int k = arr.length - 1; k > 0; k--){
			//根节点和最后一个节点交换位置
			int temp = arr[0];
			arr[0] = arr[k];
			arr[k] = temp;
			adjustToMaxHeap(arr,0,k);

		}
	
	}


}