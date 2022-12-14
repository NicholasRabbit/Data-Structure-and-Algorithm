

/*
* 比较冒泡排序和选择排序的优劣
* 1，两者的比较次数相同，交换元素的次数不同，
* 选择排序的交换次数少，因为它是找到最小元素所在下标后才进行交换的，依次到位。
* 本例中：
* 方法  比较次数  交换次数
* 冒泡  15			15
* 选择  15			3   //明显选择排序性能更优
* 2，处理大量数据时选择排序时间就会短很多。
*/
public class CompareBubbleAndSelectTest {

	public static void main(String[] args){
		
		int[] arrs={6,5,4,3,2,1}; 
		int[] arrs2={6,5,4,3,2,1}; 

		bubbleMethod(arrs);  
		list(arrs);
		select(arrs2);
		list(arrs2);
	}



	//冒泡排序
	public static void bubbleMethod(int[] arrs){ 
       int count=0;
	   int exchange = 0;
	   for(int i=arrs.length-1;i>0;i--){
	       /*
		   * 判断条件 j=0，j<i，表示每次都从第一个开始判断j和j+1位置的大小
		   * 例，第一轮，当i=5时，判断0-4 < 5，判断了5次，符合上面的思路分析。
		   */
		   for(int j=0;j<i;j++){
               count++;  		   
		       if(arrs[j]>arrs[j+1]){
			       exchange ++;
				   int temp;
			       temp=arrs[j];      //此处temp必须写在前面，因为要给局部变量赋值，写成arrs[j]=temp就错了。
				   arrs[j]=arrs[j+1]; //因为java先计算等号右边的结果，再把得到的值赋给左边，temp不能在左边。
				   arrs[j+1]=temp;
			   
			   }

		   }

	   }
       
	   System.out.println("冒泡比较次数："+count);
	   System.out.println("冒泡交换次数==>" + exchange);
	  
	
	}
	
	//选择排序法
	public static void select(int[] arr){
		int count = 0; //比较次数
		int exchange = 0;  //交换次数
		
		for(int i=0;i<arr.length-1;i++){    //做法：把最左边的数与右边的所有数比较，第一次比较最左边数下表0，第二次是1，依次类推。   
		                                    //因此，i=0,为初始值。length-1表示比较5次，比数组长度小1。
		    int min=i;
			for(int j=i+1;j<arr.length;j++){   //紧邻下表i的数j=i+1为初始值，开始循环比较。
			    count ++;
				if(arr[j]<arr[min]){
				    min=j;                  //如果if语句成立，把min赋值为j
				}
				
				//写这里错误，这样没必要每次都交换，当内存for循环完之后，找到最小的再判断
				/*if(min != i){               //此处成立说明有比arr[i]更小的数，则进行左移。
				   exchange ++;
				   int temp;
				   temp=arr[i];
				   arr[i]=arr[min];
				   arr[min]=temp;
				}
				*/
				
			}

			//内存for判断完之后，在这里进行交换，此处成立说明有比arr[i]更小的数，则进行左移。
			if(min != i){               
			   exchange ++;
			   int temp;
			   temp=arr[i];
			   arr[i]=arr[min];
			   arr[min]=temp;
			}
		}

		System.out.println("选择排序法比较次数==>" + count);
		System.out.println("选择排序法交换次数==>" + exchange);
	
	}


	//遍历方法
	public static void list(int[] array){
		for(int i = 0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}


}