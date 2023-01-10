
//二叉树增删改查范例
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
		System.out.println("======前序遍历1======");
		tree.preList(tree.getRoot());     //前序：1,2,4,5,3,6,7
		//1.2 前序遍历，使用节点内部的遍历方法
		System.out.println("======前序遍历2======");
		tree.getRoot().preListNode();   
		//2,中序遍历(左根右)
		System.out.println("======中序遍历======");
		tree.infixList(tree.getRoot());  //中序：4,2,5,1,6,3,7
		//3,后序遍历
		System.out.println("======后序遍历======");
		tree.suffixList(tree.getRoot());  //后序：4,5,2,6,7,3,1

		//二，根据id查找节点的三种方式
		//1,前序遍历查找。
		NodeDemo nodeToFind = tree.preSearchTree(6);
		System.out.println("前序遍历查找结果：" + nodeToFind);
		//2,中序遍历查找
		nodeToFind = tree.infixSearchTree(6);
		System.out.println("中序遍历查找结果：" + nodeToFind);
		//3,后序遍历查找
		nodeToFind = tree.suffixSearchTree(6);
		System.out.println("后序遍历查找结果：" + nodeToFind);
			
		//4,三种遍历方式查找比较次数统计，查找的节点相同时，以目前范例来看后序查找似乎是最优的，待后期验证。
		
		
		/*
		* 三，删除节点。（采用直接删除子树的方式，具体见NodeDemo.java内注释）
		*/
		int result = tree.deleteNodeTree(2);
		System.out.println("result of deleting==>" + result);
		//删除后遍历
		tree.preList(tree.getRoot());

	}

}