
import java.util.*;

//后缀表达式计算器，适用于List<String>类型的逆波兰表达式
public class CalculatorUtil {

	
	public static String calculate(List<String> suffixExpList){
		//使用java的Stack栈类
		Stack<String> calStack =  new Stack<String>();	
		String result = "";
		Iterator<String> it = suffixExpList.iterator();
		while (it.hasNext()){
			String item = it.next();
			//判断当前元素是不是数，"\d+"是正则表达式，表示一位或多位数
			if(item.matches("\\d+")){
				calStack.push(item);

			//不是数字就是运算符
			}else{
				//减法或除法时注意运算顺序，这里后弹出的数是被减数或被除数
				int first = Integer.parseInt(calStack.pop());
				int second = Integer.parseInt(calStack.pop());;   //后弹出的数
				int resultInt = getResult(first,second,item);
				calStack.push(String.valueOf(resultInt));
			}

		}
		
		return calStack.pop();
	
	}


	private static int getResult(int first,int second,String oper){
		//减或除法的时候注意操作顺序
		int result = 0;
		switch(oper){
			//注意符号要对应准确
			case "+":
				result = first + second;
				break;
			case "-":
				result = second - first;
				break;
			case "*":
				result = second * first;
				break;
			case "/":
				result = second / first;
				break;
			default:
				break;

		}
		return result;
	}
		

}