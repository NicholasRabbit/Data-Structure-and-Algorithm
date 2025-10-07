
/*
 * 环形数组模拟队列，可以实现无线循环添加和去除元素
 * 1，head,tail的起始位置不一样了，参照示意图，需要空一个位置，防止head超过tail
 *    head始终指向元素的前一个位置，tail始终指向最后一个元素的下一个位置，这样方便
 *    判断队列的空或满的状态。
 * */
public class CircleArrayQueue {
	
	int head;
	int tail;
	int maxSize;
	int[] array;

	public CircleArrayQueue(int maxSize){
		head = 0;
		tail = 1;
		this.maxSize = maxSize;
		array = new int[maxSize];
	}

	//1,添加元素
	public boolean add(int e){
		if(tail == head){
			System.out.println("队列已满");
			return false;  //满了之后要中断方法，不要再添加了。
		}

		array[tail] = e;
		
		//重点，环形队列，下标累加后跟最大容量取余数，这样下标可以在0-4(数组容量为5)之间循环
		tail ++;
		tail = tail % maxSize;
		return true;
	
	}

	//2，遍历元素
	public void show(){
		for(int i = 0; i < array.length; i++){
			System.out.println("element:" + array[i]);
		}
	}

	//3,出列
	public int pop(){
		if( head + 1 == tail){
			System.out.println("队列已空");
		}
		head ++;
		return array[head];
	}

	//4,获取打头元素
	public int getHead(){
		if( head + 1 == tail){
			System.out.println("队列已空");
		}
		return array[head + 1];

	}




}
