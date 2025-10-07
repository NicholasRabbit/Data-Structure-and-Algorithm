
import java.util.Date;
import java.text.SimpleDateFormat;


/*
* 基数排序 
* 1，本例只实现了正整数的排序，有负数的排序需要调整代码；
* 2，基数排序中用二维数组模拟”桶“，具体思路参照个人笔记；
* 3，基数排序属于用空间复杂度来减少时间复杂度的排序方法。
*/
public class RadixSortTest {
	
	private static int count;

	public static void main(String[] args){
		
		//手动分步
		int[] array = {736, 234, 12, 352, 29, 20};
		radixSortByStep(array);

		//自动
		int[] array3 = {7360, 234, 12, 352, 29, 20};
		radixSort(array3);
		list(array3);

		//测试速度
		//基数排序所占内存 用了11个int数组，每个int占4 byte：10000000 * 11 * 4 /1024/1024 = 419.6 Mb
		//如果写上亿条数组，则会报错 out of memory error,heap 堆内存溢出。
		int[] array2 = new int[10000000];  
		for(int i = 0; i < array2.length; i++){
			array2[i] = (int)(Math.random() * 10000000);
		}
		int[] temp2 = new int[array2.length];
		System.out.println("length==>" + array2.length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date start = new Date();
		System.out.println("开始时间==>" + sdf.format(start));
		radixSort(array2);
		Date end = new Date();
		System.out.println("结束时间==>" + sdf.format(end));
		


		
	}

	//一，首先，手动分步实现，加深理解思路。后面使用for循环实现。
	public static void radixSortByStep(int[] array){
	
		//(1)用二维数组新建10个桶，下标表示对应的数，内层的一维数组用来放具体的数
		//外层数组的长度是10(0~9),内层数组的长度是要排序数组的长度，因为有可能要排序的数组内所有个位数都相同。
		int[][] bucket = new int[10][array.length];
		/*
		* (2)还要创建个一维数组来表示桶内元素的数量，防止把桶内数据放回原数组时，把空数据也放回去
		* 这个数组下标对应桶二维数组的下标，下标对应的值就是桶内元素的数量
		* 例：bucketElementCount[0]=2，表示0号桶内放了两个数。
		*/
		int[] bucketElementCount = new int[10];


		/*
		* 第一轮排序:
		* (1)按照个位数放到对应下标的桶里，然后放回原数组。
		*/
		for(int i = 0; i < array.length; i++){
			
			//取余数，即取出个位数
			int digit = array[i] / 1 % 10;  
			
			/*
			* 取出对应下标桶内的元素数量，即bucketElementCount数组该下标内的值。
			* 因为如果桶内有元素，要把新的数放到后一个位置，不能覆盖之前的。
			*/
			int bucketCount = bucketElementCount[digit];
			bucket[digit][bucketCount] = array[i];
			
			//添加完之后要更新桶内元素数量
			bucketElementCount[digit] = bucketCount + 1;
		
		}
		/*
		* (2)按照这个桶的顺寻，依次取出元素放回原数组。
		*/
		//首先遍历二维数组外层。
		int index = 0;  //原数组起始下标
		for(int j = 0;j < bucket.length; j++){
			//如果桶内有数的话就取出
			if(bucketElementCount[j] > 0){
				for(int k = 0; k < bucketElementCount[j]; k++){
					array[index] = bucket[j][k];
					index++; //原数组添加完后后移。
				}
			}
			//重点注意：每轮取出数放回原数组后，要把bucketElementCount中的指针归零，即对应元素值设置为0，否则影响后续的结果。
			bucketElementCount[j] = 0;

		}
		System.out.print("第一轮排序后==>");
		list(array);


		
		/*
		* 第二轮排序:
		*/
		for(int i = 0; i < array.length; i++){
			
			//取余数，即取出个位数，第二轮除以10
			int digit = array[i] / 10 % 10;  
			int bucketCount = bucketElementCount[digit];
			bucket[digit][bucketCount] = array[i];
			bucketElementCount[digit] = bucketCount + 1;
		
		}
		/*
		* 按照这个桶的顺寻，依次取出元素放回原数组。
		*/
		index = 0;  
		for(int j = 0;j < bucket.length; j++){
			if(bucketElementCount[j] > 0){
				for(int k = 0; k < bucketElementCount[j]; k++){
					array[index] = bucket[j][k];
					index++; //原数组添加完后后移。
				}
			}
			//重点注意：每轮取出数放回原数组后，要把bucketElementCount中的指针归零，即对应元素值设置为0，否则影响后续的结果。
			bucketElementCount[j] = 0;

		}
		System.out.print("第二轮排序后==>");
		list(array);


		/*
		* 第三轮排序: 因为数组内最大的数是三位数，所有三轮排序后即可把数组排序好，结束。
		*/
		for(int i = 0; i < array.length; i++){
			//取余数，即取出个位数，第三轮除以100
			int digit = array[i] / 100 % 10;  
			int bucketCount = bucketElementCount[digit];
			bucket[digit][bucketCount] = array[i];
			bucketElementCount[digit] = bucketCount + 1;
		
		}
		/*
		* 按照这个桶的顺寻，依次取出元素放回原数组。
		*/
		index = 0;  
		for(int j = 0;j < bucket.length; j++){
			
			if(bucketElementCount[j] > 0){
				for(int k = 0; k < bucketElementCount[j]; k++){
					array[index] = bucket[j][k];
					index++; //原数组添加完后后移。
				}
			}
			//重点注意：每轮取出数放回原数组后，要把bucketElementCount中的指针归零，即对应元素值设置为0，否则影响后续的结果。
			bucketElementCount[j] = 0;

		}
		System.out.print("第二轮排序后==>");
		list(array);

	}

	
	//二，使用for循环自动排好序
	public static void radixSort(int[] array){
		
		//(1)用二维数组新建10个桶，下标表示对应的数，内层的一维数组用来放具体的数
		//外层数组的长度是10(0~9),内层数组的长度是要排序数组的长度，因为有可能要排序的数组内所有个位数都相同。
		int[][] bucket = new int[10][array.length];
		/*
		* (2)还要创建个一维数组来表示桶内元素的数量，防止把桶内数据放回原数组时，把空数据也放回去
		* 这个数组下标对应桶二维数组的下标，下标对应的值就是桶内元素的数量
		* 例：bucketElementCount[0]=2，表示0号桶内放了两个数。
		*/
		int[] bucketElementCount = new int[10];
		
		//1,首先找到数组中位数最多的数，也是最大的数
		//  先假设第一个数最大，然后和其余的比较，遇到更大的就替换
		int maxNumber = array[0];  
		for(int m = 0; m < array.length; m++){
			if(array[m] > maxNumber){
				maxNumber = array[m];
			}
		}

		//2,求出最大数的位数，可使用 (digitNo + "").length的简单方式，这里暂且不用。
		//第一种求位数方法
		int maxLength = 0;
		//因为有可能是0这个一位数的情况，所以用do..while,先计一次数
		do{
			maxLength++;
			maxNumber = maxNumber / 10;
		}
		while(maxNumber > 0);
		
		//System.out.println("maxLength==>" + maxLength);
		
		////第二种求位数方法，用递归的方法计算整数的位数。
		//recursionCount(max);
		//System.out.println("count==>" + count);
		
		/*
		* 3,下面进行比较，跟上述的手动分步方式一样，只是外层套个for，加个变量m  
		*/
		for(int i = 0,m = 1; i < maxLength; i++,m *= 10){
			//3.1首先把数取出来按照顺序放到桶里。
			for(int j = 0; j < array.length; j++){
				int digit = array[j] / m % 10;  // m是取余数时用，每次增10倍，初始值1，即从个位开始取余数
				int bucketCount = bucketElementCount[digit];
				bucket[digit][bucketCount] = array[j];
				bucketElementCount[digit] = bucketCount + 1;
			}
			//3.2 然后遍历“桶”这个二维数组，把桶里的数据从前向后取出来，放回原数组.
			int index = 0;   //原数组下标起始值。
			for(int k = 0; k < bucket.length; k++){
				for(int n = 0; n < bucketElementCount[k]; n++){  
					array[index] = bucket[k][n];
					index++;  //注意原数组下标后移。
				}
				//重点注意：每个桶的数取完后，要把统计桶元素数的那个一维数组所在的下标里的元素值清空为0，否则有旧数据影响后续。
				bucketElementCount[k] = 0;
			}
		
		}
		
	
	
	}
	

	//遍历方法
	private static void list(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	//用递归的方法计算整数的位数。
	public static void recursionCount(int number){
		//这里就是判断当参数小于等于9时，递归的最后一个方法打印完数组，return,弹栈，后续的所有方法持续弹栈
		if(number <= 9){
			count++;
			return;
		}
		recursionCount(number / 10);
		count++;
		
	}


}