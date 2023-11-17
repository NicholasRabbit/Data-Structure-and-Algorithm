

public class TestDeleteNode{

	public static void main(String[] args){
		
		int[] array = {8,3,1,6,4,7,10,14,13};

		BinarySortedTree bst = new BinarySortedTree();

		for(int i = 0; i < array.length; i++)
			bst.addNode(new TreeNode(array[i]));

		bst.infixList();

		//delete 4
		bst.deleteNode(13);
		bst.deleteNode(4);
		bst.deleteNode(7);

		bst.infixList();

	}		

}
