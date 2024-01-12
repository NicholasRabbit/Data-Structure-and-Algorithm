

/*
 * AVL树测试
 * */

public class TestAVL {

	public static void main(String[] args){
	
		int[] arr = {2,1,4,3,7,6,9,8,10};	

		AVLTree tree = new AVLTree();
		for(int i = 0; i < arr.length; i++){
			//tree.add(new TreeNode(arr[i]));   //使用下面的方法就注释本行
			TestAVL.rotateWhenAdd(tree, new TreeNode(arr[i]));
		}

		//中序遍历
		tree.infixList(tree.root);
		//树的高度
		height(tree);
		//自动旋转(个人思路)
		//rotateAutomatically(tree);  //使用rotateWhenAdd(...)时注释本行


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

	/*
	 * 2, 自动旋转
	 * 这里旋转方法是使用for循环对整个BST旋转，是个人思路。
	 * 老师的是每次添加节点后判断，如果高度差 > 1 则进行旋转。
	 * */
	public static void rotateAutomatically(AVLTree tree){

		//单次旋转
		//tree.rotateToLeft();  //使用下面自动旋转时把这里注释
		//height = tree.getHeight(tree.root);
		//System.out.println("The height of tree is " + height);

		tree.rotate();
		int height = tree.getHeight(tree.root);  //得树高度为4，比实际高度大1，并不是旋转代码错误。
		System.out.println("End result:" + height);

		
	}

	/*
	 * 3, 按照老师所讲思路进行左旋。每添加一个节点后，就进行一次判断。
	 * */
	public static void rotateWhenAdd(AVLTree tree, TreeNode node){
		tree.rotateLeftWhenAdd(node);
	}

}
