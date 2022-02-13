/*本例使用环形数组解决例1中出现的问题
解决思路：利用取余数的思路，使得head,tail箭头走到数组尾部时，再添加元素则循环到数组头部
也可以把环形数组想象现在这个数组后边接的一个同样长度的数组，下标一直是0-4
*/

public class CircleArrayQueue {
	
	//头部箭头指向数组的第一个元素，与QueueArrayTest001里不一样
	private int head;	
	//尾部箭头指向最后一个元素的下一位，与上例也不一样，这一个位置占用数组中的一位，始终为空，也可以不留空位
	private int tail;	
	private int maxSize;
	private int[] circleArr;

	public CircleArrayQueue(){
	}
	//使用构造方法初始化数组
	public CircleArrayQueue(int maxSize){
		this.maxSize = maxSize;
		this.circleArr = new int[maxSize];
		//初始化head,tail为0，其中tail为0目的是留出一个空闲的位置，也可以不留
		//初始化语句可以不写，实例变量默认值就是0
		this.head = 0;  
		this.tail = 0;
	}

	//1,添加元素
	public void add(int element){
		if(isFull()){
			System.out.println("队列已满！");
			return;
		}
		circleArr[tail] = element;
		tail++;
	}
	
	//2,取出头部元素
	public int get(){
		//这里使用取余数的方法获取下标，以后index一直在0-4之间循环，从而实现了循环数组的效果
		//个人思路，有漏洞，head++一直加的话，可能会造成int类型值超限
		/*int index = head % maxSize;
		int headValue = circleArr[index];
		head++;   //如果数据多，head会超出int范围
		return headValue;  */

		//优化方案
		if(isEmpty()){
			System.out.println("没有元素，队列为空！");
			return -1;
		}
		int headValue = circleArr[head];
		head = (head + 1) % maxSize; //head后移一位，在这里取余数，再给head赋值，可解决上面超限问题
		return headValue;
	}

	//3,查看队列中有多少个元素
	public int getSize(){

		//这里加个判断队列为空
		if(isEmpty()){
			System.out.println("队列元素数量为0！");
		}
		//求出队列中的元素的个数
		//tail + maxSize实际思路还是把一个新的相同数组放到后面，因此tail的下标需要再加一个maxSize, 然后再跟head求差
		int size = (tail + maxSize - head) % maxSize;
		return size;
	}

	//4,遍历队列
	public void showAll(){
		//判断是否为空
		if(isEmpty()){
			System.out.println("队列为空！");
		}

		for(int i = head; i < head + getSize(); i++){
			int index = i % maxSize;  //这里要取余数，否则可能抛出数组超限异常
			System.out.println("circleArr下标: " + index + "==>" + circleArr[index]);
		}
	}

	//5,查看头部元素
	public int getHead(){
		return circleArr[head];
	}


	
	//判断队列是否为空
	public boolean isEmpty(){
		return head == tail;
	}

	//判断队列是否已满
	public boolean isFull(){
		//这里tail + 1就是留了一个空位置，可以不留，不留的话，数组满后，tail会循环指向0的位置
		boolean isFull = (tail + 1) % maxSize == 0;
		return isFull;
	}

}