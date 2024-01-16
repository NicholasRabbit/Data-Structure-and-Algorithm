
/*
 * 测试向右旋转
 * 思路跟左旋同理
 * 这里也是人为设置了一种简单的情况，仅为了初步熟悉旋转的思路，
 * 从而引出问题，为后面的双旋转做准备。
 * */
public class TestRotateRight{

	public static void main(String[] args){
		
		//依次添加数组中的元素到AVL Tree，得到左子树高于右子树
		int[] arr = {20,15,13,10,12,9,8,7,6,22};


		AVLTree tree = new AVLTree();
		for(int i = 0; i < arr.length; i++){
			tree.add(new TreeNode(arr[i]));   //使用下面的方法就注释本行
		}

		//中序遍历
		tree.infixList(tree.root);
		//树的高度
		height(tree);

		//单次右旋转
		tree.rotateToRight();
		height(tree);
		
		//自动旋转
		while(tree.leftHeight() - tree.rightHeight() > 1){
			tree.rotateToRight();
		}
		height(tree);
	}


	public static void height(AVLTree tree){
		/*
		 * 1, 获取树的总高度
		 * 问题：这里获取高度比实际高度大1，老师代码也如此，原因待分析。
		 * */
		int height = tree.getHeight(tree.root);
		System.out.println("The height of tree is " + height);

		//左子树高度
		int leftHeight = tree.leftHeight(tree.root);
		System.out.println("height of left tree:" + leftHeight);

		//右子树高度
		int rightHeight = tree.rightHeight(tree.root);
		System.out.println("height of right tree:" + rightHeight);
	}
	

}
