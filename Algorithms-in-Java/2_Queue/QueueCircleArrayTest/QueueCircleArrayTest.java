import java.util.Scanner;

public class QueueCircleArrayTest {

	public static void main(String[] args){
		CircleArrayQueue  queue = new CircleArrayQueue(5);

		char key = ' ';   //创建一个空字符，接收用户客户端的输入
		Scanner scan = new Scanner(System.in);
		boolean loop = true;  
		while(loop){
			System.out.println("==============");
			System.out.println("s(show):展示队列");
			System.out.println("a(add):添加元素");
			System.out.println("g(get):取出头部元素");
			System.out.println("h(head):查看队列头部的元素");
			System.out.println("z(size):查看队列内元素数量");
			System.out.println("==============");
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
				System.out.println("head value==>" + queue.getHead());
				break;
			case 'z' :
				System.out.println("queue size==>" + queue.getSize());
			}
		}
		
	}


	
}