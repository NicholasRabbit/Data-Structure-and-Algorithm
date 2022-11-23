
/**
* 使用栈进行逆波兰表达式（后缀表达式）的运算
* 1，逆波兰表达式可使用栈这种数据结构较容易的实现，并且也减少了内存访问次数，优于栈模拟中缀表达式计算的性能。
* 只用一个栈就可以完成运算操作。
* 2，计算规则：
* 例：中缀表达式 6+5*3-7
*     后缀表达式 6 5 3 * + 7 -
* 从左到右扫描，依次入栈，遇到运算符则把运算符左边的两个数弹出进行运算，结果再入栈，这种方式不用再判断运算符的优先级了。
* 后面再遇到运算符则弹出栈顶两个数进行运算，结果入栈，一直到最后只有一个数就是最终结果。
*/
public class NotationTest {

	public static void main(String[] args){
		//6+5*3-7 = 14
		String exp = "6 5 3 * + 7 -";  //这里每个数中间用空格隔开方便观察，有两位数的话不容易混淆。
		//37+52*32-76 = 1625
		String exp2 = "37 52 32 * + 76 -";  //这里每个数中间用空格隔开方便观察，有两位数的话不容易混淆。
		
		int result = calculate(exp2);
		System.out.println("result==>" + result);
		
	}


	//代码相对于中缀表达式的简洁了。
	public static int calculate(String exp){
		char[] charArray = exp.toCharArray();
		CalculatorStack calStack =  new CalculatorStack(10);	
		int result = 0;
		String concatNum = "";
		for(int i = 0; i < charArray.length; i++){
			char ch = charArray[i];
			
			//如果元素为空，或栈是数则直接入栈，为空格不进入此条件内
			if(calStack.isEmpty() || calStack.isNumber(ch)){
				
				concatNum += ch;  //拼接多为数字
				
				//calStack.push(ch - 48);  //个位数的运算按此方式入栈，详见ASCII码表
				//有多位数的运算，则需要把多位数拼接起来，int ：32对应char的空格space
				//当前元素的下一格是空格，则把之前拼接的数字入栈
				if(32 == charArray[i+1]){
					calStack.push(Integer.parseInt(concatNum));
					concatNum = "";  //入栈后别忘了初始化
				}
				

			//这里要用else if 不要用else，因为有空格
			}else if(calStack.isOper(ch)){
				//减法或除法时注意运算顺序，这里后弹出的数是被减数或被除数
				int first = calStack.pop();
				int second = calStack.pop();   //后弹出的数
				result = calStack.calculate(first,second,ch);
				calStack.push(result);
			}
		}
		
		return result;
		
	
	}
	

}