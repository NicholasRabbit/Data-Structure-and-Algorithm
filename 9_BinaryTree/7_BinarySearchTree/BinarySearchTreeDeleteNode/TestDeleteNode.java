

public class TestDeleteNode{

	public static void main(String[] args){
		
		int[] array = {8,3,1,6,4,7,10,14,13};

		BinarySortedTree bst = new BinarySortedTree();

		for(int i = 0; i < array.length; i++)
			bst.addNode(new TreeNode(array[i]));

		bst.infixList();

		System.out.println("delete==================");

		//To delete a leaf.
		//bst.deleteNode(13);
		//bst.deleteNode(4);
		//bst.deleteNode(7);

		//To delete a node with one child.
		//bst.deleteNode(14);
		//bst.deleteNode(4);  //delete a leaf and 6 has only one child.
		//bst.deleteNode(6);  
		bst.deleteNode(3);

		
		bst.infixList();


	}		

}
