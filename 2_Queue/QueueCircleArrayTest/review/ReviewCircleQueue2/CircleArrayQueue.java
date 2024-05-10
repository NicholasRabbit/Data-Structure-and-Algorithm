
public class CircleArrayQueue{

	//The top pointer point to first element.  (Its position is different from which it point to in ArrayQueue.)
	private int top;
	//The pointer of bottom point to the element to be added.
	private int bottom;
	int[] arr;
	int maxSize;

	public CircleArrayQueue(int maxSize) {
		top = 0;
		bottom = 0;
		this.maxSize = maxSize;
		arr = new int[maxSize];
	}

	//show all the elements
	public void show() {
		if (isEmpty()) {
			System.out.println("The queue is empty.");
			return;
		}

		int size = getSize();
		for (int i = top; i < top + size; i++) {
			int index = i % maxSize;
			System.out.println(arr[index]);
		}


	}


	public boolean isEmpty() {
		return top == bottom;
	}

	public boolean isFull() {
		// There is an empty
	    boolean b = (bottom + 1) % maxSize == top;
	    return b;
	}

	public boolean add(int i) {
		if (isFull()) {
			System.out.println("The queue has already been full.");
			return false;
		}
		
		// module
		bottom = bottom % arr.length;
		arr[bottom] = i;
		bottom = ++bottom % arr.length;

		return true;
	}



	public int pop() {
		if (isEmpty()) {
			System.out.println("The queue is empty.");
			return 0;
		}
		
		int n = top++;
		top = top % maxSize;
		return arr[n] ;
	}

	public int getHead() {
		return arr[top + 1];
	}

	public int getSize() {
		int size = (bottom + maxSize - top) % maxSize;
		return size;

	}

}
