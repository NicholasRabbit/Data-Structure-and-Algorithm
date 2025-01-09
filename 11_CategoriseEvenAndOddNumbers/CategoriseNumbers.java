


/*
 * To move all the even numbers to one side of the array 
 * and odd numbers to the other.
 * */
public class CategoriseNumbers {

	public static void main(String[] args){

		int[] arr = {1,2,3,4,5,6,7,13,15,16,19,40,42};
		//algorithmOne(arr);

		int[] arr2 = {1,2,3,4,5,6,7,13,15,16,19,40,42};
		twoPointers(arr2);
	}

	/*
	 * Solution One
	 * A simple and staight way.
	 * */
	public static void algorithmOne(int[] arr) {
		int[] even = new int[arr.length];
		int[] odd = new int[arr.length];
		 
		int eCount = 0, oCount = 0;
		System.out.print("original array: ");
		for (int i = 0; 
				i < arr.length; 
				i++) {
			if (arr[i] % 2 == 0) {
				even[eCount] = arr[i];
				eCount++;
			} else {
				odd[oCount] = arr[i];
				oCount++;
			}
			System.out.print(arr[i] + " ");
		}

		System.out.println();

		for (int j = 0; j < even.length; j++) {
			System.out.print(even[j] + " ");
		}

		System.out.println();

		for (int j = 0; j < odd.length; j++) {
			System.out.print(odd[j] + " ");
		}

		for (int j = 0; j < eCount; j++) {
			arr[j] = even[j];
		}

		for (int i = 0, j = eCount; j < arr.length; j++, i++) {
			arr[j] = odd[i];
		}

		System.out.println();
		
		System.out.println("result:");
		for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + " ");
		}


	}


	/*
	 * Move the even number to the left side and odd number to the right.
	 * The second solution:
	 * Using two pointers to find out the even and odd numbers, respectively.
	 * The left pointer starts from the beginning of the array and the other starts from the end of 
	 * the array. 
	 * If and only if the left pointer find an odd number and the right pointer find an even number,
	 * switch these two numbers.
	 * */
	public static void twoPointers(int[] arr) {

		int left = 0, right = arr.length - 1;
		
		while (left != right) {
			if (arr[left] % 2 == 0)
				left++;
			if (arr[right] % 2 != 0)
				right--;
			if ((arr[left] % 2 != 0) && (arr[right] % 2 == 0)) {
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			}
		}

		System.out.println("result2:");
		for (int j = 0; j < arr.length; j++) {
			System.out.print(arr[j] + " ");
		}

	
	}

}

