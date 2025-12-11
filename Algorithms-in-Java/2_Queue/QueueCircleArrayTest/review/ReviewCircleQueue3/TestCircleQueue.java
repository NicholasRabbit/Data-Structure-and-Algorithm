
import java.util.Scanner;

public class TestCircleQueue{

	public static void main(String[] args){
		test();
	}


	public static void test() {
		
		// Intialisation.
		CircleArray queue = new CircleArray(5);

		while(true){
			System.out.print("Please input a command: add(a),show(s),pop(p),head(h): ");
			Scanner scan = new Scanner(System.in);
			String order = scan.nextLine();
			int element = 0;
			switch (order) {
				case "a" : 
					System.out.print("Please input a number:");
					element = scan.nextInt();
					queue.add(element);
					break;
				case "s" : 
					queue.show();				
					break;
				case "p" :
					int result = queue.pop();
					System.out.println("pop：" + result);
					break;
				case "h" : 
					int head = queue.head();
					System.out.println("the head is：" + head);
					break;
				case "g" :
					int size = queue.size();
					System.out.println("The number of elements: " + size);
					break;
				default :
					break;
			
			}
			
		}
		

	}

}
