
//链表中的节点
public class UserNode {
	
	//这里不用private，仅为代码简洁。
	int id;
	String name;
	UserNode next;  

	public UserNode(){
	
	}
	public UserNode(int id,String name){
		this.id = id;
		this.name = name;
	}

	

	//重写toString()
	public String toString(){
		return "{id:" + this.id + ",name:" + this.name + "}";
	}

}