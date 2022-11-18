
import java.util.Scanner;


public class StackTest {

	public static void main(String[] args){
		//用数组模拟栈，初始化容量为5。
		ArrayStack  stack = new ArrayStack(5);
		System.out.println(stack.stackArr.length);
		
		//进行栈的相关操作
		String key = "";
		boolean flag = true;
		Scanner scan = new Scanner(System.in);
		while (flag){
	
			System.out.println("请输入相关操作：");
			System.out.println("遍历：show");
			System.out.println("入栈：push");
			System.out.println("出栈：pop");
			System.out.println("退出：exit");
	
			key = scan.next();

			switch (key) {
				case "show":
					stack.list();
				break;
				case "push":
					System.out.println("请输入参数：");
					int element = scan.nextInt();
					stack.push(element);
				break;
				case "pop":
					int retValue = stack.pop();
					System.out.println("弹栈值==>" + retValue);
				break;
				case "exit":
					flag = false;
				break;	 
				default:
					System.out.println("命令错误，请检查后再输入!");
					break;
				
			}
		
		}

	}
}