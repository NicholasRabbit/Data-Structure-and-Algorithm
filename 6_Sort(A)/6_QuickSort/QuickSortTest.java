
import java.util.Date;
import java.text.SimpleDateFormat;


/*
* 快速排序：
* 1，快速排序是对冒泡排序的一种改进。冒泡排序是从左到右这个判断并交换，
* 快速排序的交换是在基数的两头交换，交换距离扩大，针对大部分数组的话效率会高很多。
* 但是最差时间复杂度和冒泡排序相同都是O(n^2)，快速排序的平均时间复杂度是O(nlog_2n)，快了很多。
* 2，快速排序方法有的以数组第一个元素为基数，有的以中间下标的元素为基数进行判断。
* 本例采用后者。
*/
public class QuickSortTest {


	public static void main(String[] args){
		//int[] array = {9,8,7,6,5,4,3,2,1,0};  //这个全逆序数组，一次交换就完成了，没用到递归
		int[] array = {36,-5,0,78,0,46};  //写两个相同值的元素，测试3中所说的情况。
		int[] array2 = {9,5,7,6,5,4,3,2,1,0};

		quickSort(array,0,array.length - 1);  //这里时array.length-1,最右边的下标。
		list(array);

		//测试速度，速度比希尔排序移位法要快。
		int[] array3 = new int[100000];  
		for(int i = 0; i < array3.length; i++){
			array3[i] = (int)(Math.random() * 100000);
		}
		System.out.println("length==>" + array3.length);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date start = new Date();
		System.out.println("开始时间==>" + sdf.format(start));
		quickSort(array3,0,array3.length - 1);
		Date end = new Date();
		System.out.println("结束时间==>" + sdf.format(end));

	}

	/*
	* 快速排序方法:
	* 思路：
	* 1，取数组中间下标的数为基数，各从左右两头开始，与基数进行比较
	* 2，如果比基数大则放右边，比基数小则放左边。即当左边发现一个比基数大的，左边发现一个小的时才进行交换。
	* 3，如果有别的元素的值和基数相等时注意判断下标移动，详见下面解释。
	*/
	public static void quickSort(int[] array,int left,int right){	
		
		//把初始下标赋值给临时变量，下面遍历时下标要移动。
		int l = left;
		int r = right;
		//中间的基数值
		int pivot = array[(left + right) / 2];
		//临时变量
		int temp = 0;
		//l<r表示左面的下标还没有遇到右边的，继续循环判断，如果相等就停止，说明l下标和r重复。
		while (l < r){
			
			/*
			* 4，如果基数左右的数不相等，例如左边的元素少，右边的元素多，当l遍历到pivot所在下标时，
			* 而右边的又比pivot小《这时他们就会交换位置，即pivot值所在位置不是固定的，根据比较来随时调整下标，
			* 保证左边所有元素都比它小，右边都比它大。
			*/
			
			//当左边的数小于中间值pivot，则不交换，继续向后遍历。找到大于等于的数才进行交换
			while(array[l] < pivot){
				l++;
			}
			
			//当右边的数大于中间值pivot，则也不交换，继续向前遍历
			while(array[r] > pivot){
				r--;
			}

			//如果以上循环左右两个坐标相等或交叉，则终止循环
			if(l >= r){
				break;
			}

			//前面两个while循环之后，就找到了符合条件的两个值，然后进行交换。
			temp = array[l];
			array[l] = array[r];
			array[r] = temp;

			/*
			* 思路第3步，对下面的判断进行解释：
			* (1)因为上面两个内部的while循环都有=pivot的情况，条件重复，说明数组中有别的元素和中间基数pivot值相等，
			* 如果不移动r-=1或l+=1，则一直比较，一直交换，外部while就一直死循环。
			* (2)也可以把上面两个while的改成 <=pivot 或 >=pivot，不写下面的if语句(待多次验证)。
			*/
			if(array[l] == pivot){
				r--;
			}
			if(array[r] == pivot){
				l++;
			}
			
		
		}  //while loop end

		/*
		* 如果左右的下标相同了，则各自沿原方向再走一位，因为不走一位的话，数组中有两个数值相同的话
		* 下面的递归就会无穷尽，因为left或right的值没变，而left < r或 l < right总成立，则一直递归。
		*/
		if(l == r){
			l++;
			r--;
		}
		
		/*
		* 上面一个while之后，只是把比基准数小的放到了左边，大的放到了右边，左右两边的数还是无序的。
		* 需要进行递归调用，即把左边的当成一个数组再找基数按相同的方式交换排序。
		* 右边的同理也需要递归调用。（debug调试分析结论）
		*/
		//(1)左边的递归调用
		//left < r 说明从右面过来的下标和最初左边的下标之间还有距离，因为上面while循环完之后，r和l时重合的，
		//而r根据上面的第三个while循环，有右移的语句，方便递归调用，所以用这个条件。下面 l < right同理。
		if(left < r){
			quickSort(array,left,r);
		}
		//(2)右边的递归调用
		if(l < right){
			quickSort(array,l,right);
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