public class SingleLinkedList {
	
	private NodeDemo head = new NodeDemo(0,"");  //初始化头节点，里面不存数据

	
	//1,添加节点方法add()不做排序
	public int add(NodeDemo newNode){
		//把头节点赋值于临时变量，head头节点不可动，动了就找不到链表的内存地址了，后面要改变temp的next指针
		NodeDemo temp = head;  
		//当节点后面还有节点时，继续循环
		while(temp.next != null){
			temp = temp.next;
		}
		//temp.next == null时走下一句
		temp.next = newNode;
		int count = 1;
		return count;
	}

	/*2,添加节点时按id大小进行排序，addByOrder(..)
		(1),如果要按id排序，需要考虑判断链表中是否存在相同id
		思路：首先不能按照当前节点比新增节点的id小，就在该节点后添加，因为可能后面的节点的id也比新节点的id小
		应该是，循环到一个节点时，此节点刚好比新节点的id大，就在该节点前添加
	*/
	public int addByOrder(NodeDemo newNode){
		NodeDemo temp = head;
		boolean flag = false; 
		while(true){  //另一种循环的写法，加个if语句判断，跟1中一样作用
			
			if(temp.next == null){
				break;
			}
			//(1)循环时找到第一个比新节点id大的节点
			if(temp.next.id > newNode.id){
				break; 
			}else if(temp.next.id == newNode.id){
				flag = true;
			}
			temp = temp.next;	//指针后移
		}

		//(2)判断添加的id是否重复
		if(flag){
			System.out.println("id已存在，不可添加");
			return 0;
		}else{
			//找到第一个比新节点id大的节点，添加newNode到该节点前面
			//当所有节点的id都小于新节点时，在这里也会添加，只是把newNode添加到最后，newNode.next = null而已
			newNode.next = temp.next;  //添加到B(temp.next指向的节点)节点前
			temp.next = newNode;       //把newNode的地址给A.next(temp的next属性)
		}

		return 1;
		
	}

	//3,遍历单向链表
	public void show(){
		//(1)首先判断链表是否为空
		if(head.next == null){
			System.out.println("链表为空！");
			return;
		}
		//(2),遍历首先获取头节点后面的第一个节点，因为头节点不需要打印，不要写成：temp = head,这样会打印头节点
		//     注意学习临时变量temp的用法 
		NodeDemo temp = head.next; 
		while(true){
			//循环到最后一个节点时，终止循环
			if(temp == null){
				break;  
			}
			//打印节点
			System.out.println(temp);
			//打印完后，指针后移一位，到下个节点
			temp = temp.next;
		}
	}

	//4,修改节点
	public void updateNode(NodeDemo node){
		
		//4.(1)首先判断链表是否为空
		
		if(head.next == null){
			System.out.println("链表为空，无法修改");
			return;
		}
		//(2)根据id修改节点信息，注意id不可更改
		NodeDemo temp = head.next;
		boolean flag = false;
		while(true){
			//遍历完链表，没有找到相同的id
			if(temp == null){
				System.out.println("没有找到此id");
				break;
			}
			//对链表进行遍历
			if(temp.id == node.id){
				flag = true;   //找到节点，flag改为true
				break;     //找到后终止循环，否则temp会被重新赋值
			}
			temp = temp.next;  
		}
		//找到节点后，进行修改
		if(flag){
			temp.name = node.name;
		}
	}

	/*
	*5,删除节点。注意：是通过用户指定节点的序号删除，不是内存地址，因为不可能预先知道内存地址
	*思路：
	*因为是单向链表，后节点没有前节点的地址，因此不能遍历到要删除的节点再删除，而是遍历到被删除节点的前一个节点时就通过temp.next进行删除操作
	*/
	public void deleteNode(int id){
		NodeDemo temp = head;
		boolean flag = false;
		while(true){
			if(temp.next == null){
				System.out.println("没有找到该节点！");
				break;
			} else if(temp.next.id == id){
				flag = true;   //这里找到了要删除的节点，把flag=true
				break;
			}
			//节点后移，继续找
			temp = temp.next;
		}

		if(flag){
			temp.next = temp.next.next;   //这里把被删除节点N的下个节点的内存地址给N前一个节点的next，即可达到删除目的，这是N没有被引用指向，会被回收
		}else{
			System.out.println("没有找到该节点！");
		}
	}

}


class NodeDemo {

	public int id;
	public String name;
	public NodeDemo next; //指向下一个节点

	public NodeDemo(){
	}
	public NodeDemo(int id, String name){
		this.id = id;
		this.name = name;
	}

	public String toString(){
		return "Node==>" + "{id=" + id + ";name=" + name + "}";
	}
}