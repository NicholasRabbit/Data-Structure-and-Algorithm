public class DoubleLikedListTest {

	public static void main(String[] args){
		DoubleLinkedList list = new DoubleLinkedList();
		list.showList(list.getHead());
		//一，添加节点
		list.add(new NodeDemo(1001,"Tom"));
		list.add(new NodeDemo(1003,"Jerry"));
		list.showList(list.getHead());

	}


	

	
}