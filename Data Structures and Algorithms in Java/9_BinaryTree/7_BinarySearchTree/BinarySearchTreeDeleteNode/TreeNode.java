

public class TreeNode {

	int no;  // no = value
	TreeNode left;
	TreeNode right;

	public TreeNode(){
	
	}

	public TreeNode(int no){
		this.no = no;
	}

	//To get parent
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

	//add node in a ascend order
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


	//infix traverse
	public void infixList(){
		
		//show node in a recursion way.
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
