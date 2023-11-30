

//The binary sorted/search tree.
public class BinarySortedTree{

	TreeNode root;

	//To delete a node according to its value(number);
	public boolean deleteNode(int no){
		//First step: find the node if it exists in the tree.
		TreeNode target = getTargetNode(no);

		/*
		 * There are three kinds of the nodes which are going to be deleted.
		 * 1, a leaf node;
		 * 2, a parent node which has only one child;
		 * 3, a parent node which has two children;
		 * */
		if(target != null){
			//To get the parent node of the node to be deleted.
			TreeNode parent = getParentNode(target);			
			//1,To delete a leaf.
			if(isLeaf(target)){
				//Don't forget to verify whether the left or right node is null or not.
				if(parent.left != null && parent.left.no == target.no)
					parent.left = null;
				else if(parent.right != null && parent.right.no == target.no)
					parent.right = null;
			}else if(target.left != null && target.right != null){
				//3, The target has two children
				
			}else{

				//2, The target has only child.

				//2.1 If the only child is on left.
				if(target.left != null){
					//If the target is on left.
					if(parent.left != null && parent.left.no == target.no)
						parent.left = target.left;
					else
						//If the target is on right.
						parent.right = target.left;
				}else{
				//2.2 If the only child is on right
					//If the target is on left.
					if(parent.left != null && parent.left.no == target.no)
						parent.left = target.right;
					else
						//If the target is on right.
						parent.right = target.right;

				}
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

