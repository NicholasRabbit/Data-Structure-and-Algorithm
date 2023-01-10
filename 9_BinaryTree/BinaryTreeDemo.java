
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


	//添加节点
	//1,添加节点，用循环的方式
	// 注意，这个添加节点代码错误，因为会一直在root左边添加。
	public int add(NodeDemo newNode){
		//1.1,检查根节点是否为空
		if(root == null){
			root = newNode;
			return 1;
		}
		
		//1.2,遍历添加其他节点，先左后右，不按顺序
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
	* 一，遍历节点，使用递归的方式。注意体会递归的用法，以及在这三种打印方式的区别。
	* 3,前序遍历节点，输出顺序“根左右”使用递归。
	* 也可以把则个递归遍历的方法写在节点类里，只是写法稍作改变，参照NodeDemo.java。课堂源码既是如此。
	*/
	public void preList(NodeDemo root){
		//判断是否为空树
		if(root == null){
			System.out.println("二叉树为空！");
			return;
		}
		//前序遍历先输出父节点，从根节点开始
		System.out.println(root);
		//向左递归，打印左节点。
		if(root.getLeft() != null){
			preList(root.getLeft());
		}
		//向右递归，打印右节点。
		if(root.getRight() != null){
			preList(root.getRight());
		}

	}

	//4,中序遍历，“左根右”
	public void infixList(NodeDemo root){
		//判断是否为空树
		if(root == null){
			System.out.println("二叉树为空！");
			return;
		}
		
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
		
		//判断是否为空树
		if(root == null){
			System.out.println("二叉树为空！");
			return;
		}
		
		//向左遍历
		if(root.getLeft() != null){
			suffixList(root.getLeft());
		}
		//向右遍历
		if(root.getRight() != null){
			suffixList(root.getRight());
		}
		//打印当前根节点。
		System.out.println(root);
	}
	

	/*
	* 二，查找节点
	* 也是使用递归，同样的有三种前序，中序，后序查找方式。
	* 
	*/

	//1，前序遍历的方式查找，调用节点内部的查找方法。
	public NodeDemo preSearchTree(int no){
		if(root != null){
			return this.root.preSearch(no);
		}else{
			return null;
		}
		
	}

	//2，中序遍历查找
	public NodeDemo infixSearchTree(int no){
		if(root != null){
			return this.root.infixSearch(no);
		}else{
			return null;
		}
	}

	//3，后序遍历查找
	public NodeDemo suffixSearchTree(int no){
		if(root != null)
			return this.root.suffixSearch(no);
		else
			return null;
	}

	
	/*
	* 三，删除节点
	* 删除root节点在这里进行，因为root节点特殊，它不是任何节点的左或右节点。
	* 注意：本方式删除root就直接把整个二叉树置为null了，没有向上提子节点。
	* 向上提子节点的方式后面写。
	*/
	public int deleteNodeTree(int no){
		//1,如果根节点符合条件则删除根节点。
		if(root.getNo() == no){
			this.root = null;
			return 1;
		}
		//2,查找左右字节点，符合条件的删除
		return root.deleteNode(no);  //从root开始查找。

	}

	



}