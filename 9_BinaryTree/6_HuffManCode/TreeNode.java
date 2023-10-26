public class TreeNode{
	//暂时不用private，方便调用。
	byte ascCode;
	int weight;
	TreeNode left;   //左子节点
	TreeNode right;  //右子节点

	public TreeNode(){
	
	}
	
	public TreeNode(byte ascCode,int weight){
		this.ascCode = ascCode;
		this.weight = weight;
	}

	//前序遍历。根，左，右。
	public void preList(){
		//打印根节点
		System.out.println(this);
		//左节点
		if(this.left != null){
			this.left.preList();
		}
		//右节点
		if(this.right != null){
			this.right.preList();
		}

	}

	public String toString(){
		return "{ascCode:" + this.ascCode + ",weight:" + weight + "}";
	}


}
