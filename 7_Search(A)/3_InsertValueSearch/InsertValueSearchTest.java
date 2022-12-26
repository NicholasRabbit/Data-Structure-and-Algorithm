
import java.util.Arrays;

/*
* 插值查找算法：
* 这个算法只适用于排好序的数组，是在二分查找算法的基础上进行了改进，
* 当数组内的元素的递增值比较均匀时，这个算法比二分查找快，当分步不均匀时不一定快。
* 
* 思路：
* 利用有序数组的特性，算出查找值和最小值的差，用这个差在和数组内最大值最小值的差求一个比例，
* 这个比例值较精确的确定了要查找值的下标位置。
* 关键公式转化思路：
* int mid = (begin + end) / 2
*		  = begin + (end - begin) / 2
* ==> mid = begin + (end - begin) * (value - array[begin]) / (array[end] - array[begin]);
*     注：这个把1/2这个大范围的比例进一步缩小了，定位更准确了。
*/
public class InsertValueSearchTest {

	public static void main(String[] args){
	
		//创建一个有序数组，这个数组是个最理想的情况，递增均匀，
		int[] array = new int[100];
		for(int i = 0; i < array.length; i++){
			array[i] = i + 1;
		}
		System.out.println("array==>" + arrayToString(array));
		
		int index = insertValueSearch(array,0,array.length - 1,12);
		System.out.println("index==>" + index);

		//使用递归
		System.out.println("=====recursion======");
		index = insertRecursion(array,0,array.length - 1,16);
		System.out.println("index==>" + index);
	
	}

	//1，插值查找算法。使用while循环的方式。
	public static int insertValueSearch(int[] array,int begin,int end,int value){
		
		//还是按照二分查找法的主要思路，只是mid的计算方法优化了。
		int mid = begin + (end - begin) * (value - array[begin]) / (array[end] - array[begin]);
		int count = 0;
		int index = -1;
		while(begin < end){
			//数据判断，防止查找一个value值过大，数组中没有的话造成求出的mid值下标越界。
			if(value < array[begin] || value > array[end] 
				|| begin < 0 || end > array.length){
				break;
			}
			count ++;
			if(array[mid] < value){
				end = mid - 1;
				mid = begin + (end - begin) * (value - array[begin]) / (array[end] - array[begin]);
			}else if(array[mid] > value){
				begin = mid + 1;
				mid = begin + (end - begin) * (value - array[begin]) / (array[end] - array[begin]);
			}else{
				index = mid;
				break;
			}
		
		}
		System.out.println("查找次数==>" + count);
		return index;
	}

	
	//2,使用递归的方式
	public static int insertRecursion(int[] array,int begin,int end,int value){
		//数据判断，防止查找一个value值过大，数组中没有的话造成求出的mid值下标越界。
		if(begin > end || value < array[0] || value > array[array.length - 1]){
			return -1;
		}
		//按比例求出下标
		int mid = begin + (end - begin) * (value - array[begin]) / (array[end] - array[begin]);
		if(array[mid] > value){
			end = mid - 1;
			return insertRecursion(array,begin,end,value);
		}else if(array[mid] < value){
			begin = mid + 1;
			return insertRecursion(array,begin,end,value);
		}else{
			return mid;
		}
		
		
	}
	
	
	
	//仿写Arrays.toString(..)
	private static String arrayToString(int[] array){
		
		if(array == null){
			return "null";
		}

		int maxIndex = array.length - 1;
		if(maxIndex == -1){
			return "{}";
		}
		
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		for(int i = 0; ; i++){  //这里for中间没有条件，因此无论如何回进入下面的if语句，所以最后不写return也不报错。
			builder.append(array[i]);
			if(i == maxIndex)
				return builder.append("}").toString();
			builder.append(",");
		}

	}
}