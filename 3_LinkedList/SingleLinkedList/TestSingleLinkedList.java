
import java.util.Stack;

/*
1,������������ʵ�ֻ�������ӹ���
2,�趨�����һ���������ݣ�ֻ��һ��next����ָ����һ���ڵ㣬����ڵ�ĵ�ַ���ǵ�������ĵ�ַ
*/

public class TestSingleLinkedList {

	public static void main(String[] args){
		SingleLinkedList list = new SingleLinkedList();
		
		//0,��ȡͷ�ڵ�
		list.getHead();  //һ������������Ȼ�ȡͷ�ڵ㣬ͷ�ڵ���ڴ��ַ������ĵ�ַ

		//1,add()������ӽڵ㣬������
		list.add(new NodeDemo(1001,"Tom"));
		list.add(new NodeDemo(1002,"Jerry"));
		list.add(new NodeDemo(1004,"Liam"));
		list.add(new NodeDemo(1003,"Liam"));
		list.show();
		
		System.out.println("�ָ���=============");
		//2,addByOrder(..)��ӽڵ㣬����������
		SingleLinkedList listOrder = new SingleLinkedList();
		listOrder.addByOrder(new NodeDemo(2001,"Lily"));
		listOrder.addByOrder(new NodeDemo(2004,"Daniel"));
		listOrder.addByOrder(new NodeDemo(2002,"Bill"));
		listOrder.addByOrder(new NodeDemo(2003,"Joseph"));
		listOrder.show();   //3,������������

	
		
		System.out.println("�ָ���=============�޸Ľڵ�");
		//4,updateNode(..)�޸Ľڵ㣬ָ�޸Ľڵ����ݣ������޸Ľڵ���ڴ��ַ
		NodeDemo node01 = new NodeDemo(2004,"MacJones");
		listOrder.updateNode(node01);
		listOrder.show();

		//5,deleteNode(..) ɾ���ڵ�
		System.out.println("�ָ���=============ɾ���ڵ�");
		listOrder.deleteNode(2001);
		listOrder.deleteNode(2004);
		listOrder.show();

		//6,��������Ч�ڵ�ĸ�����������ͷ�ڵ�
		System.out.println("�ָ���=============�ڵ����");
		listOrder.addByOrder(new NodeDemo(2005,"Nash"));
		listOrder.addByOrder(new NodeDemo(2006,"Brady"));
		listOrder.addByOrder(new NodeDemo(2007,"Zoey"));
		NodeDemo head = listOrder.getHead();
		int length = SingleLinkedList.getLength(head);   //һ���������ʱ���ҵ�ͷ�ڵ�
		System.out.println("����������==>" + length);

		//7,��ȡ������index���ڵ�
		System.out.println("�ָ���=============������index���ڵ�");
		listOrder.show();  //�ȱ����½ڵ�
		head =listOrder.getHead();
		NodeDemo indexNode = listOrder.getBackwardIndex(head,2);
		System.out.println("�����ڶ����ڵ�==>" + indexNode);

		//8,��ת��������
		System.out.println("�ָ���=============��ת����������תǰ��");
		listOrder.show();
		System.out.println("�ָ���=============��ת����������ת��");
		SingleLinkedList.reverseLinkedList(listOrder.getHead());
		listOrder.show();

		//9,�����ӡ����
		SingleLinkedList orderList = new SingleLinkedList();  //�½�һ������
		orderList.addByOrder(new NodeDemo(5,"Lily"));
		orderList.addByOrder(new NodeDemo(3,"Otmar"));
		orderList.addByOrder(new NodeDemo(2,"Thomas"));
		orderList.addByOrder(new NodeDemo(4,"Nancy"));
		orderList.addByOrder(new NodeDemo(1,"Matt"));
		System.out.println("�ָ���=============���������ӡ��");
		orderList.show();
		System.out.println("�ָ���=============���������ӡ��");
		Stack stack = orderList.printReverseList(orderList.getHead());  //��������
		
		while(stack.size() > 0){
			System.out.println("�����ӡ��node==>" + stack.pop());
		}


		//10,�ϲ�������������ʹ֮�ϲ�����Ȼ����
		//˼·�������ڷ�ת��������һ���µĵ��������½�һ����ͷ��Ȼ���������������ݱȽϺ���ӵ���������
		



	}
}

