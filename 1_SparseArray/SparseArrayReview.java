//review of sparse array

public class SparseArrayReview {
	
	public static void main(String[] args){
		sparseArr();
	}

	public static void sparseArr(){
		
		//原数组,模拟围棋
		//规定：1，白棋，2，黑棋
		int[][] go = new int[19][19];
		go[3][10] = 1;
		go[15][9] = 2;
		go[18][8] = 1;
		go[11][13] = 2;

		//遍历数组，查看棋子摆放
		int chessCount = 0;  //统计棋子数
		for(int i = 0; i < go.length; i++){
			for(int j = 0; j < go[i].length; j++){
				System.out.print(go[i][j] + " ");
				if(go[i][j] > 0)
					chessCount ++;
			}
			System.out.println();
		}

		/*
		 * 生成稀疏数组
		 * 1,总棋子数，原棋盘的长宽作为新的稀疏数组的第一行数据
		 * 2,后面的每行为其横向坐标，纵向坐标，棋子颜色
		 * */
		System.out.println("number of chess is ==> " + chessCount);

		int[][] sparseArray = new int[chessCount + 1][3];  //行数加一行统计数据，列数是3
		//第一行统计数据
		sparseArray[0][0] = 19;
		sparseArray[0][1] = 19;
		sparseArray[0][2] = chessCount;
		//放进稀疏数组
		int index = 1; //稀疏数组从第二行开始表示棋子坐标
		for(int k = 0; k < go.length; k++){
			for(int m = 0; m < go[k].length; m++){
				if(go[k][m] > 0){
					sparseArray[index][0] = k;
					sparseArray[index][1] = m;
					sparseArray[index][2] = go[k][m];
					index ++;

				}	
			}
		}

		//遍历稀疏数组
		for(int g = 0; g < sparseArray.length; g++){
			for(int h = 0; h < sparseArray[g].length; h++){
				System.out.print(sparseArray[g][h] + " ");
			}
			System.out.println();
		}


		//还原数组
		int row = sparseArray[0][0];
		int column = sparseArray[0][1];
		int count = sparseArray[0][2];
		int r = 1; //从稀疏数组第二行开始遍历
		int[][] originArr = new int[row][column];
		for(int i = 1; i <= count; i++){
			row = sparseArray[i][0];
			column = sparseArray[i][1];
			int value = sparseArray[i][2]; //棋子颜色
			originArr[row][column] = value;
		}

		System.out.println("============");
		for(int i = 0; i < originArr.length; i++){
			for(int j = 0; j < originArr[i].length; j++){
				System.out.print(originArr[i][j] + " ");
			}
			System.out.println();
		}




		
	
	}

	

}
