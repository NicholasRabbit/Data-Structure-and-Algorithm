
import java.util.Date;
import java.text.SimpleDateFormat;

/*
* 插入排序法
* 思路：
* 1，把现有的数组模拟分成两个数组；
* 2，第一步把第一个元素模拟当成一个数组，余下的元素为第二个数组；
* 3，从第二个数组中遍历元素跟第一个数组中的比较，如果小则前面，大则放后面。
* 插入排序时间复杂度：最好的时候，O(n)原数组已排好序，最坏 O(n^2)，完全逆序需要每个元素重新排的情况。
* Insertion sort time complexity: best O(n)  worse O(n^2)
*/
public class InsertionSortTest {

	public static void main(String[] args){
		
		int[] array = {5,4,3,2,1};
		int[] array2 = {5,4,3,2,1};
		
		insertSortByStep(array);
		
		insertSort(array2);
		list(array2);

		//测试插入排序时间
		//测试较大数组的排序时间，插入排序时间优于选择排序，更优于冒泡排序。
		int[] array3 = new int[100000000];  //十万条数据太少，看不出差别，用一亿条
		for(int i = 0; i < array2.length; i++){
			array3[i] = (int)(Math.random() * 100000000);
		}
		System.out.println("length==>" + array3.length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date start = new Date();
		System.out.println("开始时间==>" + sdf.format(start));
		insertSort(array3);
		Date end = new Date();
		System.out.println("结束时间==>" + sdf.format(end));

	}

	
	//插入排序，使用for循环自动完成排序
	public static void insertSort(int[] array){
		/*
		* 下标从1开始，不是0，因为把0下标的元素当作模拟的一个数组
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
	
	
	//插入排序，手动一步步交换，来理解思路。
	public static void insertSortByStep(int[] array){
	
		//第一次交换
		int insertValue = array[1];  //从第二个元素开始是要插入到之前的数组的，赋值给临时变量insertValue
		int insertIndex = 1 - 1; 
		/*
		* insertIndex --表示，每循环一次，下标前移继续判断前面的元素
		*/
		while(insertIndex >= 0 && insertValue < array[insertIndex]){
			array[insertIndex + 1] = array[insertIndex]; //把前面数值大的元素5赋值给array[1];
			insertIndex --;
		} //(1)while循环完之后：5,5,3,2,1

		/*
		* 退出while循环时，表示已找到合适的位置，已把数值大的数5移动到后面[1]的位置，
		* 但是由于向前遍历了insertIndex --，所以后移到0，把insertValue(4)这个值赋值给array[0]
		*/
		array[insertIndex + 1] = insertValue;  //(2)这里赋值完之后：4,5,3,2,1
		
		System.out.print("第一次交换：");
		list(array);

		/* 
		* 第二次交换：
		* 第一次形成的数组(4,5),3,2,1，括号内就是模拟的第一个数组。
		* 因为第一次交换完之后，数组的前两个元素已排好顺序，只要大就依次后移，不会出现乱序的情况。
		* 即不会出现(6,5),3,2,1，判断3比5小，5后移，比6小，6后移却在5之前的情况。
		*/
		insertValue = array[2];  //从第二个元素开始是要插入到之前的数组的
		insertIndex = 2 - 1; 
		/*
		* insertIndex --表示，每循环一次，下标前移继续判断前面的元素
		*/
		while(insertIndex >= 0 && insertValue < array[insertIndex]){
			array[insertIndex + 1] = array[insertIndex]; 
			insertIndex --;
		} //(1)while循环完之后：4,4,5,2,1
		/*
		* 退出while循环时，表示已找到合适的位置，已把数值大的数5移动到后面[1]的位置，
		* 但是由于向前遍历了insertIndex --，所以后移到0，把insertValue(4)这个值赋值给array[0]
		*/
		array[insertIndex + 1] = insertValue;  //(2)这里赋值完之后：3,4,5,2,1
		
		System.out.print("第二次交换：");
		list(array);

		//第三次第四次。。同理。
		
	}


	//遍历方法
	public static void list(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

}