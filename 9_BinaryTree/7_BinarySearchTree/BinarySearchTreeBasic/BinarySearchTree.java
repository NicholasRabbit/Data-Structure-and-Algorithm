

public class BinarySearchTree{

	//根节点
	TreeNode root;

	//添加节点
	public void addNode(TreeNode node){
		//入参校验
		if(node == null)
			return;
		
		//先添加根节点
		if(this.root == null)
			this.root = node;
		else 
			root.add(node);
	
	}

	//中序遍历二叉排序树
	public void infixShow(TreeNode root){
		if(root == null)
			return;
		root.infixShow();	
	}
	
}
