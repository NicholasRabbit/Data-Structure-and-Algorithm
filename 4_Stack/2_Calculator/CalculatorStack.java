
/*
* 相关概念：
* 中缀表达式：运算符在中间的运算式，例，3+4，符合人的运算习惯
* 前缀表达式：运算符在前面 + 3 4
* 后缀表达式：运算符在后面 3 4 +
* 本例用栈模拟计算器，计算中缀表达式
*/
public class CalculatorStack {

	int top = -1;  //初始指向栈底下一位
	int maxSize;
	int[] arrayStack;  //此数组模拟栈

	public CalculatorStack(){
	
	}
	//1，初始化栈的容量
	public CalculatorStack(int maxSize){
		//校验数据
		if(maxSize <= 0){
			System.out.println("数据格式错误，初始化栈容量应大于0");
		}
		this.maxSize = maxSize;
		arrayStack = new int[maxSize];
	}

	//2，判断栈是否为空
	public boolean isEmpty(){
		return top == -1;
	}

	//3，判断栈是否已满
	public boolean isFull(){
		//此处判断能起作用的前提是有参构造初始化栈的容量，如果只是实例化的话这个判断和判断空的效果一样
		return top == maxSize -1;  
	}

	//4，入栈
	public int push(int element){
		if(isFull()){
			System.out.println("栈已满");
			return -1;
		}
		top ++;
		arrayStack[top] = element;
		return 1;
	}
	
	//5，出栈	
	public int pop(){
		if(isEmpty()){
			System.out.println("栈已空！");
			return -1;   //栈空后这里要终止方法执行，防止下面的数组越界
		}
		int temp = top;  //这里把栈顶指针赋值给临时变量，因为弹栈后栈顶指针要下移，但又不能写在return后
		top --;
		return arrayStack[temp];
	}

	//6，遍历栈
	public void show(){
		if(isEmpty()){
			System.out.println("栈为空");
		}
		
		for(int i = top; i >= 0; i--){
			System.out.printf("arrayStack[%d]=%d\n",i,arrayStack[i]);
		}
	
	}

	//7，获取栈顶元素，计算器要判断栈顶元素
	public int getTop(){
		if(isEmpty()){
			System.out.println("栈为空");
			return -1;
		}
		return arrayStack[top];
	}

	//8和9用一个判断就行了，在运算式中不是数字就是运算符
	//8，判断运算式中是数字还是运算符
	public boolean isNumber(char number){
		//因为char本质上就是用int表示的，char类型的数0~9对应十进制的int值48~57，差值48。参照ASCII表。
		return number >= 48 && number <= 57;  //这里char自动向上转型为int。
	}

	//9，判断是否为运算符
	public boolean isOper(char oper){
		return oper == '+' || oper == '-' || oper == '*' || oper == '/';
	}

	//10，判断运算符的优先级，因为四种运算有两个等级，因此用数字来表示，1：优先级高，0：优先级低
	public int checkPriority(char oper){
		int priority = -1;
		if(oper == '*' || oper == '/'){
			priority = 1;
		}else if(oper == '+' || oper == '-'){
			priority = 0;
		}
		return priority;
	}

	//11，进行运算，second为栈中后弹出的数
	public int calculate(int first,int second,char oper){
		//减或除法的时候注意操作顺序
		int result = 0;
		switch(oper){
			//注意符号要对应准确
			case '+':
				result = first + second;
				break;
			case '-':
				result = second - first;
				break;
			case '*':
				result = second * first;
				break;
			case '/':
				result = second / first;
				break;
			default:
				break;

		}
		return result;
	}


}