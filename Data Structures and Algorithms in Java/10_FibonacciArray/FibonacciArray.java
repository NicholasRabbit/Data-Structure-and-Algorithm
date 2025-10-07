import java.util.*;
import java.text.*;

/*
* Fibonacci Array.
* Find the Nth number in the Fibonacci array.
*/
public class FibonacciArray {
   
   
   public static void main(String[] args) {
		
		int n = 100000; // position of the number to be found
     
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		Date start = new Date();
		System.out.println("start==>" + sdf.format(start));
		int result = fib(n);
		Date end = new Date();
		System.out.println("finish==>" + sdf.format(end));
	  
		System.out.println("The number at position " + n + " is " + result);	
   }

	
	/*
	* The time complexity is O(n)
	*/
	public static int fib(int n) {
      if (n <= 1) return n;
      
      int fib = 1;
      int prevFib = 1;
      
      for (int i = 2; i < n; i++) {
         int temp = fib;
         fib += prevFib;
         prevFib = temp;
      }
      
      return fib;
   }

}