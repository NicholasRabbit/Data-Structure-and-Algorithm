
/*
* 二叉树范例
*/
public class BinaryTreeDemo {
	
	private NodeDemo root;
	
	//setter , getter
	public void setRoot(NodeDemo root){
		this.root = root;
	}
	public NodeDemo getRoot(){
		return this.root;
	}


	//1,添加节点，用循环的方式
	public int add(NodeDemo newNode){
		//1.1,检查根节点是否为空
		if(root == null){
			root = newNode;
			return 1;
		}
		
		//1.2,遍历添加其他节点，先左后右，不按顺序
		// 注意，这个添加节点代码错误，因为会一直在root左边添加。
		NodeDemo temp = root;
		while(true){
			if(temp.getLeft() == null){
				temp.setLeft(newNode);
				break;
			}else if(temp.getRight() == null){
				temp.setRight(newNode);
				break;
			}else{
				temp = temp.getLeft();  //向左子节点移动一位，以这个左节点为父节点开始下一轮遍历，检查，添加。
			}
		}
		
		return 1;

	}

	//2,添加节点，用递归的方式

	
	/*
	* 3,前序遍历节点，输出顺序“根左右”使用递归。
	* 也可以把则个递归遍历的方法写在节点类里，只是写法稍作改变，参照NodeDemo.java。课堂源码既是如此。
	*/
	public void preList(NodeDemo root){
		//前序遍历先输出父节点，从根节点开始
		System.out.println(root);
		//向左递归
		if(root.getLeft() != null){
			preList(root.getLeft());
		}
		//向右递归
		if(root.getRight() != null){
			preList(root.getRight());
		}

	}

	//4,中序遍历，“左根右”
	public void infixList(NodeDemo root){
		//中序遍历先遍历打印左边的，向左递归
		if(root.getLeft() != null){
			infixList(root.getLeft());
		}
		//打印当前的父节点
		System.out.println(root);
		//向右递归，打印右边的节点
		if(root.getRight() != null){
			infixList(root.getRight());
		}

	}

	//5,后续遍历，“左右根”
	public void suffixList(NodeDemo root){
		//向左遍历
		if(root.getLeft() != null){
			suffixList(root.getLeft());
		}
		//向右遍历
		if(root.getRight() != null){
			suffixList(root.getRight());
		}
		System.out.println(root);
	}

	



}