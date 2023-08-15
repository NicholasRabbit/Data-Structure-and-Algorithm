
import java.util.Date;
import java.text.SimpleDateFormat;

/*
* 归并排序：
* 1，时间复杂度是对数阶：nO(log n): 注意2是底数
* 2，采用分治策略，利用递归和三个下标left,mid,right，直到把数组分成每个元素一个数组（通过下标模拟的，并没有实际分）
* 当分到最后只有几个元素时，就开始比较大小并合并。
* 
*/
public class MergeSortTest {

	public static void main(String[] args){
		
		int[] array = {9,6,7,8,0,1,5,2,4,0};
		int[] temp = new int[array.length];
		divide(array,0,array.length - 1,temp);
		list(array);

		//测试速度，十万数据不到一秒，速度快
		int[] array2 = new int[100000];  
		for(int i = 0; i < array2.length; i++){
			array2[i] = (int)(Math.random() * 100000);
		}
		int[] temp2 = new int[array2.length];
		
		System.out.println("length==>" + array2.length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date start = new Date();
		System.out.println("开始时间==>" + sdf.format(start));
		divide(array2,0,array2.length - 1,temp2);
		Date end = new Date();
		System.out.println("结束时间==>" + sdf.format(end));
			

	}


	
	
	/*
	* 拆分数组，使用递归的特性结合下标进行虚拟的拆分。
	*/
	public static void divide(int[] array,int left,int right,int[] temp){
		
		if(left < right){
			int mid = (left + right) / 2;  //每次递归都会分成两份，所以时间复杂度是nO(log_2n)
			//向左递归，一直分到只有一个元素再弹栈
			divide(array,left,mid,temp);   //当最后剩一个元素，不符合if(left < right)时就开始弹栈
			//向右递归，同理
			divide(array,mid + 1,right,temp);   //当最后剩一个元素，不符合if(left < right)时就开始弹栈

			//当栈顶层的最后一个递归弹栈后就开始比较并合并
			merge(array,left,mid,right,temp);
			
		}
	
	}
	
	
	
	/*
	* 按顺序合并方法：
	* 参数：
	* array:原数组
	* left : 本次合并第一个数组(模拟的)的最左边下标。最开始合并下标就是0和1(right)进行比较
	* mid : 中间位置下标
	* right : 本次合并第二个数组(模拟的)的最右边的的下标。
	* temp: 临时数组
	* 步骤：
	* 本方法和递归结合，从最开始的一一比较，两两比较，直到最后五五比较完成。
	* 因为使用递归，所以两两比较前，每个数组中的两个元素已排好序，所以从开头两个数组各选一个元素
	* 再进行比较就可以生成四个元素排好序的数组，后面同理。
	*/
	public static void merge(int[] array,int left,int mid,int right,int[] temp){
		
		//设置临时指针
		int i = left;  //左边数组的起始位置。
		int j = mid + 1;  //右边数组起始位置，mid下标归左边。
		int t = 0;  //临时数组起始位置，从0开始。
		
		//一，先从两个数组的第一个元素拿出来比较，放入临时数组。
		/*
		* 如果左边的小，则先放入临时数组，同时下标后移一位，注意右边的下标不要后移，还要和它比较
		* 因为有可能左二的值还比右一小，例：{1,2,5}|{4,10,12}
		*/
		while(i <= mid && j <= right){   //注意设置循环条件，防止数组下标越界。
			if(array[i] < array[j]){
			temp[t] = array[i];
			i++;
			t++; //临时数组下标要后移
			//如果右边小则把它放入临时数组，右边下标后移移位，左边的不动
			}else{
				temp[t] = array[j];
				j++;
				t++; //临时数组下标要后移
			}
		}
		

		/*
		* 二，有可能上面比较完之后，有可能左边还留有几个都比右边最后一个元素(即右边所有元素)都大的元素
		*     需要将他们复制到临时数组里。
		*/
		//复制左边剩余的元素
		while(i <= mid){
			temp[t] = array[i];
			t++;
			i++;
		}
		//复制右边剩余的元素
		while(j <= right){
			temp[t] = array[j];
			t++;
			j++;
		}

		/*
		* 最后把本次比较完的放回原数组。
		* 注意：不是全部比较合并完才统一放回去，而是每次递归比较后放回原数组
		* 例：0,1下标比较合并排序好后还放回原数组0，1的位置
		*/
		int tempLeft = left;  //设置临时左边的下标，最开始就是0
		t = 0;    //临时数组的下标，从0开始
		while(tempLeft <= right){
			array[tempLeft] = temp[t];
			tempLeft ++;
			t++;
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