/*
双向链表类
*/
public class DoubleLinkedList {
	//初始化头节点
	private NodeDemo head = new NodeDemo(0,"");

	
	//获取头节点
	public NodeDemo getHead(){
		return this.head;
	}
	
	/*
	一，遍历双向链表，与遍历单向链表做法一样
	*/
	public void showList(){
		//首先，判断链表是否为空
		if(head.next == null){
			System.out.println("链表为空！");
			return;
		}
		//进行遍历，首先获取第一个节点
		NodeDemo temp = head.next;
		do {
			System.out.println("NodeDemo==>" + temp);
			temp = temp.next;  //打印一个后引用后移
		}while(temp != null);
	
	}

	/*
	二，(1)添加节点，默认不按id大小添加，添加到链表末尾
	*/
	public int add(NodeDemo newNode){
		//使用临时节点，不要动head，head是找到链表的标识
		NodeDemo temp = head;  //这里head就是this.head
		int count = 0;
		while(true){
			if(temp.next == null){
				temp.next = newNode;
				newNode.pre = temp;
				count ++;
				break;
			}
			temp = temp.next;  //遍历时，不符合条件的一定要后移，切记
		}
		return count;
	}

	//(2)有序添加节点，按id从下到大排序
	public int addByOrder(NodeDemo newNode){
		
		int count = 0;
		NodeDemo temp = head;
		boolean flag = false;

		//第一步，检查链表是否为空，为空则直接添加
		if(temp.next == null){
			temp.next = newNode;
			newNode.pre = temp;
			return count++;
		}
		//第二步：链表不为空，比较id进行添加
		while(temp.next != null){
			//找到第一个比新节点id大的节点，设置flag为true。
			if(temp.id > newNode.id){
				flag = true;
				break;
			}
			temp = temp.next;
		}
		//插入新节点
		if(flag){
			newNode.pre = temp.pre;
			newNode.next = temp;
			count++;
		}else{
			//如果链表内节点id都比新节点小，则添加到末尾
			temp.next = newNode;
			newNode.pre = temp;
			count++;
		}
		return count;

	}


}


//节点
class NodeDemo {

	int id;
	String name;
	NodeDemo pre;
	NodeDemo next;

	public NodeDemo(){
	
	}
	public NodeDemo(int id,String name){
		this.id = id;
		this.name = name;
	}

	public String toString(){
		return "NodeDemo==>" + "{id:" + this.id + "," + "name:" + this.name + "}";
	}


}