public class DoubleLikedListTest {

	public static void main(String[] args){
		DoubleLinkedList list = new DoubleLinkedList();
		//一，遍历节点
		list.showList();
		
		//二，(1)添加节点，添加到末尾，不比较id大小
		list.add(new NodeDemo(1001,"Tom"));
		list.add(new NodeDemo(1003,"Jerry"));
		list.add(new NodeDemo(1005,"Lambo"));
		System.out.println("添加到末尾，遍历节点=======");
		list.showList(); 
		//(2)根据id有序添加节点
		NodeDemo nodeDemo = new NodeDemo(1002,"Ben");
		list.addByOrder(nodeDemo);
		list.addByOrder(new NodeDemo(1004,"Gibrelle"));  //测试刚好最后一个节点比新节点id大的情况
		list.addByOrder(new NodeDemo(1006,"Hans"));
		list.addByOrder(new NodeDemo(1004,"Dan"));		//测试相同id，覆盖name参数
		System.out.println("有序添加节点，遍历=======");
		list.showList();

		//三，删除节点
		list.deleteNode(1006);
		System.out.println("删除1002节点后遍历=====");
		list.showList();
		list.deleteNode(1006);

		//四，修改节点
		NodeDemo nodeDemo2 = new NodeDemo(1002,"Rothlisburg");
		list.updateNode(nodeDemo2);
		System.out.println("修改节点后=========");
		list.showList();



	}


	

	
}