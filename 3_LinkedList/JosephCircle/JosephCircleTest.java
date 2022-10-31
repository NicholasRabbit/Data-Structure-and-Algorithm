
/*
* 约瑟夫环：
* 1，实际是一个环形的单向链表，以这个环来实现像丢手绢一样的操作；
*/
public class JosephCircleTest{

	public static void main(String[] args){
		//(1)简单测试
		JosephCircle jc = new JosephCircle();
		jc.create(5);
		jc.showList(jc);
		System.out.println("============分隔符===============");
		//(2)进行出圈操作
		JosephCircle jc2 = new JosephCircle();
		jc2.create(100);
		jc2.showList(jc2);
		jc2.pop(1,5,100);
		
	}
}






	

