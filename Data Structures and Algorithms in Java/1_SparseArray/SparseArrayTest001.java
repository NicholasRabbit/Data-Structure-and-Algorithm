//稀疏数组代码实现
public class SparseArrayTest001 {

	public static void main(String[] args){
		sparseArray();
	}

	public static void sparseArray(){
		
		/*用二维数组模拟五子棋盘，然后用稀疏数组表示其中的棋子
		  用1表示白子，0表示空子，2表示黑子
		*/
		int[][]  arr01 = new int[11][11];
		arr01[1][2] = 1;
		arr01[2][3] = 2;   //还原图片中的棋子位置
		
		int count = 0;  //计数器，计算棋子数
		for(int i = 0; i < arr01.length; i++){
			for(int j = 0; j < arr01[i].length; j++ ){
				System.out.printf("%d\t", arr01[i][j]);    //\t是制表符tab
				if(arr01[i][j] > 0){
					count++;     //如果有棋子就自加一
				}
			}
			System.out.println();
		}

		/*上面的转换为稀疏数组范例
		index  row  col  value    index列不计入稀疏数组
		 0		11   11    2      第一行，表示上面的数组是11x11的，有两个位置有值
		 1		1    2     1      第二行，表示在原二维数组中的下标为[1][2]的元素值是1，即白子
		 2		2    3	   2      第三行，表示下标[2][3]的元素值是2，即黑子。其余未写的均为0
		*/
		
		//(1)，把二维数组转换为稀疏数组，原二维数组需要11x11个位置，而转换成的稀疏数组只要3x3个位置，内存得到了优化
		int[][] sparseArray = new int[count+1][3];    //行数是原二维数组内的不为0的元素数再加一行首行。
		//给第一行赋值
		sparseArray[0][0] = arr01.length;
		sparseArray[0][1] = arr01[0].length;
		sparseArray[0][2] = count;
		//给剩余行赋值，需要遍历赋值
		int index = 0;
		for(int i=0; i < arr01.length; i++){
			for(int j=0; j < arr01[i].length; j++){
				if(arr01[i][j] > 0){
					index++;    //如果有值，则自加一
					sparseArray[index][0] = i;
					sparseArray[index][1] = j;
					sparseArray[index][2] = arr01[i][j];   //每行遍历赋值

				}
			}
		}
		//遍历稀疏数组
		for(int i=0; i < sparseArray.length; i++){
			System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);  //一个%d定义一个参数，最后\n是换行
		}

		//(2)把稀疏数组还原为二维数组
		toNormalArray(sparseArray);

	}


	public static void toNormalArray(int[][] sparseArray){
		int hight = sparseArray[0][0];  //普通二位数组的长度
		int width = sparseArray[0][1];  //宽度，即其内作为元素的一维数组的长度
		//创建好二维数组
		int[][] normalArray = new int[hight][width]; 
		//赋值,因为前面已经定好了普通二维数组的长度，这里就从1即第二行开始遍历赋值
		for(int i=1; i< sparseArray.length; i++){
			int y = sparseArray[i][0];		//棋子的纵向y轴的坐标，按上面普通二维数组输出的结果来看，坐标系以右上角为0点
			int x = sparseArray[i][1];      //横向x周坐标
			int chess = sparseArray[i][2];  //棋子的值，即稀疏数组的最后一列的值
			normalArray[y][x] = chess;      //给指定好位置的二维数组赋值  
		}
		System.out.println("分割线===================");
		for(int i = 0; i < normalArray.length; i++){
			for(int j = 0; j < normalArray[i].length; j++ ){
				System.out.printf("%d\t",normalArray[i][j]);    //\t是制表符tab
			}
			System.out.println();
		}
	}
}