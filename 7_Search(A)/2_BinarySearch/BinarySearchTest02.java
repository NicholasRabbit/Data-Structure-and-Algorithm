
import java.util.*;


//完善二分查找法，找出所有相同的值的下标
public class BinarySearchTest02{

	public static void main(String[] args){
		
		//数组已排好序
		int[] array = {13,25,37,66,73,96,100,100,100,367,421};

		List<Integer> list = binarySearch(array,100);
		System.out.println("list==>" + list);

	}


	//使用循环的方式自动完成查找
	public static List<Integer> binarySearch(int[] array,int value){
		
		//初始化数组，用来存所有符合要求的下标
		List<Integer> list = new ArrayList<>();

		//先初始化左中右三个下标
		int begin = 0;
		int end = array.length - 1;
		int mid = (begin + end) / 2;

		while(begin < end){
			if(array[mid] > value){
				//注意这里中间下标不符合要求就前移一位，否则到最后就一直等于某个下标（用递归的时候）
				end = mid - 1;  
				mid = (begin + end) / 2;
			}else if(array[mid] < value){
				begin = mid + 1;
				mid = (begin + end) / 2;
			//第3种情况：正好中间下标的值和要找的相等
			}else{
				//当找到一个符合的下标时，继续向左和向右查找是否有相同的值
				//1，向左扫描
				int temp = mid - 1;
				while(true){
					//当下标小于0或者数组值不登录查找的值时，中断循环
					if(temp < 0 || array[temp] != value){
						break;
					}
					//到这里说明值相等，把下标加到集合里。
					list.add(temp);
					temp -= 1;
				}
				
				//2，向右查找
				temp = mid + 1;
				while(true){
					if(temp > array.length - 1 || array[temp] != value){
						break;
					}
					//到这里说明值相等，把下标加到集合里。
					list.add(temp);
					temp += 1;
				}
				
				list.add(mid);  //最后别忘了加mid
			
				//注意：所有的都添加完后，要终止外部的while循环
				break;
			
			}

			
		}

		return list;

	}
}