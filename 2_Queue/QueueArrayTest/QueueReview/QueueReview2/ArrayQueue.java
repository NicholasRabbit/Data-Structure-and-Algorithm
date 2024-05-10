
public class ArrayQueue {

	//The top pointer point to the position above the fisrt element.
	private int top;
	//The pointer of bottom point to the element to be added.
	private int bottom;
	int[] arr;

	public ArrayQueue(int size) {
		top = -1;
		bottom = 0;
		arr = new int[size];
	}

	//show all the elements
	public void show() {
		if (isEmpty()) {
			System.out.println("The queue is empty.");
			return;
		}

		int index = top + 1;
		for (int i = index; i < bottom; i++) {
			System.out.println(arr[i]);
		}


	}


	public boolean isEmpty() {
		return top + 1 == bottom;
	}

	public boolean isFull() {
		return bottom == arr.length;
	}

	public boolean add(int i) {
		if (isFull()) {
			System.out.println("The queue has already been full.");
			return false;
		}

		arr[bottom] = i;
		bottom ++;
		return true;
	}

	public int pop() {
		if (isEmpty()) {
			System.out.println("The queue is empty.");
			return 0;
		}
		
		top++;

		return arr[top] ;
	}

	public int getHead() {
		return arr[top + 1];
	}

}
