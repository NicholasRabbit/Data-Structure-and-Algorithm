

//The binary sorted/search tree.
public class BinarySortedTree{

	TreeNode root;

	//To delete a node according to its value(number);
	public boolean deleteNode(int no){
		//First step: find the node if it exists in the tree.
		TreeNode target = getTargetNode(no);
		if(target != null){
			//Leaf
			if(isLeaf(target)){
				TreeNode parent = getParentNode(target);			
				//Don't forgent to verify whether the left or right node is null or not.
				if(parent.left != null && parent.left.no == target.no)
					parent.left = null;
				else if(parent.right != null && parent.right.no == target.no)
					parent.right = null;
			}
		}



		return false;
	}


	public boolean addNode(TreeNode node){
		if(node == null)
			return false;
		//Thre root must be added at the first time.
		if(root == null){
			root = node;
			return true;
		}
		//Add others nodes.
		return	root.add(node);
	}

	//Traverse
	public void infixList(){
		if(root == null)
			return;
		root.infixList();
	}

	
	//To find the node to delete
	public TreeNode getTargetNode(int no){
		if(root == null)
			return null;
		return root.get(no);
	}

	//To find the parent node
	public TreeNode getParentNode(TreeNode child){
		if(child == null)
			throw new InvalidArgumentException("invalid node:null");
		//The root node hasn't any parent.
		if(root.no == child.no){
			return null;
		}	

		return root.getParent(child);

	}

	//To check if the node is a leaf.
	public boolean isLeaf(TreeNode node){
		if(node == null)
			throw new InvalidArgumentException("invalid node:null");
		if(node.left == null && node.right == null)
			return true;
		else
			return false;
	}

}

//Throw an exception when node is null.
class InvalidArgumentException extends RuntimeException{
	public InvalidArgumentException(String s){
		super(s);
	}
}
