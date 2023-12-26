

public class AVLTree{

	//根节点
	TreeNode root;

	//添加节点
	public void add(TreeNode node){
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
	public void infixList(TreeNode root){
		if(root == null)
			return;
		root.infixList();	
	}

	//根据父节点获取树的高度
	public int getHeight(TreeNode root){
		return root.getHeight();
	}

	//获取左子树的高度
	public int leftHeight(TreeNode root){
		return root.leftHeight();
	}

	//获取右子树的高度
	public int rightHeight(TreeNode root){
		return root.rightHeight();
	}

	//旋转二叉树
	public boolean rotate(){
		//1，计算左右子树高度差，看是否>1
		int subtraction = leftHeight(root) - rightHeight(root);
		while(subtraction > 1 || subtraction < -1){
			rotateToLeft();
		}

		return true;
	}
	
	//左旋转，具体思路分析见本章节笔记
	//这里只是完成一次调整。
	public boolean rotateToLeft(){
		//1, 创建临时节点，备份跟节点。
		TreeNode temp = root;
		//2, 临时节点的左节点指向根节点的左节点，右节点指向根节点的左子节点的右节点。
		temp.left = root.left;
		temp.right = root.right.left;
		//3, 把根节点的右子节点设置位根节点
		root = root.right;
		//4, 新的根节点左边指向临时节点
		root.left = temp;

		return true;
	
	}
	
}
