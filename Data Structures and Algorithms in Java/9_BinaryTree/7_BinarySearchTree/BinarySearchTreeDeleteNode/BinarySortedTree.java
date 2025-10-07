

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
				//To get node with minimum value on the right tree.
				TreeNode minNodeOfRight = getMinNode(target.right);

				/* My code is right but it is not so simple.
				//To get parent of the minimum node.
				TreeNode parentOfMin = getParentNode(minNodeOfRight);
				//To replace the target.
				minNodeOfRight.left = target.left;
				minNodeOfRight.right = target.right;
				//3.1 The target is a left node.
				if(parent.left != null && parent.left.no == target.no){
					parent.left = minNodeOfRight;
				}else{
					//3.2 The target is a right node.
					parent.right = minNodeOfRight;
				}

				//3.3 The minimum node of the right tree must be a left one.Then delete it.
				parentOfMin.left = null;

				*/

				//!! The teacher's code. He has remove the minimum node on the right tree, meanwhile, and assign its value to the target.
				int val = deleteMinimum(minNodeOfRight);
				target.no = val;

				
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
			throw new InvalidArgumentException("Invalid node: null");
		//The root node hasn't any parent.
		if(root.no == child.no){
			return null;
		}	

		return root.getParent(child);

	}

	//To check if the node is a leaf.
	public boolean isLeaf(TreeNode node){
		if(node == null)
			throw new InvalidArgumentException("Invalid node: null");
		if(node.left == null && node.right == null)
			return true;
		else
			return false;
	}

	//To get the node with minimun value in a BST.
	public TreeNode getMinNode(TreeNode node){
		if(node == null)
			throw new InvalidArgumentException("Invalid node: null");
		
		if(node.left == null){
			return node;
		}else{
			return getMinNode(node.left);
		}
	
	}

	//To get the node with maximum value in a BST.
	public TreeNode getMaxNode(TreeNode node){
		if(node == null)
			throw new InvalidArgumentException("Invalid node: null");
		if(node.right == null){
			return node;
		}else{
			return getMaxNode(node.right);
		}
		
	}

	//To delete the node with minimum value and to return its value.
	public int deleteMinimum(TreeNode minNode){
		int val = minNode.no;
		deleteNode(minNode.no);
		return val;
	}

}

