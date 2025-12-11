
import java.util.Scanner;

public class TestArrayQueue {

	public static void main(String[] args){
		
		test();

	}


	public static void test() {
		
		// Intialisation.
		ArrayQueue queue = new ArrayQueue(5);

		while(true){
			System.out.print("请输入操作指令:添加(a),查看(s),出列(p),打头(h): ");
			Scanner scan = new Scanner(System.in);
			String order = scan.nextLine();
			int element = 0;
			switch (order) {
				case "a" : 
					System.out.print("请输入要添加的数字:");
					element = scan.nextInt();
					queue.add(element);
					break;
				case "s" : 
					queue.show();				
					break;
				case "p" :
					int result = queue.pop();
					System.out.println("出列元素：" + result);
					break;
				case "h" : 
					int head = queue.getHead();
					System.out.println("打头元素：" + head);
					break;
				default : 
					break;
			
			}
			
		}
		

	}

}
