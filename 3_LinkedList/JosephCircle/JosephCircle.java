
/*
约瑟夫环，实际是一个单向链表环。
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
			//首先创建一个新的节点，准备添加
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

	/*
	* 三，出圈操作，即从第n个节点开始数，再数到m个出圈
	* int totalNum : 约瑟夫环中的节点数
	* 
	*/
	public NodeDemo pop(int startNo,int countNo,int totalNum){
		//1）首先校验参数的合法性，如果约瑟夫环的第一个节点为空则它不存在
		//   开始数小于1说明也不可能，countNo == totalNum老师课堂代码校验了，
		//   其实可以不校验，因为是环形的，数到最后一个还可以继续从第一个数
		if(first == null || startNo < 1 || countNo == totalNum){
			System.out.println("输入数据不合理");
		}
		/*
		* 2）设置一个变量endNode，指向环的最后一个节点，后面让节点出出圈时会用到 
		*/
		NodeDemo endNode = first;  //先让它指向第一个节点，开始遍历
		while(true){
			if(endNode.next == first){
				break;
			}
			endNode = endNode.next;
		}
		/**
		* 3）让first和endNode移动到开始的节点，因为first最开始就指向第一个节点，所以从第n个节点开始，就移动n-1个位置。
		*    endNode同理。
		*/
		for(int i = 0; i < startNo -1; i++){
			first = first.next;
			endNode = endNode.next;
		}

		/*
		* 4）进行出圈操作，这里执行while循环，按照规定直到剩一个节点为止。
		*/
		while(true){
			if(endNode == first){  //最后一个节点就是第一个节点，说明最开始只添加了一个节点。
				break;
			}
			//向后数m个节点出圈，就从当前节点后移m-1个位置
			for(int j = 0; j < countNo - 1; j++){
				first = first.next;
				endNode = endNode.next;
			}
			//上面for循环结束后，first指向要出圈的节点。
			//下面是进行出圈操作
			System.out.println("要出圈的节点==>" + first.id);
			first = first.next;  //节点后移一位，指向新的头节点
			endNode.next = first;     //最后一个节点的next指向新的头节点，原来的节点就没有引用指向了，即为出圈。
		}
		//5）最后first就指向圈中唯一剩余的一个节点
		System.out.println("按规定令节点出圈后，剩余最后一个==>" + first.id);
		System.out.println("first.hashCode()==>" + first.hashCode() + "\t\n" + "endNode==>" + endNode.hashCode());

		return first;		

	}

}


//节点
class NodeDemo {
	int id;
	NodeDemo next;   //这里属性公开，不用set,get方法，代码简洁
	
	public NodeDemo(){
	
	}

	public NodeDemo(int id){
		this.id = id;
	}

}