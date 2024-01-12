

public class TreeNode {

	int no;  // no = value
	TreeNode left;
	TreeNode right;

	public TreeNode(){
	
	}

	public TreeNode(int no){
		this.no = no;
	}

	/*
	 * 本方法针对一个父节点进行操作，获取其下子树的最大高度。对于任何一个二叉树中的节点都是通用的。
	 * 递归获取叶子节点的高度。
	 * 递归推导分析：
	 * 从最底部的调用叶子节点开始推导，先模拟叶子节点的方法是如何执行的，执行完弹栈，依次向上返回值。
	 * */
	public int getHeight(){
		//老师的写法比较简洁，但不容易理解，个人按照相同的思路把代码写的详细些，方便理解。
		int maxLeft = 0;
		int maxRight = 0;
		if(this.left != null){
			maxLeft = this.left.getHeight();
		}

		if(this.right != null){
			maxRight = this.right.getHeight();
		}
		
		//返回最大值
		int height = maxLeft > maxRight ? maxLeft : maxRight;
		return height + 1;  //注意这里返回时要 +1,否则递归弹栈的是否无法累加高度，导致最后结果得到0

		//The concise code written by teacher.
		//return Math.max(left == null ? 0 : left.getHeight(), right == null ? 0 : right.getHeight()) + 1;
		
	}


	/*
	 * 获取左子树的高度
	 * */
	public int leftHeight(){
		if(this.left == null)
			return 0;
		else
			return this.left.getHeight();
	}

	/*
	 * 获取右子树的高度
	 * */
	public int rightHeight(){
		if(this.right == null)
			return 0;
		else
			return this.right.getHeight();
	}

	//To get parent node.
	public TreeNode getParent(TreeNode child){
		if(this.no > child.no){
			if(this.left != null && this.left.no == child.no){
				return this;
			} else {
				return this.left.getParent(child);
			}
		}else{
			//this.no <= child.no
			if(this.right != null && this.right.no == child.no){
				return this;
			}else{
				return this.right.getParent(child);
			}
		}

	}


	//To find the node.
	public TreeNode get(int no){
		if(this.no == no)
			return this;
		//do the recursion of seraching
		if(no < this.no && this.left != null){
			return this.left.get(no);
		}else if(no > this.no){
			return this.right.get(no);
		}

		return null;
	}

	//add node
	public boolean add(TreeNode node){
		//to check the argument
		if(node == null)
			return false;

		//to compare the values
		if(node.no < this.no){
			if(this.left == null)
				this.left = node;
			else
				this.left.add(node);
		}else if(node.no >= this.no){
			if(this.right == null)
				this.right = node;
			else
				this.right.add(node);
		}
		
		return true;

	}

	//add node and rotate if the tree is not balanced.
	public boolean addAndRotate(TreeNode node){
	
		return true;
	}


	//in-order tree traversal
	public void infixList(){
		
		//show node recursively
		if(this.left != null)
			this.left.infixList();
		//print th parent node
		System.out.println(this);

		//right
		if(this.right != null)
			this.right.infixList();


	}


	public String toString(){
		return this.no + ""; 
	}

}
