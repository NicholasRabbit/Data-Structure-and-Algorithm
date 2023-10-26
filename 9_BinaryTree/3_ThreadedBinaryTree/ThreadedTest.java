
/*
* 线索化二叉树：
* 1,像用一条线一样把二叉树的各个节点连接起来，遍历的时候就沿着这个线
* 去遍历，而不是用原来的二叉树递归方法。
* 这种方式遍历速度快。
* 2,线索化后，就不能用原来的递归方式遍历了，会报错。
* 3,实际线索化二叉树就是利用二叉树节点下标间的关系，用数组的形式把二叉树表示出来。后续调整大小顶堆，堆排序都是在此基础上进行的。
* 
* 步骤，以中序遍历为例：
* 1,中序为“左根右”，因此把左节点的right属性设置为其父节点，右节点的left
* 设置为其父节点，依次类推；
* 2,给每个已经线索化的节点加个增加标记。
* 
*/
public class ThreadedTest {

	public static void main(String[] args){
		
		ThreadedBinaryTree tree = new ThreadedBinaryTree();
		
		//手动添加节点
		NodeDemo node01 = new NodeDemo(1,"Lily");
		NodeDemo node02 = new NodeDemo(2,"Jack");
		NodeDemo node03 = new NodeDemo(3,"Tom");
		NodeDemo node04 = new NodeDemo(4,"Brady");
		NodeDemo node05 = new NodeDemo(5,"Kate");
		NodeDemo node06 = new NodeDemo(6,"Omar");
		NodeDemo node07 = new NodeDemo(7,"Michael");
		
		//手动添加7个节点。
		tree.setRoot(node01);
		NodeDemo root = tree.getRoot();
		root.setLeft(node02);
		root.setRight(node03);
		node02.setLeft(node04);
		node02.setRight(node05);
		node03.setLeft(node06);
		node03.setRight(node07);
		
		//线索化之前用原递归的方式中序遍历
		tree.inffixList();

		//线索化二叉树，注意线索化之后就不能用上面递归遍历的方式了，会递归无穷尽。
		tree.threaded(root);

		//打印
		System.out.println("========");
		System.out.println("left==> " + node04.getLeft() + "\t\n right==>" + node04.getRight());

	}

}
