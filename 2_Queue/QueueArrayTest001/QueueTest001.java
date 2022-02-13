import java.util.Scanner;

//测试一般数组模拟的队列
//出现的问题：把元素添加满后，再全部取出后，队列已空了，但是再实行添加操作的话显示“队列已满”
public class QueueTest001 {
	
	public static void main(String[] args){
		
		ArrayQueue  queue = new ArrayQueue(5); //初始化数组的容量

		char key = ' ';   //创建一个空字符，接收用户客户端的输入
		Scanner scan = new Scanner(System.in);
		boolean loop = true;  
		while(loop){
			System.out.println("s(show):展示队列");
			System.out.println("a(add):添加元素");
			System.out.println("g(get):取出头部元素");
			System.out.println("h(head):查看队列头部的元素");
			key = scan.next().charAt(0);  //获取用户输入的指令
			switch (key){
			case 's' :
				queue.showAll();	
				break;
			case 'a' :
				System.out.println("请添加一个数字：");
				int element = scan.nextInt();
				queue.add(element);
				break;
			case 'g' :   //取出头部元素后，head箭头就后移一位，这时虽然数组中原来的头部元素还在，但是已经不再指向了，被“遗忘”了
				int value = queue.get();
				System.out.println("取出头部元素==>" + value);
				break;
			case 'h' :
				System.out.println("head value==>" + queue.headValue());
				break;
			}
		}
		
	}

	
}