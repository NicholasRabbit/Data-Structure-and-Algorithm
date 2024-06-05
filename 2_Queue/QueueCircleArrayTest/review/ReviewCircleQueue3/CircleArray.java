
public class CircleArray {

	private int[] arr;
	private int size;
	private int top;
	private int bottom;

	public CircleArray(int size) {
		arr = new int[size];
		this.size = size;
		top = 0;  // pointing to the first element.
		bottom = 0;  // pointing to the position below the last element.
	}

	public void add(int e) {

		if (isFull()) {
			System.out.println("The array is full!");
			return;
		}

		arr[bottom] = e;
		bottom++;
		bottom = bottom % size;

	}

	public int pop() {

		if (isEmpty()) {
			System.out.println("The array is empty!");
			return -1;
		}

		int result = arr[top];
		top = ++top % size;
		return result;
	}

	public void show() {
		if (isEmpty()) {
			System.out.println("The array is empty!");
			return;
		}

		int count = getCount();
		for (int i = top; i < top + count; i++) {
			int index = i % size;
			System.out.println("index: " + i + ", element: "+  arr[index]);
		}
	}

	public int size() {
		return 0;
	}

	public int head() {
		return 0;
	}

	public boolean isFull() {
		int i = (bottom + 1) % size;
		return i == top;
	}

	public boolean isEmpty() {
		return top == bottom;
	}

	public int getCount() {
		return (bottom + size - top) % size;
	}

	
}
