public class SparseArrayReview_2 {

	public static void main(String[] args){

		int[][] originalArray = new int[19][19];
		list(originalArray);

		// add chess
		originalArray[2][3] = 1;
		originalArray[5][16] = 2;
		originalArray[6][7] = 2;
		originalArray[12][18] = 1;
		list(originalArray);

		// get a sparse array.
		int[][] sparse = getSparseArray(originalArray);
		list(sparse);

		// recover the original array
		int[][] recoverArr = recover(sparse);
		list(recoverArr);
	
	}

	public static void list(int[][] array){
		if(array == null)
			return;

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.printf("%d  ", array[i][j]);
			}
			System.out.println();
		}

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

	}


	public static int count(int[][] array){
		if(array == null)
			return 0;

		int count = 0;

		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(array[i][j] > 0)
					count ++;
			}
		}
		return count;
	}
	
	public static int[][] getSparseArray(int[][] array) {
		if(array == null){
			System.out.println("the array is a null");
			return null;
		}
		//Count rows and columns.
		int rows = array.length;
		int columns = array[0].length;
		int count = count(array);

		System.out.println("rows: " + rows);
		System.out.println("columns: " + columns);
		System.out.println("count: " + count);
		
		int[][] sparse = new int[count + 1][3];
		sparse[0][0] = rows;
		sparse[0][1] = columns;
		sparse[0][2] = count;

		int chess = 1;
		for (int m = 0; m < array.length; m++) {
			for (int n = 0; n < array[m].length; n++) {
				if (array[m][n] > 0) {
					sparse[chess][0] = m;
					sparse[chess][1] = n;
					sparse[chess][2] = array[m][n];
					chess++;
				}
			}
		}

		return sparse;

	}

	public static int[][] recover(int[][] sparse) {
		
		int rows = sparse[0][0];
		int columns = sparse[0][1];
		int count = sparse[0][2];
		int[][] originalArray = new int[rows][columns];

		//To get chess from the second row.
		for (int k = 1; k < sparse.length; k++) {
			int row = sparse[k][0]; 
			int column = sparse[k][1]; 
			int value = sparse[k][2]; 
			originalArray[row][column] = value;
		}

		return originalArray;
	}

}
