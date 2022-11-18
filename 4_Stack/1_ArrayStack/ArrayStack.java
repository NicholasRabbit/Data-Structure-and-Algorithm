/**
* 使用数组来模拟栈的操作
*/

public class ArrayStack{

	int top = -1;    //初始化栈，栈顶底部元素的下一格-1
	int maxSize;     //栈的最大容量
	int[] stackArr;  //这个数组模拟栈
	
	public ArrayStack(){
		
	}

	//1,初始化数组栈的容量
	public ArrayStack(int maxSize){
		if(maxSize <= 0){
			System.out.println("初始化数据错误！");
		}
		this.maxSize = maxSize;    //A:注意这里要在实例化的时候给属性this.maxSize复制，否则B处maxSize始终是初始值，导致栈一直判断为满。
		stackArr = new int[this.maxSize];
	}

	//2,判断是否栈满
	public boolean isFull(){
		//如果栈满，则top指向栈顶，下标登录maxSize - 1
		return top == maxSize -1;  //B:这里判断的时候注意this.maxSize是否在实例化的时候赋值了（A处）
	}

	//3,判断栈空，即top = -1时为空
	public boolean isEmpty(){
		return top == -1;
	}

	//4,入栈操作
	public void push(int element){
		if(isFull()){
			System.out.println("栈已满，无法添加。");
			return;
		}
		top ++;  //下标先上移再添加，因为当前栈指向栈顶元素，上移一位指向空的位置
		stackArr[top] = element;
	}

	//5,出栈或弹栈操作
	public int pop(){
		if(isEmpty()){
			throw new EmptyStackException("栈已空");  //如果是空栈，这里抛出异常或return个特殊值中断方法，否则下面的语句还会执行并报数组越界异常。
		}
		int temp = top;  //先把栈顶的下标赋值给临时变量，因为弹栈后下标要下移，但是return之后就不能写语句了，所以用个临时变量存下下标。
		top --;
		return stackArr[temp];
	} 

	//6,遍历栈，是从栈顶开始遍历
	public void list(){
		if(isEmpty()){
			System.out.println("栈为空");
			return ;
		}
		for(int i = top; i >=0 ; i--){
			System.out.println("stack[" + i + "]:" + stackArr[i]);
		}
	}



	
}