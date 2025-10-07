
//生成Fibonacci sequece范例
public class FibonacciArray {
	
	private static int[] arr;
	
	public static void main(String[] args){
		
		
		int[] array = createFibonacci(20);
		System.out.println(arrayToString(array));
		//递归创建斐波那契数列
		arr = new int[20];   
		createFibByRecursion(19);   //注意：数组容量是20的话，最大index是19
		System.out.println(arrayToString(arr));


	}

	//一，使用循环的方法创建斐波那契数组
	public static int[] createFibonacci(int maxSize){
		int[] array = new int[maxSize];
		array[0] = 1;
		array[1] = 1;		
		for(int i = 2; i < maxSize; i++){
			array[i] = array[i-1] + array[i-2];	
		}
		return array;
	}

	//二，使用递归的方法创建斐波那契数组
	public static int createFibByRecursion(int index){
		if(index < 0){
			return 0;
		}else if(index == 0){
			arr[0] = 1;
			return arr[0];
		}
		arr[index] = createFibByRecursion(index - 1) + createFibByRecursion(index - 2);
		return arr[index];
		
	}



	//仿写Arrays.toString(..)
	private static String arrayToString(int[] array){
		if(array == null){
			return "null";
		}
		int maxIndex = array.length - 1;
		if(maxIndex == -1){
			return "{}";
		}
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		for(int i = 0; ; i++){  //这里for中间没有条件，因此无论如何回进入下面的if语句，所以最后不写return也不报错。
			builder.append(array[i]);
			if(i == maxIndex)
				return builder.append("}").toString();
			builder.append(",");
		}

	}



}