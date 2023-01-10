
//树节点。
public class NodeDemo {

	private int no;
	private String name;
	private NodeDemo left;
	private NodeDemo right;

	//统计比较次数，用实例变量。(错误，因为每个节点对象都有一个此实例变量，不是全局的。)
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


	/*
	* 三，删除节点。根据指定编号。
	* 
	* 删除规则：
	* 1,因为本例的二叉树是单向的，子节点没有父节点的引用，因此要判断当前父节点的左或右节点是否符合要求，
	*   然后设置this.left/right=null。
	* 2,如果是叶子节点，则直接删除；
	* 3,删除非叶子节点
	*	3.1 方式一
	*		如果是非叶子节点，按简单的删除方式，删除子树;
	*   3.2 方式二
	*		如果被删除的非叶子节点下只有一个叶子，则把该叶子提到被删除节点的位置;
	*		如果被删除的非叶子节点下左右叶子都有，则把左节点提到被删除节点的位置。
	* 4,这里删除左右节点，删除root节点的操作与左右节点特点不同，在BinaryTreeDemo里单独执行。
	*
	* 思路步骤：
	* 1,先比较当前节点的左节点编号是否符合要求，符合则删除，否则比较右节点；
	* 2,如果左右节点都不符合要求则向下递归，先向左递归，即把左节点当作父节点调用递归方法，
	*   就递归执行步骤1中的操作。然后同理向右递归。
	* 
	*/
	//3.1 方式一，删除非叶子节点。
	public int deleteNode(int no){
		//如果当前节点的左子节点符合要求则删除左子节点，注意：这里把左子节点以下的一并删除了。
		//this.left != null这个条件不要忘了，因为如果this.left=null说明当前节点是叶子节点，查找到头了，没必要比较了。
		if(this.left != null && this.left.no == no){
			this.left = null;
			return 1;
		}
		//右子节点删除同理。
		if(this.right != null && this.right.no == no){
			this.right = null;
			return 1;
		}
		
		//如果左右子节点都不符合要求，则向下递归
		//先向左递归
		int delSuccess = 0;  //是否删除成功标志
		if(this.left != null){
			//把this.left当作this来调用deleteNode(..)方法，即把左节点当作父节点来比较其左右节点
			delSuccess = this.left.deleteNode(no); 
		}
		/*
		* 如果删除成功则不需向右递归了。老师课堂代码返回值为void，个人觉着不妥，
		* 因为返回值空，下面的向右递归程序还是会执行，而节点的no理应作为节点的唯一标识，
		* 在二叉树中no不应该存在重复的，所以没必要向右递归了。
		*/
		if(delSuccess == 1) return 1;

		//如果前面都没找到，则向右递归。
		if(this.right != null){
			delSuccess = this.right.deleteNode(no);
		}

		return delSuccess;
			
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