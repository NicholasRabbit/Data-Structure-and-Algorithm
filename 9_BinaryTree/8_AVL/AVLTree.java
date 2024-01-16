
/*
 * AVL Tree
 * */
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

	//自动左旋旋转二叉树
	public boolean rotateToLeftAuto(){
		//1，计算左右子树高度差，看是否>1
		int subtraction = leftHeight(root) - rightHeight(root);
		while(subtraction > 1 || subtraction < -1){
			rotateToLeft();
			subtraction = leftHeight(root) - rightHeight(root);
		}

		return true;
	}
	
	//左旋转，具体思路分析见本章节笔记
	//这里只是完成一次调整。
	public boolean rotateToLeft(){
		//1, 创建临时节点，备份根节点。
		//注意：这里不要直接写成 temp = root, 这种使用引用赋值的形式导致后面temp的左右节点改变后，root的节点也改变，因为它们俩指向的是同一个对象。
		TreeNode temp = new TreeNode();
		temp.no = root.no;  
		//2, 临时节点的左节点指向根节点的左节点，右节点指向根节点的左子节点的右节点。
		temp.left = root.left;
		temp.right = root.right.left;
		//3, 把根节点的右子节点设置位根节点
		root = root.right;
		//4, 新的根节点左边指向临时节点
		root.left = temp;

		return true;
	
	}


	//每次添加完节点后，进行判断和左旋
	public boolean rotateLeftWhenAdd(TreeNode node){
		//1,添加节点
		add(node);
		//3,进行比较
		int l = leftHeight(root);
		int r = rightHeight(root);
		if(l - r > 1 || l - r < -1){
			rotateToLeft();
		}
		return true;
	}


	//右旋转，和左旋同理
	public boolean rotateToRight(){
		//1, 创建临时节点，本分根节点的值
		TreeNode temp = new TreeNode();
		temp.no = root.no;
		//2, temp右边指向原根节点的右节点,temp左边指向原根节点的左节点的右子节点
		temp.right = root.right;
		temp.left = root.left.right;
		//3, 原根节点的左子节点作为新的根节点，
		root = root.left;
		//4, 新根节点右边指向temp
		root.right = temp;

		return true;
	
	}
	
}
