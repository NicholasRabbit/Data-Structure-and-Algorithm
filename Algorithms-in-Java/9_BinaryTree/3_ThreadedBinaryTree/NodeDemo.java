
//树节点。
public class NodeDemo {

	private int no;
	private String name;
	private NodeDemo left;
	private NodeDemo right;

	//节点线索化标记
	private int leftPointer;   //left指向前驱节点标记为1，否则默认为0
	private int rightPointer;  //right指向后继节点标记为1，否则默认为0


	//中序遍历
	public void inffixList(){
		//先打印左节点
		if(this.getLeft() != null){
			this.getLeft().inffixList();
		}
		//打印当前节点
		System.out.println(this);
		//打印右节点
		if(this.getRight() != null){
			this.getRight().inffixList();
		}
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

	public int getLeftPointer() {
		return leftPointer;
	}

	public void setLeftPointer(int leftPointer) {
		this.leftPointer = leftPointer;
	}

	public int getRightPointer() {
		return rightPointer;
	}

	public void setRightPointer(int rightPointer) {
		this.rightPointer = rightPointer;
	}




	@Override
	public String toString() {
		return "NodeDemo{" +
				"no=" + no +
				", name=" + name + '}';
	}


	
}