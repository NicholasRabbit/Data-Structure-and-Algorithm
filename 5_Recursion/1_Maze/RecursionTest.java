
/*
* 使用递归来进行迷宫回溯，可对二维数组画图进行模拟对比。
*/
public class RecursionTest {

	public static void main(String[] args){
		mazeTest();
	}

	
	public static void mazeTest(){
		//使用二维数组模拟迷宫
		int[][] maze = new int[9][8];  //二维数组，第一个9指的是最外层数组的容量，第二个8指的是作为数组内元素的一维数组的容量。

		//遍历二维数组，打印出的图形，其左上角为0点。
		scanArray(maze);

		//迷宫四周造墙
		buildWall(maze);
		System.out.println("======造墙后遍历======");
		scanArray(maze);
		
		//加一个挡板
		maze[3][1] = 1;
		maze[3][2] = 1;
		System.out.println("======加挡板后遍历======");
		scanArray(maze);
		
		/*
		* 开始回溯
		*/
		findWay(maze,1,1);
		System.out.println("======走完迷宫后遍历======");
		scanArray(maze);

		//测试走不通的情况
		maze[1][2] = 1;
		maze[2][2] = 1;
		findWay(maze,1,1);
		System.out.println("======走不通后遍历======");
		scanArray(maze);

	
	}
	



	
	/*
	* 用这个方法进行递归，模拟在迷宫走到右下角maze[7][6]的位置。
	*/
	/*
	* 迷宫回溯规则：
	* 一，“1”表示墙，不能走；
	*     “2”表示走过的路径，可以走通；
	*     “3”表示走过却未走通的路；
	*     “0”表示未走的路。
	* 二，x,y表示起点,注意：x是表示横轴，y表示竖轴(即对应的二维数组maze[9][8]的[9])
	* 三，行走方案：下->右->上->左，也可按照其他方案进行
	*/
	public static boolean findWay(int[][] maze, int y, int x){  //注意这里坐标y在x之前。
		boolean flag = false;
		//走到终点就停止递归。
		if(maze[7][6] == 2){
			return true;
		}
		//下面进行:下->右->上->左的判断
		//maze[y][x] == 0表示这个点还没有走过
		if(maze[y][x] == 0){
			//赋值2，代表已走。
			maze[y][x] = 2;
			//1,向下移动，如果下面的是0则向下走
			if(maze[y+1][x] == 0){
				//1.2 使用递归调用
				findWay(maze,y+1,x);  
			//2,如果上面的走不通，则向右走
			}else if(maze[y][x+1] == 0){
				findWay(maze,y,x+1);
			//3,或者向上走
			}else if(maze[y-1][x] == 0){
				findWay(maze,y-1,x);
			//4,或者向左走
			}else if(maze[y][x-1] == 0){
				findWay(maze,y,x-1);
			//5，以上情况都走不通，则设置当前点为3
			}else{
				maze[y][x] = 3;
			}
		//起始点不等于0的情况，
		}else{
			flag = false;
		}
		return flag;
	}


	//遍历迷宫
	private static void scanArray(int[][] maze){
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[i].length; j++){
				System.out.print(maze[i][j] + " ");
			}
			System.out.println();
		}	
	}

	//迷宫四周上墙
	private static int[][] buildWall(int[][] maze){
		for(int i = 0; i < maze.length; i++){
			//上下的两堵墙
			if(i == 0 || i == maze.length - 1){
				for(int j = 0; j < maze[i].length; j++){
					maze[i][j] = 1;
				}
			}
			//左右的两堵墙
			for(int k = 0; k < maze[i].length; k++){
				if(k == 0 || k == maze[i].length -1){
					maze[i][k] = 1;
				}
			}
		}
		return maze;
	}

}