
//树节点。
public class NodeDemo {

	private int no;
	private String name;
	private NodeDemo left;
	private NodeDemo right;

	//前序方式遍历节点。
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