
import java.util.Date;
import java.text.SimpleDateFormat;


/*
* 选择排序法
* 思路
* 从数组最左边的元素开始，遍历后面的其他元素，找到最小的元素，把下表给一个变量min
* 如果min和最开始的i值不同，说明有更小的值，则下标i上的值和min上的值进行交换
* 步骤：
* 第一轮：
* 4,5,3,2,1  //内层的每次for循环之后的结果，注意只是交换下标，并没有进行元素值的交换
* 3,5,4,2,1
* 2,5,4,3,1
* 1,5,4,3,2  //内层for完了之后，在外层for才进行元素值的交换。因此才体现选择排序比冒泡排序速度快。
* 第二轮:拿第二个数与之后的进行比较，因为第一个已经确定最小
* (1),4,5,3,2
* (1),3,5,4,2
* (1),2,5,4,3
* 第三轮：
* (1),(2),4,5,3
* (1),(2),3,5,4
* 第四轮：
* (1),(2),(3),4,5
* 总结：选择排序法时间复杂度O(2^n)
*/
public class SelectSortTest {
	
	public static void main(String[] args){
		int[] array = {5,4,3,2,1};
		selectSort(array);
		list(array);

		//测试较大数组的排序时间
		int[] array2 = new int[50000];  //五十万条数据
		for(int i = 0; i < array2.length; i++){
			array2[i] = (int)(Math.random() * 50000);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date start = new Date();
		System.out.println("开始时间==>" + sdf.format(start));
		selectSort(array2);
		Date end = new Date();
		System.out.println("结束时间==>" + sdf.format(end));
		
	}

	public static void selectSort(int[] array){
		
		for(int i = 0; i < array.length - 1; i++){
			
			//假设当前下标的元素为最小值，然后再与其后面的元素比较
			int min = i; 
			//循环遍历后面的元素，找到最小的值
			for(int j = i + 1; j < array.length; j++){
				//如果假设不成立，前面的大，后面的小，则交换下标，然后j++,再继续循环判断看后面还有更小的没有，有就交换下标；
				if(array[min] > array[j]){
					min = j;
				}
				
				//交换值的语句，写这里错误，这样没必要每次都交换，当内存for循环完之后，找到最小的再判断。
				//否则选择排序和冒泡排序的交换次数一样了，体现不出优势了。
				/*  
				if(i != min){
				int temp = array[i];
				array[i] = array[min];
				array[min] = temp;
				*/
					
			}

			//交换值的语句写这里，在这里才体现选择排序比冒泡排序速度快，因为交换次数少，读取内存次数少则在处理大量数据时时间就会短很多。
			//如果有更小的值，说明min的值发生了变化，min就是最小元素的下标。与最初的i不相等了，进行元素数值交换
			//内存for判断完之后，在这里进行交换，此处成立说明有比arr[i]更小的数，则进行左移。
			if(i != min){
				int temp = array[i];
				array[i] = array[min];
				array[min] = temp;
			}
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