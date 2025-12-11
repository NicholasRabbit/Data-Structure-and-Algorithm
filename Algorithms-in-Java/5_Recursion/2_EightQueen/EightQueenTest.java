
/*
* 规则：
* 在8x8的棋盘上，放置8个皇后，使其不能互相攻击，即任意两个皇后都不能在同一行，同一列或统一对角斜线上。
* 注意：斜线指的是45°斜线，不是日字上的斜线。
* 解题思路：
* 1，首先可以用二维数据模拟棋盘，但是也可以用一维数组，可以把下标当作行，下标对应位置的元素值当作皇后所在正确列的位置。
* 
*/
public class EightQueenTest {
	
	//模拟棋盘的一维数组
	private static int[] chessBoard;
	private static int maxSize;
	private static int count;
	private static int checkCount;

	public static void main(String[] args){
				
		//初始化数组棋盘
		maxSize = 8;
		chessBoard = new int[maxSize];
		show(chessBoard);
		//放置皇后，从第一行第一列开始
		putQueen(0);
		System.out.println("放置皇后方法数：" + count);
		System.out.println("判断次数：" + checkCount);

	}


	
	//放置皇后的递归放法
	//n : 表示放置第n个皇后
	public static void putQueen(int n){
		/*
		* 如果n等于数组容量8，表示前面已经判断完0-7这八个元素了，而且这次八个皇后的方案合格(63行putQueen(n + 1)会执行)，
		* 才会执行递归调用，进入下面的if语句，然后可以在这里统计次数，打印皇后位置。
		*/
		if(n == maxSize){
			count ++;
			show(chessBoard);
			return;
		}
		
		//遍历行中的列，进行判断
		for(int i = 0; i < maxSize; i++){
			
			//表示当前行从第一列开始判断，下标n表是的n+1行。
			chessBoard[n] = i;  //用i表示元素值，0表示第一列，1是第二列，依次类推。
			
			/*
			* 重点：
			* 1，在这里进行递归调用，如果isInLine(n)结果是true则继续进行递归调用，
			* 2，否则则执行到A，皇后在当前行向后移动一列，继续检查
			* 3，递归调用举例：
			*    例，当前判断第二行第三列，chessBoard[1] = 2符合条件，执行if语句
			*    当到最后第八行最后一列，此时n=7，chessBoard[7] = 7，如过检查之前的有冲突的，
			*    则不执行if语句调用递归，而这个递归链最后一个方法的for循环执行结束，然后执行到方法B最后弹栈。
			*    弹到递归链的第一个方法的for循环处，执行i++，第二行的皇后移到第四列再向下判断执行递归。
			*/
			if(isInLine(n)){
				putQueen(n + 1);
			}

			//A:如果上面不符合要求，则继续执行遍历，i++，即皇后向后移动一列
		}
		
		//B:方法最后
	}

	
	
	//判断是否在同一列，同一行，同一对角斜线的方法
	//此方法是判断一维数组模拟棋盘中皇后是否可互相攻击的
	public static boolean isInLine(int n){
		
		//统计判断的次数
		checkCount ++;

		/*
		* 思路：
		* 1，循环遍历n行之前的所有行；
		* 2，首先判断是否在同一列，即array[i] == array[n]，因为数组元素的值代表列，下标代表行
		* 3，判断是否在45°对角线上，判断方法，利用绝对值的方式，求一个点周围所有45°角的斜线。
		* 4，不用判断是否在同一行，因为n就是下一行。
		* 结合笔记及图例理解。
		*/
		//这里i < n，因为是循环判断n行之前的行数，从第一行，即下标0开始。
		for(int i = 0; i < n; i++){
			if(chessBoard[i] == chessBoard[n] 
				|| Math.abs(chessBoard[i] - chessBoard[n]) == Math.abs(n - i)){
					//第一个绝对值是列的差值，第二个是求行的差值，如果想等说明这两点在同一斜线。
				return false;
			}
		}
		return true;
	}


	//遍历数组
	private static void show(int[] array){
		if(array == null) return;
		for(int i = 0; i < array.length; i++){
			System.out.printf("%d ",array[i]);
		}
		System.out.println();
	}


}