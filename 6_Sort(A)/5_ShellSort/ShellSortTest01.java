
import java.util.Date;
import java.text.SimpleDateFormat;

/*
* ShellSort，希尔排序实在插入排序的基础上进行优化的一种排序。
* 
* 例：{2,3,4,5,6,1}
* 这样的数组，最小的在最后，使用插入排序的话前面5个每次都得后移，效率低。
* 希尔排序在这方面做了优化。
* 
* 本例采用交换法的希尔排序，还有使用移动法的再ShellSortTest02中。
* 
* 例：{9,8,7,6,5,4,3,2,1,0}
* 思路：
* 1，把数组除以2，array.length/2 = 5，每两个是一组，共5组，下标(0,5),(1,6)...依次类推。
*    每组使用插入排序排好。
* 2，再把上述分组结果数除以2，array.length/2/2 = 2，去掉小数。
*    现在是分成2组，每组还是按正常的插入排序。
* 3，继续分组：array.length/2/2/2 = 1，只剩一组，继续按插入排序排。
* 
*/
public class ShellSortTest01 {

	public static void main(String[] args){

		int[] array = {9,8,7,6,5,4,3,2,1,0};		
		shellSortByStep(array);

		int[] array2 = {9,8,7,6,5,4,3,2,1,0};		
		sheelSort(array2);

		//测试希尔插入排序时间，个人测试希尔排序比插入排序时间长……
		int[] array3 = new int[100000];  
		for(int i = 0; i < array2.length; i++){
			array3[i] = (int)(Math.random() * 100000);
		}
		System.out.println("length==>" + array3.length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date start = new Date();
		System.out.println("开始时间==>" + sdf.format(start));
		sheelSort(array3);
		Date end = new Date();
		System.out.println("结束时间==>" + sdf.format(end));
		
	}


	//方式一，先按思路，手动分布使用希尔排序，熟悉步骤。
	public static void shellSortByStep(int[] array){
		
		int group = array.length / 2;  //5,group变量备用。
		
		/*
		* 第一轮：
		* 因为分成了五组，第一组后面的元素下标是5(0,5)，所以从5开始，
		* 下一组是(1,6),(2,7)
		*/
		for(int i = 5; i < array.length; i++){
			
			/*
			* 在组内进行插入排序，因为步长5，所以j-=5，因为一组内可能不止两个元素。
			* 从j=5-5开始，即5左边第一个元素，符合插入排序的做法。
			*/		//这里是j=i-5，i是变化的，不要写死5
			for(int j = i - 5; j >= 0; j -= 5){  
				
				/*
				* 重点注意：以下的内部for循环写法错误，
				* 原因：外层for进入内层for后，外层i的值没有变化，如果是4，就一直是4，
				* 那array[i] < array[j]就一直在和array[4]比较，数组内只有两个以上元素，那插入排序的判断就错误了。
				*/
				/*if(array[i] < array[j]){
					int temp = array[j];
					array[j] = array[i];
					array[i] = temp;
				}*/
				
				//正确写法
				//如果下标5处的值小，则和0进行交换，依次类推
				if(array[j+5] < array[j]){
					int temp = array[j];
					array[j] = array[j+5];
					array[j+5] = temp;
				}
			}
		}
		
		//第一轮过后结果
		list(array);

		/*
		* 第二轮：
		* 继续分组array.length/2/2=2
		* 每隔一位组成一组，即下标(0,2,4,6,8)(1,3,5,7,9)这两组，然后各自执行插入排序
		*/
		group = (array.length / 2) / 2;  //=2
		for(int i = 2; i < array.length; i++){
			for(int j = i - 2; j >= 0; j -= 2){
				//如果下标2处的值小，则和0进行交换，依次类推
				if(array[j+2] < array[j]){
					int temp = array[j];
					array[j] = array[j+2];
					array[j+2] = temp;
				}
			}
		}

		//第二轮过后结果
		list(array);

		/*
		* 第三轮：
		* 继续除以2进行分组，array.length/2/2/2=1，这里直接是1组插入排序
		*/
		group = ((array.length / 2) / 2) / 2;  //=2
		for(int i = 1; i < array.length; i++){
			for(int j = i - 1; j >= 0; j -= 1){
				//如果下标1处的值小，则和0进行交换，依次类推
				if(array[j+1] < array[j]){
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}

		//第三轮过后结果
		list(array);

	
	}


	//方式二，使用变量，直接自动完成希尔排序。
	private static void sheelSort(int[] array){
		
		
		for(int group = array.length / 2; group > 0; group /= 2){
			for(int i = group; i < array.length; i++){
				for(int j = i - group; j >= 0; j -=group){
					if(array[j+group] < array[j]){
						int temp = array[j];
						array[j] = array[j+group];
						array[j+group] = temp;
					}
				}
			}
		}
		//打印结果
		//System.out.print("array2==>");
		//list(array);

	}

	
	
	//遍历方法
	private static void list(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

}