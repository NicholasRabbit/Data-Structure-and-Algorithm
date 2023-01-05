
//树节点。
public class NodeDemo {

	private int no;
	private String name;
	private NodeDemo left;
	private NodeDemo right;

	//统计比较次数，用实例变量。(错误，因为每个节点都有一个此实例变量，不是全局的。)
	private int count;

	/*
	* 一，遍历节点，可在节点内部写遍历的方法，也可在BinaryTreeDemo中写
	* 在节点内部这里写：前序方式遍历节点。中序，后续遍历方法同理。
	*/
	public void preListNode(){
	
		//前序的先打印父节点
		System.out.println(this);
		//向左递归
		if(this.left != null){
			this.left.preListNode();
		}
		if(this.right != null){
			this.right.preListNode();
		}
	}

	/*
	* 二，查找节点
	* (1)查找节点现在节点内部查找，然后在二叉树类内调用即可。
	* 结合各自对应的遍历方式理解，遍历节点的顺序也是判断的顺序。
	* 
	* (2)统计这三种查找的比较次数，注意统计变量放置的位置，不要都放到方法头一句，
	* 因为如果放到头一句，那递归一次就统计一次，是错误的，应放到 if(this.no == no) 之前
	*/
	//1,前序遍历方式查找，“根左右”
	public NodeDemo preSearch(int no){
		
		//定义一个当前递归栈中的局部变量，只在当前方法栈有效。注意区分和实例变量的区别。
		NodeDemo temp = null;
		
		System.out.println("前序查找统计~~");
		//1.1 先判断当前根节点开始查找
		if(this.no == no){
			return this;
		}
		//1.2 没找的话再向左查找
		if(this.left != null){
			temp = this.left.preSearch(no); //调用当前节点的左节点的 preSearch(..)方法进行比较。
		}
		//向左递归的返回值如果不是null，说明进入了1.1的判断，找到了符合条件的节点，直接返回即可。
		if(temp != null){
			return temp;
		}

		//1.3 向右递归查找
		if(this.right != null){
			temp = this.right.preSearch(no);
		}
		
		/*
		* 右递归判断时注意：
		* 查完右节点直接返回temp就行了，因为如果一直递归找到叶子节点还没找到自然就返回null。
		* 不需要再判断右递归的结果是否为null,因为右递归完就查找结束了，右节点调用递归方法的
		* 时候，已经在1.1处判断了，实际这个递归的思路是把左右节点当成子树的根节点进行判断，
		* 最终的判断还是在1.1处进行的。
		* 
		*/
		return temp;
	}

	//2,中序遍历的方式查找。“左根右”。
	public NodeDemo infixSearch(int no){
		//定义当前递归栈临时变量。
		NodeDemo temp = null;
		
		//2.1 先判断左节点，向左递归
		if(this.left != null){
			temp = this.left.infixSearch(no);
		}
		//同样的,左递归时如果temp不是null，说明找到了。
		if(temp != null){
			return temp;
		}
		
		System.out.println("中序查找统计~~");
		
		//2.2 判断当前根节点
		if(this.no == no){
			return this;
		}
		
		//2.3 判断当前右节点
		if(this.right != null){
			temp = this.right.infixSearch(no);
		}

		//右节点是最后判断的，无论temp是否为null，都得返回
		return temp;
	
	}

	//3,后序遍历查找。“左右根”。
	public NodeDemo suffixSearch(int no){
		NodeDemo temp = null;
		//3.1 先判断左节点
		if(this.left != null){
			temp = this.left.suffixSearch(no);
		}
		//同样的,左递归时如果temp不是null，说明找到了。
		if(temp != null){
			return temp;
		}
		//3.2 其次向右递归
		if(this.right != null){
			temp = this.right.suffixSearch(no);
		}
		//注意，这里与之前的两种查找不同，因为向右递归不是最后判断的，因此如果找到则直接返回。
		if(temp != null)
			return temp;

		System.out.println("后序查找统计~~");

		//3.3 最后判断当前子树的根节点
		if(this.no == no){
			return this;
		}
		
		//最后返回temp，因为在3.2向右递归时无论找到或找不到都把返回值赋值于temp了。
		return temp;

	}
	

	
	public NodeDemo(){
	
	}
	public NodeDemo(int no,String name){
		this.no = no;
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NodeDemo getLeft() {
		return left;
	}

	public void setLeft(NodeDemo left) {
		this.left = left;
	}

	public NodeDemo getRight() {
		return right;
	}

	public void setRight(NodeDemo right) {
		this.right = right;
	}


	@Override
	public String toString() {
		return "NodeDemo{" +
				"no=" + no +
				", name=" + name + '}';
	}


	
}