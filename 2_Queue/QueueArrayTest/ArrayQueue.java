//数组对象
//先写一个普通数组模拟队列，看看其出现的问题
public class ArrayQueue {
	
	private int head;		//指向队列第一个元素的前一位
	private int tail;		//指向队列的最后一个元素
	private int maxSize;	//数组的最大容量
	private int[] arr;      //数组对象

	public ArrayQueue(){
	}
	
	//1,初始化队列，确定其最大容量
	public ArrayQueue(int maxSize){
		this.maxSize = maxSize;
		this.arr = new int[maxSize];
		head = -1;
		tail = -1;
	}
	

	//2,添加元素
	public void add(int element){
		if(isFull()){
			System.out.println("队列已满！");
			return;
		}
		tail++;      //tail箭头向后移动一位，指向空的位置再添加元素
		arr[tail] = element;  //上面tail++了，这里不要再写arr[tail+1]了
	}

	//3,取出队列头部元素,
	public int get(){
		if(isEmpty()){
			System.out.println("队列已空！");
			return -1;
		}
		//head向后移动一位，指向第一个元素，待后面取出
		head++;
		//return这里移除头部元素，所以head还是指向第一个元素前的空位置,因此设定head指向第一个元素前一位使代码简洁，逻辑清晰了；
		return arr[head]; 
	}

	//4,查看队列头部元素
	public int headValue(){
		return arr[head + 1];
	}

	//5,判断队列是否为空
	public boolean isEmpty(){
		//如果队尾tail等于head,说明tail没有指向任何元素，即tail下标也需从指向0时取出一个元素后，变为-1了
		boolean isEmpty = (head == tail);
		return isEmpty;
	}
	//6,判断队列是否已满
	public boolean isFull(){
	   return tail == maxSize -1; //类似于length - 1
	}

	//7,展示所有队列内的元素
	public void showAll(){
		//这里加个判断队列为空，防止展示旧的数据
		if(isEmpty()){
			System.out.println("队列为空！没有数据！");
			return;
		}
		//注意：这里遍历从第一个元素开始，不要全数组遍历，会把“出列”的元素也展示出来
		for (int i = head + 1; i<arr.length; i++ ){
			System.out.println(i + "==>" + arr[i]);
		}
	}

}
