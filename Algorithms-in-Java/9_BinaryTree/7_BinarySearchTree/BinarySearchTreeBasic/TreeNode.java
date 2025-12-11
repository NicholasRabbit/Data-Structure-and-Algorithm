public class TreeNode {

	protected int no;
	protected TreeNode left;
	protected TreeNode right;

	public TreeNode(int no){
		this.no = no;
	}

	//二叉搜索树添加节点：左边小，右边大
	public void add(TreeNode node){
		if(node == null)
			return;
		//新增节点比父节点小
		if(node.no < this.no){
			if(this.left == null){
				//左子节点是空就直接添加。
				this.left = node;
			}else{
				//左子节点不为空，则继续递归向左遍历
				this.left.add(node);
			}
		}else{
			//新增节点大于或等于父节点
			if(this.right == null){
				this.right = node;
			}else{
				this.right.add(node);
			}
		}
	
	}


	//采用中序遍历的方式遍历二叉搜索树，这样展示的结果是从小到大的。
	public void infixShow(){

		//递归左节点打印
		if(this.left != null){
			this.left.infixShow();
		}
		//打印中间节点
		System.out.println(this + " ");
		//递归打印右节点
		if(this.right != null){
			this.right.infixShow();
		}

	}


	public String toString(){
		return this.no + "";
	}

}
