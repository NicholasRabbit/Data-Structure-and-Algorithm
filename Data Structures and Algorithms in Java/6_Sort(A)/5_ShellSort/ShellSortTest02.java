
import java.util.Date;
import java.text.SimpleDateFormat;

/*
* 移位法希尔排序，效率比交换法希尔排序高。
* 
*/
public class ShellSortTest02 {

	public static void main(String[] args){
		int[] array = {9,8,7,6,5,4,3,2,1,0};
		shellSort(array);
		list(array);


		/*
		* 测试希尔排序交换法
		* 使用移位法明显速度变快，10万数据不到一秒，而交换法得八秒。
		*/
		int[] array2 = new int[100000];  
		for(int i = 0; i < array2.length; i++){
			array2[i] = (int)(Math.random() * 100000);  //10万
		}
		System.out.println("length==>" + array2.length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date start = new Date();
		System.out.println("开始时间==>" + sdf.format(start));
		shellSort(array2);
		Date end = new Date();
		System.out.println("结束时间==>" + sdf.format(end));
	}

	//移位法，注意比较和希尔排序交换法的不同
	public static void shellSort(int[] array){
	
		//给数组循环分组。
		for(int group = array.length / 2; group > 0; group /= 2){
			
			//分好组之后，array[group]永远是所在组的第二个元素，例,array[5](前面有array[0]),array[2](前面也是array[0])。
			for(int i = group; i < array.length; i++){
				
				/*
				* 优化之处：分好组之后，每组按照插入排序的方式，进行逐个移位，
				* 而不是像ShellSortTest01中的交换的方式，这样减少内存访问次数，速度快。
				*/
				int j = i - group;  //本组的第一个下标
				int temp = array[i]; //temp就相当于InsertSort中的InsertValue
				while(j >= 0 && temp < array[j] ){  
					//如果相邻的两个元素，前面的小则后移一位，这里就是改良的地方，比交换法速度快。
					array[j+group] = array[j];
					j -= group; //因为间隔为group，所以这里-=group
				}
				array[j+group] = temp;
			
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
}