

import java.util.Date;
import java.text.SimpleDateFormat;


/*
* 一，冒泡排序复习:
* 二，对数组进行从小到大排序：{6,5,4,3,2,1}
* 思路：
* 1,首先从第一个数开始，跟第二个进行比较，如果前者小则交换位置，
*   然后第二个和第三个比较，依次类推，直到最后一个，这样第一轮就把最大的数放到最后了
*   第一轮排序:
*   5,6,4,3,2,1
*	5,4,6,3,2,1
*	5,4,3,6,2,1
*	5,4,3,2,6,1
*   5,4,3,2,1,6  这轮从第一个数开始，排序了5次，比数组长度小1，数组最后一个是最大的数了现在。
*   第二轮排序：
*	4,5,3,2,1,6
*	4,3,5,2,1,6
*	4,3,2,5,1,6
*	4,3,2,1,5,6  //这轮最后两个数最大的确定了。排序比较了4次
*	第三轮排序
*	3,4,2,1,5,6
*	3,2,4,1,5,6
*	3,2,1,4,5,6  //最后三个数确定
*	第四轮排序
*	2,3,1,4,5,6  
*	2,1,3,4,5,6   //最后四个数确定
*	第五轮排序
*	1,2,3,4,5,6   //所有顺序排好
* 总结：总共进行5轮排序，比数组长度小1，比较的时候从下标0开始，跟后一位比较，比较次数从5递减
* 思路和之前学习JavaSE时一样，这次按照数据结构老师的做法排序
* 三，冒泡排序的时间复杂度O(n^2)，n是数组的长度。
*/
public class BubbleSortTest {

	public static void main(String[] args){
		
		//测试大长度数组排序，暂时注释
		//int[] array = {60,40,50,30,10,20};
		//bubbleSort(array);

		//测试较大数组的排序时间
		int[] array2 = new int[50000];  //十万条数据
		for(int i = 0; i < array2.length; i++){
			array2[i] = (int)(Math.random() * 50000);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date start = new Date();
		System.out.println("开始时间==>" + sdf.format(start));
		bubbleSort(array2);
		Date end = new Date();
		System.out.println("结束时间==>" + sdf.format(end));
		//list(array2);


	}


	//排序方法，写法和个人JavaSE的写法不同，注意比较。
	//优化：当数组排好后不需再进行比较
	public static void bubbleSort(int[] array){
		
		int temp = 0;
		
		for(int n = 0; n < array.length -1; n++){
			
			boolean flag = false;  //是否比较标志
			
			//注意这里是i < array.length - 1，不是遍历时的i < array.length
			//因为要和[i+1]比较，防止数组越界。
			//array.length - 1 - n 表示比较的次数，随着最后的数字确定，次数减少
			for(int i = 0; i < array.length - 1 - n; i++){
				if(array[i] > array[i+1]){
					flag = true;    //走到这里说明本轮进行了比较
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}
			}
			//如果本轮没有比较的话，说明排序已完成，中断循环
			if(!flag) break;

			//测试大长度数组排序，暂时注释
			//System.out.print("第" + (n+1) + "次：" ); 
			//list(array);
			
		}
		

	}

	//遍历方法
	public static void list(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

}



