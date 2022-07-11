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
	public void showList(NodeDemo head){
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
	二，添加节点，默认不按id大小添加，添加到链表末尾
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