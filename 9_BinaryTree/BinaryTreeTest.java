
//测试二叉树增删改查
public class BinaryTreeTest {

	
	public static void main(String[] args){
		
		BinaryTreeDemo tree = new BinaryTreeDemo();

		NodeDemo node01 = new NodeDemo(1,"Lily");
		NodeDemo node02 = new NodeDemo(2,"Jack");
		NodeDemo node03 = new NodeDemo(3,"Tom");
		NodeDemo node04 = new NodeDemo(4,"Brady");
		NodeDemo node05 = new NodeDemo(5,"Kate");
		NodeDemo node06 = new NodeDemo(6,"Omar");
		NodeDemo node07 = new NodeDemo(7,"Michael");
		
		//手动添加7个节点。
		tree.add(node01);
		NodeDemo root = tree.getRoot();
		root.setLeft(node02);
		root.setRight(node03);
		node02.setLeft(node04);
		node02.setRight(node05);
		node03.setLeft(node06);
		node03.setRight(node07);
		
		
		//一，二叉树的遍历的三种方式。

		//1,前序序遍历（根左右）,使用BinaryTreeDemo.java中的方法
		tree.preList(tree.getRoot());
		//1.2 前序遍历，使用节点内部的遍历方法
		System.out.println("======分割线======");
		tree.getRoot().preListNode();
		//2,中序遍历(左根右)
		System.out.println("======中序遍历======");
		tree.infixList(tree.getRoot());
		//3,后序遍历
		System.out.println("======后序遍历======");
		tree.suffixList(tree.getRoot());

	}

}