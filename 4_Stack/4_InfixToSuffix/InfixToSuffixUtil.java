
import java.util.*;


/*
* 中缀表达式转后缀表达式工具类
*/
public class InfixToSuffixUtil {

	//String expInfix = "26+15+(6+4)*16/5-7";	
	public static void toSuffix(String expInfix){
		
		//把字符串转化为char数组，也可把运算式拆分成字符串数组。
		char[] infixArray = expInfix.toCharArray();
		//符号栈
		ArrayStack operStack = new ArrayStack(20);
		//存后缀表达式的集合
		List<String> expSuffixList = new ArrayList<>();
		
		//遍历表达式进行转换
		String concatNum = "";  //拼接多位数，注意写在for循环外面
		for(int i = 0; i < infixArray.length; i++){
			
			char element = infixArray[i];
			
			if(operStack.isNumber(element)){				
				//1，如果是数字则拼接后入栈
				concatNum += element;
				//首先判断是否多位数。
				//先判断是否是中缀表达式的最后一位，因为如果是最后一位的话，infixArray[i + 1]会报数组越界异常。
				if(i == infixArray.length - 1  ){
					expSuffixList.add(concatNum);
					concatNum = "";   //入栈后要初始化
				//如果扫描到下一位是运算符或括号，则拼接数字完成了，直接入栈。
				}else if(!operStack.isNumber(infixArray[i + 1])){
					expSuffixList.add(concatNum);
					concatNum = "";
				}
				
			}else if(operStack.isOper(element)){
				/*2，如果是符号则进行判断 */
				//2.1 栈为空则，或者栈顶是左括号，则直接入栈。左括号char:'('对应int值是40。
				if(operStack.isEmpty() || operStack.getTop() == 40){
					operStack.push(element);
				//2.2 当前运算符优先级大于栈顶的，则直接入栈。
				//注意：比较优先级要调用同一个方法checkPriority()，不能写成：operStack.checkPriority(element) > operStack.getTop()，这样是比较的int类型的值，错误。
				}else if(operStack.checkPriority(element) > operStack.checkPriority((char)operStack.getTop()) ){
					operStack.push(element);
				//2.3 如果当前运算符的优先级小于等于栈顶的，则弹栈一次把弹出的加入到后缀表达式，注意弹栈后还要再次比较
				}else if(operStack.checkPriority(element) <= operStack.checkPriority((char)operStack.getTop()) ){
					//(1)循环与栈顶元素比较
					while(operStack.checkPriority(element) <= operStack.checkPriority((char)operStack.getTop())){
						expSuffixList.add(new Character((char)operStack.pop()).toString());  //因为用int[]数组模拟栈，所以要向下转型。
					}
					//(2)最后将当前的操作符入栈
					operStack.push(element);
				}

			}else{
				
				//3，最后一种情况，除了数字和运算符就剩括号了
				
				//3.2 当前元素左括号“(”，则直接入栈
				if(operStack.isBracket(element) == 0){
					operStack.push(element);
				//3.3 当前元素是右括号，则依次弹出运算符加入后缀表达式，直到左括号，最后去除左右括号。
				}else if(operStack.isBracket(element) == 1){
					//循环遍历直到栈顶是左括号
					while(operStack.isBracket((char)operStack.getTop()) != 0){
						expSuffixList.add(new Character((char)operStack.pop()).toString());	
					}
					operStack.pop();  //弹栈左括号
				}

			}
		}

		//4，把栈内剩余的符号弹出来，放到中缀表达式
		while(true){
			if(operStack.isEmpty()){
				break;
			}
			expSuffixList.add(new Character((char)operStack.pop()).toString());
		}

		System.out.println("expSuffixList==>" + expSuffixList);
	
	}

}