
import java.util.Scanner;

/*
* 哈希表数据结构:
* 1,哈希表实际是由链表组成的数组。
* 2,增删改查元素的时候根据散列函数算法确定把元素加到哪个下标下的链表里。
* 3,JDK集合中的Hashtable.java就是一个哈希表，只是设计的功能更复杂，但底层是一样的。
*/

public class HashTableTest {

	public static void main(String[] args){
		
		//单链表测试
		//SingleLinkedList list = new SingleLinkedList();
		//listDo(list);

		//哈希表
		HashTableDemo hashTable = new HashTableDemo(10);
		tableDo(hashTable);
		
	}
	
	
	//哈希表测试，增删改查完成，改是在增的功能里，遇到相同id的直接覆盖旧节点。
	public static void tableDo(HashTableDemo hashTable){
		while(true){
			Scanner scan = new Scanner(System.in);
			System.out.println("添加：add");
			System.out.println("展示：list");
			System.out.println("查找：find");
			System.out.println("删除：delete");
			System.out.println("退出：exit");
			String order = scan.next();
			switch (order){
			case "add":
				System.out.print("请输入id:");
				int id = scan.nextInt();
				System.out.print("请输入name:");
				String name = scan.next();
				//hashTable.add(new UserNode(id,name));  //新节点添加到链表最后
				hashTable.addByOrder(new UserNode(id,name));  //有序添加
				break; 
			case "list":
				hashTable.showTable();
				break;
			case "find":
				System.out.print("请输入要查找的id:");
				id = scan.nextInt();
				UserNode user = hashTable.findById(id);
				System.out.println("查找结果==>" + user);
				break;
			case "delete":
				System.out.print("请输入要删除的id:");
				id = scan.nextInt();
				hashTable.deleteById(id);
				break;
			case "exit":
				scan.close();  //io流一定要关闭
				System.exit(0);
				break;
			default:
				System.out.println("请输入正确的代号!!");
				break;
			}
		}
	}


	
	
	//单链表测试
	public static void listDo(SingleLinkedList list){
		
		while(true){
			Scanner scan = new Scanner(System.in);
			System.out.println("添加：add");
			System.out.println("展示：list");
			System.out.println("退出：exit");
			String order = scan.next();
			switch (order){
			case "add":
				System.out.print("请输入id:");
				int id = scan.nextInt();
				System.out.print("请输入name:");
				String name = scan.next();
				list.addByOrder(new UserNode(id,name));
				break;
			case "list":
				list.show();
				break;
			case "exit":
				scan.close();  //io流一定要关闭
				System.exit(0);
			default:
				break;
			}
		
		}
	
	}


}