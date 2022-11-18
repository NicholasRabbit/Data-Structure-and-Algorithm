
import java.util.Scanner;


/**
* 使用栈模拟计算器，这里栈来计算符合人计算习惯的中缀表达式。
*/
public class CalStackTest {

	public static void main(String[] args){
		
		//operation();
		
		calculate();
		
	
	}
	
	//计算器方法
	public static void calculate(){
	
		String exp = "7+3*5-2";  //先写死表达式		
		char[] charArr = exp.toCharArray();
		//创建两个栈，一个数栈，一个运算符栈
		CalculatorStack numberStack = new CalculatorStack(5);  //初始化容量为5，注意计算位数多时适当加大栈的容量
		CalculatorStack operStack = new CalculatorStack(5);  
		//遍历表达式
		for(int i = 0; i < charArr.length; i++){
			char ch = charArr[i];
			//判断是数还是操作符
			boolean isNumber = numberStack.isNumber(ch);
			
			if(isNumber){
				//是数字则直接入栈
				//因为char类型的0-9实际用int的48-57表示，而运算要用int的所以这里转化为int类型，方便直接运算
				numberStack.push(ch - 48); 
			}else{
				//操作符栈内如果是空，或者扫描到的操作符优先级大于栈顶的，则直接入栈
				if(operStack.isEmpty() || operStack.checkPriority(ch) > operStack.checkPriority((char)operStack.getTop())){
					operStack.push(ch);
				}else{
					//除了上面的情况，就剩小于或等与栈顶操作符的情况了，需要把数字栈的头顶两个元素弹出计算，再放回去
					int first = numberStack.pop();  
					int second = numberStack.pop();  //如果是注意运算顺序，后弹出的数字是被减或被除数
					char oper = (char)operStack.pop();
					int result = numberStack.calculate(first,second,oper);
					numberStack.push(result);
					operStack.push(ch);  //别忘了计算完把本次的操作符也入栈，否则少运算符
				} 
			}
		}

		numberStack.show();
		operStack.show();
		//上面运行之后，符号栈内剩下的就是同等级别的运算符了
		//弹栈运算，直到数字栈内剩下一个数就是最终的结果
		int result = 0;
		while(true){
			//先检查这俩栈是否为空
			if(numberStack.isEmpty() || operStack.isEmpty()){
				break;
			}
			int first = numberStack.pop();
			int second = numberStack.pop();
			char oper = (char)operStack.pop(); //运算完，这里已弹出运算符
			result = numberStack.calculate(first,second,oper);
			numberStack.push(result);  //注意把运算的结果再入栈

		}
		System.out.println("计算结果==>" + numberStack.pop());

	}

	
	
	//栈的操作演示
	public static void operation(){
		CalculatorStack stack = new CalculatorStack(5);  //初始化容量为5
		//进行栈的相关操作
		String key = "";
		boolean flag = true;
		Scanner scan = new Scanner(System.in);
		while (flag){
	
			System.out.println("\n请输入相关操作：");
			System.out.println("遍历：show");
			System.out.println("入栈：push");
			System.out.println("出栈：pop");
			System.out.println("栈顶：top");
			System.out.println("退出：exit");
	
			key = scan.next();

			switch (key) {
				case "show":
					stack.show();
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
				case "top":
					int topValue = stack.getTop();
					System.out.println("栈顶==>" + topValue);
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