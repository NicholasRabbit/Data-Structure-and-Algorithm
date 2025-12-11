/*
 * Insertion sort.
 * */
public class InsertionSortReview {

	public static void main(String[] args){
		
		int[] arr = {5,4,3,2,1};
		//sort(arr);
		sortAuto(arr);

	}
	
	public static void sort(int[] arr){

		//1,
		int temp = arr[1];
		int index = 1;
		int i;
		for(i = index - 1; i >= 0; i--){
			if(temp < arr[i]){
				arr[i + 1] = arr[i];
			}
		}
		arr[i + 1] = temp;
		show(arr);
		
		//2,
		temp = arr[2];
		index = 2;
		for(i = index - 1; i >= 0; i--){
			if(temp < arr[i]){
				arr[i + 1] = arr[i];
			}
		}
		arr[i + 1] = temp;
		show(arr);
		


	}


	public static void sortAuto(int[] arr){

		for(int index = 1; index < arr.length; index++){
			int temp = arr[index];
			int i;
			for(i = index - 1; i >= 0; i--){
				if(temp < arr[i]){
					arr[i + 1] = arr[i];
				}
			}
			arr[i + 1] = temp;
			
		}

		show(arr);
		
	}



	public static void show(int[] arr){

		for(int i = 0; i < arr.length; i++){
			System.out.println(arr[i]);
		}
	
		System.out.println("================");

	}

}
