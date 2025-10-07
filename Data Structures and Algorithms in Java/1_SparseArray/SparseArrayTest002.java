public class SparseArrayTest002 {

	public static void main(String[] args){
		doSome();	
	}


	public static void doSome(){
		
		//Go chessboard
		int[][] go = new int[19][19];
		go[3][5] = 1;
		go[6][9] = 2;
		int count = 0;
		for(int i = 0; i < go.length; i++){
			for(int j = 0; j < go[i].length; j++){
				System.out.printf("%d\t",go[i][j]);
				if(go[i][j] > 0)
					count ++;
			}
			System.out.println();
		}
		
		//稀疏数组参数
		int column = 4;
		int row = count + 1;  //比原数组中的值大1，第一行存统计数据
		int[][] sparseArr = new int[row][column];
		sparseArr[0][0] = 0;
		sparseArr[0][1] = go.length;
		sparseArr[0][2] = go[0].length;
		sparseArr[0][3] = count;
		int index = 1;
		//遍历原数组
		for(int m = 0; m < go.length; m++){
			for(int n = 0; n < go[m].length; n++){
				if(go[m][n] > 0){
					sparseArr[index][0] = index;
					sparseArr[index][1] = m;
					sparseArr[index][2] = n;
					sparseArr[index][3] = go[m][n];
					index ++;
				}
			}
		}
	

		//遍历稀疏数组
		for(int i = 0; i < sparseArr.length; i++){
			for(int j = 0; j < sparseArr[i].length; j++){
				System.out.printf("%d\t",sparseArr[i][j]);
				count ++;
			}
			System.out.println();
		}

	
	}
}