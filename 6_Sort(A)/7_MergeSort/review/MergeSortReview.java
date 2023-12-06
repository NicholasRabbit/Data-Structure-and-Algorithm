

public class MergeSortReview{

	public static void main(String[] args){

		int[] arr = {10,13,3,5,2,1,78,25};

		divide(0,arr.length - 1,arr);
	
		show(arr);
		
	}

	
	public static void divide(int begin, int end, int[] arr){
		
		if(begin < end){
			int mid = (begin + end) / 2;
			divide(begin, mid, arr);
			divide(mid + 1, end, arr);
			
			//执行比较
			merge(begin,mid,end,arr);
		}
	
	}


	public static void merge(int begin, int mid, int end, int[] arr){
		
		//临时数组
		int[] tempArr = new int[end - begin + 1];
		int t = 0; //临时数组第一个下标
		int i = begin;
		int j = mid + 1;
		while(i <= mid && j <= end){
			if(arr[i] < arr[j]){
				tempArr[t] = arr[i];
				i ++;
				t ++;
			}else{
				tempArr[t] = arr[j];
				j ++;
				t ++;
			}
		
		}

		//左边还有剩余
		while(i <= mid){
			tempArr[t] = arr[i];
			t++;
			i++;
		}

		//右边有剩余
		while(j <= end){
			tempArr[t] = arr[j];
			t ++;
			j ++;
		}

		//放回原数组
		t = 0;
		while(begin <= end){
			arr[begin] = tempArr[t];
			t ++;
			begin ++;
		}

		
	}


	public static void show(int[] arr){

		for(int i = 0; i < arr.length; i++){
			System.out.println(arr[i]);
		}
	

	}



}




