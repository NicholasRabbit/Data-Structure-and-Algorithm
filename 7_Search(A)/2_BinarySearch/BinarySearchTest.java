
import java.util.Arrays;

/*
* 二分查找算法，利用到了指数爆炸，每次除以二
* 使用二分查找算法要求数组排好序，正序或倒序都可以，然后写查找的算法时具体调整。
* 本例使用正序。
* 本例问题，有多个相同的值无法都找出来，BinarySearchTest02中进行完善。
*/
public class BinarySearchTest {

	public static void main(String[] args){
		
		int[] array = new int[]{9,5,68,3,62,1,0};
		//先排好序
		insertSort(array);
		System.out.println("排好序后：" + Arrays.toString(array));
		//再进行查找。{0, 1, 3, 5, 9, 62, 68}
		int value = 333;
		//1,手动分步查找
		int index = binarySearchByStep(array,value);
		System.out.println("value: " + value + ",index: " + index);
		//2,循环自动查找
		index = binarySearch(array,value);
		System.out.println("value: " + value + ",index: " + index);
		//3,使用递归查找
		index = binarySearchRecursion(array,value,0,array.length - 1);
		System.out.println("recursion==>value: " + value + ",index: " + index);
	
	}

	//二分查找法
	//一，先手动分步，理解思路
	public static int binarySearchByStep(int[] array,int value){
	
		//1,首先确定好初始的左右下标，即原数组的首尾下标
		int begin = 0;
		int end = array.length - 1;
		//2,求出中间下标
		int mid = (begin + end) / 2;
		
		//第一次二等分
		if(array[mid] > value){
			//如果中间下标的元素值大于要找的，则把中间下标设为end，后面小于时同理
			//注意这里中间下标不符合要求就前移一位，否则到最后就一直等于某个下标，如本例最后一直是 mid = (5+5)/2（用递归的时候）
			end = mid - 1;  
			mid = (begin + end) / 2;
		}else if(array[mid] < value){
			begin = mid + 1;  
			mid = (begin + end) / 2;
		//第3种情况：正好中间下标的值和要找的相等
		}else{
			return mid;
		}

		//第二次二等分
		if(array[mid] > value){
			end = mid - 1;
			mid = (begin + end) / 2;
		}else if(array[mid] < value){
			begin = mid + 1;
			mid = (begin + end) / 2;
		}else{
			return mid;
		}

		//第三次，四次……同理。

		//没找到则返回-1。
		return -1;
	}

	//二，使用循环的方式自动完成查找
	public static int binarySearch(int[] array,int value){
		
		//先初始化左中右三个下标
		int begin = 0;
		int end = array.length - 1;
		int mid = (begin + end) / 2;

		while(begin < end){
			if(array[mid] > value){
				//注意这里中间下标不符合要求就前移一位，否则到最后就一直等于某个下标（用递归的时候）
				end = mid - 1;  
				mid = (begin + end) / 2;
			}else if(array[mid] < value){
				begin = mid + 1;
				mid = (begin + end) / 2;
			//第3种情况：正好中间下标的值和要找的相等
			}else{
				return mid;
			}
		}

		return -1;

	}

	//三，使用递归的方式二分法查找
	public static int binarySearchRecursion(int[] array,int value,int begin,int end){
		
		if(begin <= end){
			int mid = (begin + end) / 2;
			if(array[mid] > value){
				//注意这里中间下标不符合要求就前移一位，否则到最后就一直等于某个下标（用递归的时候）
				end = mid - 1;  
				//注意使用递归时，return语句要写这里，因为递归完栈最底部的方法(递归链的头一个)要返回结果，否则递归无穷尽
				// binarySearchRecursion(array,value,begin,end);  //错误写法
				return binarySearchRecursion(array,value,begin,end);
			}else if(array[mid] < value){
				begin = mid + 1;
				return binarySearchRecursion(array,value,begin,end);
			}else{
				return mid;
			}
		}
		return -1;
	
	}


	//插入排序，使用for循环自动完成排序
	public static void insertSort(int[] array){
		/*
		* 下标从1开始，不是0，因为把0下标的元素当作模拟的第一个数组，后面从1开始是第二个数组
		*/
		for(int i = 1; i < array.length; i++){
			//用临时变量存好当前循环到的元素值
			int insertValue = array[i];
			int insertIndex = i - 1;
			/*
			* 1,insertIndex >= 0防止数组越界，后面要用短路与"&&",
			* 否则最后一次判断下标变成了-1还会执行 “&”后面的语句，会报错。 
			* 2,insertValue < array[insertIndex]表示后面元素小，则进入循环交换元素值
			*/
			while(insertIndex >= 0 && insertValue < array[insertIndex]){
				//把数值大的后移一位
				array[insertIndex + 1] = array[insertIndex];
				//指针前移，继续让insertValue和前面的元素比较
				insertIndex --;
			}
			
			//当while循环之后，还没有把insertValue给移动到前面，由于while内执行了insertIndex --
			//所以这里要后移一位
			array[insertIndex + 1] = insertValue;

		}

	}

}