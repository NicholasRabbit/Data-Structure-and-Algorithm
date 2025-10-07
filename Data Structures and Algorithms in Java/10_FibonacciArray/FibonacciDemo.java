
/*
* ¸öÈËÁ·Ï°
*/
public class FibonacciDemo {

	public static void main(String[] args){
		
		System.out.println(find(6));
			
	}

	
	public static int find(int n){
		if(n <= 1)
			return n;

		int fib = 1;
		int preFib = 1;
		for(int i = 2; i < n; i++){
			int temp = fib;
			fib += preFib;
			preFib = temp;
		}

		return fib;
	
	
	}
}