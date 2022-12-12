
/*
* 改进走不通的路设置成3
*/
public class RecursionTest02 {

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
		//findWay(maze,1,1);
		//System.out.println("======走完迷宫后遍历======");
		//scanArray(maze);

		//测试走不通的情况，注意这里测试要把开始回溯那里注释。
		maze[1][2] = 1;  //把路堵死
		maze[2][2] = 1;
		findWay(maze,1,1);
		System.out.println("======走不通后遍历======");
		scanArray(maze);

	
	}
	
	/*
	* 个人初步代码RecursionTest中实现了迷宫，但如果走不到指定的终点就不会反馈结果，即没有把走不通的路设置成“3”
	* 没有利用到递归的LiFo的特性，即没有利用回溯。
	* 在此改进。
	*/
	public static boolean findWay(int[][] maze, int y, int x){  //注意这里坐标y在x之前。
		boolean flag = false;
		//走到终点就停止递归。
		if(maze[7][6] == 2){
			flag = true;
		}
		//下面进行:下->右->上->左的判断
		//maze[y][x] == 0表示这个点还没有走过
		if(maze[y][x] == 0){
			//赋值2，代表已走。
			maze[y][x] = 2;
			
			//1,向下移动，把递归方法当成判断条件，因为返回值是boolean类型
			if(findWay(maze,y+1,x)){
				flag = true; 
			
			//2,如果上面的走不通，则向右走
			}else if(findWay(maze,y,x+1)){
				flag = true;;	
			
			//3,或者向上走
			}else if(findWay(maze,y-1,x)){
				flag = true;;
			
			//4,或者向左走
			}else if(findWay(maze,y,x-1)){
				return true;
			//5，以上情况都走不通，则设置当前点为3
			}else{
				maze[y][x] = 3;
				flag = false;;
			}
		//起始点不等于0的情况，
		}else{
			flag = false;
		}
		return flag;
	}


	//遍历二维数组迷宫
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