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
		list.addByOrder(new NodeDemo(1002,"Ben"));
		list.addByOrder(new NodeDemo(1006,"Hans"));
		list.addByOrder(new NodeDemo(1004,"Gibrelle"));
		list.addByOrder(new NodeDemo(1004,"Dan"));
		System.out.println("有序添加节点，遍历=======");
		list.showList();



	}


	

	
}