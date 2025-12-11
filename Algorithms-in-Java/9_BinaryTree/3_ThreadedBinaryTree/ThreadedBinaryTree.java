
/*
* 线索化二叉树范例
*/
public class ThreadedBinaryTree {
	
	private NodeDemo root;
	
	//辅助指针，指向当前节点的前驱节点
	private NodeDemo pre;

	//setter , getter
	public void setRoot(NodeDemo root){
		this.root = root;
	}
	public NodeDemo getRoot(){
		return this.root;
	}

	
	/*
	* 中序线索化二叉树，采用递归的方式，复习的时候写个实例模拟一遍，加深理解。
	*/
	public void threaded(NodeDemo node){
		
		//节点校验，同时也判断树最后的叶子节点的左右节点是否为空，为空就结束递归并弹栈
		if(node == null){
			return;
		}

		//线索化左子节点
		threaded(node.getLeft());

		/*
		* 2，重点：线索化当前节点；
		* 根据递归的特性，实际线索化所有节点都是在这里完成的。
		*/
		//设置前驱节点
		if(node.getLeft() == null){
			node.setLeft(pre);
			node.setLeftPointer(1);  //打上设置前驱节点线索化标记
		}
		
		//设置后继节点
		if(pre != null && pre.getRight() == null){
			pre.setRight(node);
			pre.setRightPointer(1);  //打上后继节点标记
		}

		/*
		* !!!关键步骤，把前驱指针pre设置为当前节点，这样下一次递归的时候的当前节点
		* 的left可以设置为pre
		*/
		pre = node;


		//3,线索化右节点
		threaded(node.getRight());
		

	}

	//测试用，线索化之前用原递归的方式中序遍历
	public void inffixList(){
		root.inffixList();
	}
	
	

}