
import java.util.Stack;

public class SingleLinkedList {
	
	//(1)链表的头节点的引用地址就是这个链表的地址，找到头节点就找到了整个链表；
	//(2)这个SingleLinkedList类不是代表链表本身，只是进行维护链表的操作；
	//(3)之所以设置头节点为不存数据的节点，就是为了后面对第二个节点(即第一个有效节点)操作的时候跟其他节点做法一样
	//   因为这第二个节点有引用指向，它也有个next指向第三个节点，这样head节点后面的节点特征统一，方便写代码统一处理。
	private NodeDemo head = new NodeDemo(0,"");  //初始化头节点，里面不存数据
	
	//0.获取头节点，链表头节点的内存地址就是代表链表的地址，一般对链表操作都先获取头节点
	public NodeDemo getHead(){
		return this.head;
	}
	
	//1,添加节点方法add()不做排序
	public int add(NodeDemo newNode){
		//把头节点赋值于临时变量，head头节点不可动，动了就找不到链表的内存地址了，后面要改变temp的next指针
		NodeDemo temp = head;   
		int count = 0;
		//当节点后面还有节点时，继续循环
		while(temp.next != null){
			temp = temp.next;
			count ++;
		}
		/*
		* 上面循环直到temp指向最后一个节点时，temp.next=null
		* 或者当链表中只有一个节点时，temp.next=null不会进入while循环，直接走到这里赋值
		*/
		temp.next = newNode;
		return count;
	}

	/*2,添加节点时按id大小进行排序，addByOrder(..)
		(1),如果要按id排序，需要考虑判断链表中是否存在相同id
		思路：首先不能按照当前节点比新增节点的id小，就在该节点后添加，因为可能后面的节点的id也比新节点的id小
		应该是，循环到一个节点时，此节点是第一个且刚好比新节点的id大，就在该节点前添加
		(2),注意考虑原链表只有一个节点，且该节点id比新节点的小。本例中由于设置链表头节点时无效节点，
		所以第二个才进行比较id，下面的代码是正确的。
		当新写个链表，它的第一个节点是有效节点时就需要考虑这种情况。
	*/
	public int addByOrder(NodeDemo newNode){
		NodeDemo temp = head;
		boolean flag = false; 
		while(true){  //另一种循环的写法，加个if语句判断，跟1中一样作用
			
			if(temp.next == null){
				break;
			}
			//(1)循环时找到第一个比新节点id大的节点，这里temp.next初始值就是第一个有效节点(链表中排在第二的位置)，从这里开始比较id，不要遗漏。
			if(temp.next.id > newNode.id){
				break; 
			}else if(temp.next.id == newNode.id){
				flag = true;
			}
			temp = temp.next;	//节点的id小于新节点的id时，指针后移
		}

		//(2)判断添加的id是否重复
		if(flag){
			System.out.println("id已存在，不可添加");
			return 0;
		}else{
			//(3)找到第一个比新节点id大的节点，添加newNode到该节点前面。
			//当所有节点的id都小于新节点时，在这里也会添加，只是把newNode添加到最后，newNode.next = null而已
			//
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

	/*
	*6,链表中有效节点的个数，不包括头节点
	*/
	public static int getLength(NodeDemo head){
		int count = 0;
		if(head.next == null){
			return count;
		}

		NodeDemo temp = head.next;	
		while(temp != null){
			count++;
			temp = temp.next;  //别忘了指针后移
		}
		return count;
	}

	/*
	*7,获取倒数第n个节点
	*思路：
	*(1)考虑下标越界问题，如果链表只有5个节点，求倒数第6个需要判断并返回null;
	*(2)首先求出总结点数m，然后从前向后遍历，不要从后向前遍历，太麻烦;
	*(3)开始先把指针放到第一个节点上，倒数第n个节点就是向后移动m-n个位置;
	*/
	public NodeDemo getBackwardIndex(NodeDemo head, int index){
		//链表长度，即有效节点个数
		int length = getLength(head);
		//如果是倒数第0个，或着倒数的节点超过了链表长度，则返回null
		if(index <= 0 || index > length){
			return null;
		}
		//把指针指向第一个节点，然后在for循环向移动，例，如果有5个节点，倒数第2个就是再向后移动5-2=3个位置，i<3，即i=0时移动一次，1，2时移动两次即可到指定节点
		NodeDemo currentNode = head.next;
		for(int i=0; i < length - index; i++){  
			currentNode = currentNode.next;  //i=0时会执行一次后移
		}
		return currentNode;
	}

	/*
	*8,链表反转
	*思路：
	*(1)首先判断链表是否为空,或者只有一个子节点
	*(2)新建一个表头，作为反转链表的新表头；
	*(3)去除原有链表的第一个节点，让新表头指向它，原链表把此节点删除，则原链表的第二个节点成了第一个了；
	*(4)再去除原链表的第一个节点(实际是第二个节点变成的)，把此节点放到新链表的表头和第一个节点中间，如此重复即可；
	*
	*/
	public static NodeDemo reverseLinkedList(NodeDemo head){
		//判断链表是否为空
		if(head.next == null || head.next.next == null){
			return head;
		}
		//下面进行反转
		NodeDemo reverseHead = new NodeDemo(0,"");   //声明反转链表的头节点
		NodeDemo current = head.next;  //取出原链表的当前的第一个节点
		NodeDemo next = null;  //第一个节点的下一个节点，备用
		while(current != null){
			next = current.next;   //(a)关键步骤，当取出原链表的第一个节点时，这里next是原链表的第二个节点，在下面会用到
			current.next = reverseHead.next;  //(b)把新的节点插在头节点和第一个节点之间
			reverseHead.next = current;       //给新的头节点的next赋值，让它指向插入的节点
			current = next;   //当前节点后移，下次循环到(a)处时，就表示取出原链表的第一个节点（第二个节点前移）的作用
		}
		head.next = reverseHead.next;  //还使用原来的头节点作为反转后链表的节点
		return head;
	}

	/*
	*9,逆序打印链表
	*思路：
	*(1)错误思路：使用8中反转链表的做法，再打印链表，不可行，因为会破会原链表的结构，下次正序打印时就打印不了；
	*(2)利用栈的先进后出的特性，把链表放进栈里，进行打印；
	*/
	public static Stack<NodeDemo> printReverseList(NodeDemo head){
		Stack<NodeDemo> stack = new Stack<NodeDemo>();
		if(head.next == null){
			return stack;
		}
		NodeDemo cur = head.next;
		while(cur != null){
			stack.push(cur);
			cur = cur.next;
		}
		return stack;
		
	}



}

//节点类
class NodeDemo {
	//这里不用set,get方法，为了可直接用对象调用属性，使代码简洁易读
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
