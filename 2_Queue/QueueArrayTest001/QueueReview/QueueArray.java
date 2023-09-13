public class QueueArray{

	int head;
	int tail;
	int[] array;

	//去掉无参构造方法，必须初始化数组。
	//public QueueArray(){
	//
	//}

	public QueueArray(int size){

		//头部坐标,尾部坐标指向第一个元素的前一位
		head = -1; 
		tail = -1;
		//初始化数组
		array = new int[size];  
	}

	//1，添加元素到队列
	public boolean add(int e){
		if(tail == array.length - 1){
			System.out.println("No room for new element.");
			return false;
		}
		tail ++;
		array[tail] = e;
		return true;

	}

	//2，遍历元素
	public void show(){
		for(int i = head + 1; i < array.length; i++){
			System.out.println("element:" + array[i]);
		}
	}

	//3，出列
	public int pop(){
		if(tail == head){
			System.out.println("队列为空");
			return 0;
		}
		if(head == array.length -1){
			System.out.println("已全部出列");
			return 0;
		}
		head ++;
		return array[head];

	}

	//4，获取队列打头的元素值
	public int getHead(){
		if(tail == head){
			System.out.println("队列为空");
			return 0;
		}	
		return array[head + 1];
	}

}
