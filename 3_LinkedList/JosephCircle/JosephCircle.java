
/*
约瑟夫环
*/
public class JosephCircle{
	
	//创建一个头结点，目的是每添加一个节点后，这个节点要指向头结点，形成环形，
	//如果没有头结点就不知道指向谁，形不成环形了。
	NodeDemo first; 

	//一，(1)首先创建一个含有指定个数个节点的约瑟夫环
	public void create(int num){
		//数据校验，0个节点则进行提示
		if(num < 1){
			System.out.println("节点数必须大于0");  
			return ;
		}
		//(2)创建一个辅助指针，帮助遍历添加，这个指针始终指向最后添加的一个节点，这样就方便在它后面进行添加了。
		NodeDemo curNode = null;
		for(int i = 1; i < num; i++){  //节点下标从1开始，方便对应号码。
			//首先创建一个心得节点，准备添加
			NodeDemo newNode = new NodeDemo(i);
			//(3)这里先判断i==1，是为了先给第一个节点first赋值，方便以后指向它，如果不加每次循环都会给first赋值，显然不对。
			if(i == 1){
				first = newNode;
				curNode = first;  //第一个节点指向它自己
			//(4)添加其他节点
			}else{
				curNode.next = newNode;
				newNode.next = first;	//新节点指向第一个节点，始终形成环形。
				curNode = curNode.next; //辅助指针后移一位
			}
		}

	}

	//二，遍历环形链表
	public void showList(JosephCircle josephCircle){
		if(josephCircle.first == null){
			System.out.println("环形链表内没有元素!");
		}
		//创建一个临时节点，作为指针辅助遍历
		NodeDemo temp = first;  
		while(true){
			System.out.println("约瑟夫环==>" + temp.id);  //这个打印要写在判断的前面，否则先判断的话，最后一个节点就打印不出来了。		
			//如果是最后一个节点，则终止
			if(temp.next == first){
				break;
			} 
			//后移一位
			temp = temp.next; 
			
		}


	}




}

class NodeDemo {
	int id;
	NodeDemo next;   //这里属性公开，不用set,get方法，代码简洁
	
	public NodeDemo(){
	
	}

	public NodeDemo(int id){
		this.id = id;
	}

}