
/*
1,单向链表范例，实现基本的添加功能
2,设定链表第一个不存数据，只有一个next属性指向下一个节点，这个节点的地址就是单向链表的地址
*/

public class TestSingleLinkedList {

	public static void main(String[] args){
		SingleLinkedList list = new SingleLinkedList();
		//1,add()方法添加节点，不排序
		list.add(new NodeDemo(1001,"Tom"));
		list.add(new NodeDemo(1002,"Jerry"));
		list.add(new NodeDemo(1004,"Liam"));
		list.add(new NodeDemo(1003,"Liam"));
		list.show();
		
		System.out.println("分割线=============");
		//2,addByOrder(..)添加节点，并进行排序
		SingleLinkedList listOrder = new SingleLinkedList();
		listOrder.addByOrder(new NodeDemo(2001,"Lily"));
		listOrder.addByOrder(new NodeDemo(2004,"Daniel"));
		listOrder.addByOrder(new NodeDemo(2002,"Bill"));
		listOrder.addByOrder(new NodeDemo(2003,"Joseph"));
		listOrder.show();   //3,遍历单向链表

	
		
		System.out.println("分割线=============修改节点");
		//4,updateNode(..)修改节点，指修改节点内容，不是修改节点的内存地址
		NodeDemo node01 = new NodeDemo(2004,"MacJones");
		listOrder.updateNode(node01);
		listOrder.show();

		//5,deleteNode(..) 删除节点
		System.out.println("分割线=============删除节点");
		listOrder.deleteNode(2001);
		listOrder.deleteNode(2004);
		listOrder.show();

		//6,链表中有效节点的个数，不包括头节点
		System.out.println("分割线=============节点个数");
		listOrder.addByOrder(new NodeDemo(2005,"Nash"));
		listOrder.addByOrder(new NodeDemo(2006,"Brady"));
		listOrder.addByOrder(new NodeDemo(2007,"Zoey"));
		NodeDemo head = listOrder.getHead();
		int length = SingleLinkedList.getLength(head);   //一般操作链表时先找到头节点
		System.out.println("单向链表长度==>" + length);

		//7,获取倒数第index个节点
		System.out.println("分割线=============倒数第index个节点");
		listOrder.show();  //先遍历下节点
		head =listOrder.getHead();
		NodeDemo indexNode = listOrder.getBackwardIndex(head,2);
		System.out.println("倒数第二个节点==>" + indexNode);

	}
}

