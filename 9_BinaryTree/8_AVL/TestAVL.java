

/*
 * AVL树测试
 * */

public class TestAVL {

	public static void main(String[] args){
	
		int[] arr = {2,1,4,3,7,6,9,8,10};	
		AVLTree tree = new AVLTree();
		for(int i = 0; i < arr.length; i++){
			tree.add(new TreeNode(arr[i]));
		}

		//中序遍历
		tree.infixList(tree.root);

		/*
		 * 获取树的高度
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

		tree.rotateToLeft();
		height = tree.getHeight(tree.root);
		System.out.println("The height of tree is " + height);
	}
}
