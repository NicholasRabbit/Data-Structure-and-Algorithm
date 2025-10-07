
/*
 * BST: Binary Search/Sort Tree
 * 二叉搜索树,也称二叉排序树。
 *
 * 具体分析见个人笔记。
 *
 * */


public class BinarySearchTreeTest{
	
	public static void main(String[] args){
		
		int[] arr = {8,3,1,4,6,7,10,14,13};

		//遍历添加树
		BinarySearchTree bst = new BinarySearchTree();
		for(int i = 0; i < arr.length; i++){
			bst.addNode(new TreeNode(arr[i]));
		}

		//中序遍历查看结果。
		bst.infixShow(bst.root);
		

	}

}
