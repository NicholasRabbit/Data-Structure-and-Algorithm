
/*
* 1，使用单链表做为哈希表中的数组的元素。
* 2，这次的单链表的头节点不为空，是有效节点。直接添加一个实际的节点。跟前面学习单链表有点不同。
*    这样做是为了让哈希表中的数组的引用直接指向这个有效头节点。
* 3，重写一遍，复习单链表。
*/
public class SingleLinkedList {
	
	//链表的头节点的引用地址就是这个链表的地址，找到头节点就找到了整个链表
	//这个SingleLinkedList类不是代表链表本身，只是进行维护链表的操作
	private UserNode head;  

	//1,添加节点，不按编号id的顺序，而是依次向最后添加。注意这里第一个节点就是有效节点，不为空。
	public void add(UserNode newNode){
		
		//判断头节点是否为空，空则添加
		if(head == null){
			head = newNode;
			return;
		}

		//把头节点赋值给临时变量用来遍历，直接用头节点head的话会导致其引用值变化导致找不到该链表。
		UserNode temp = head;
		//遍历到最后的节点，在其后添加新节点
		while(temp.next != null){
			temp = temp.next;
		}
		
		/*
		* 上面循环直到temp指向最后一个节点时，temp.next=null
		* 或者当链表中只有一个节点时，temp.next=null不会进入while循环，直接走到这里赋值
		*/
		temp.next = newNode;

	}
	
	//2,按顺序添加元素，根据UserNode的id值按从小到大放入链表，遇到相同id的就覆盖旧值。
	public void addByOrder(UserNode newNode){
		//头节点赋值临时变量备用。
		UserNode temp = head;	
		
		if(head == null || head.id == newNode.id){
			head = newNode;
			return;
		}

		//2.1 因为头节点没有next指向，所以新节点比头节点的id小的情况，单独处理，就把新节点放到头一个。
		if(head.id > newNode.id){
			head = newNode;
			head.next = temp;
			return;  //注意：这里添加完了一定不要忘记return,否则下边还会添加，最终形成一个环形链表。
		}

		//2.2 把头节点赋值给临时变量用来遍历，直接用头节点head的话会导致其引用值变化导致找不到该链表。
		//因为上述代码把newNode <= head 的情况处理了，所以后面考虑其他情况。
		temp = head;
		boolean flag = false;
		while(temp.next != null){
			//遍历找到第一个比新节点大的节点
			if(temp.next.id > newNode.id){
				flag = true;
				break;
			//如果相等则直接替换原有的节点，注意不要把新节点的所有属性值赋予原节点，频繁访问内存导致速度太慢
			}else if(temp.next.id == newNode.id){
				//新节点链接后面的链表
				newNode.next = temp.next.next;
				//新节点链接前面的链表
				temp.next = newNode;
				break;
			}
			
			temp = temp.next;
		}

		//找到位置，不是末尾，则中间插入新节点
		if(flag){
			//把大值的节点赋值给新节点的next
			newNode.next = temp.next;
			//把新节点的引用赋值给temp.next，完成添加
			temp.next = newNode;
			return;
		}

		//走到这里说明新节点最大则添加到末尾
		temp.next = newNode;

	}

	//3.1,展示链表内的所有元素（带异常）
	public void show(){
		if(head == null){
			//哈希表遍历的时候，空表不可抛异常，因为有可能数组中下个表不是空表，抛异常导致后面的无法展示
			EmptyListException ele = new EmptyListException("The list is empty!");
			throw ele;
		}
		//因为第一个节点head是有效节点，所以从head开始，使用do..while..先打印再往下遍历。
		UserNode temp = head;
		do{
			System.out.printf("list==>{id:%d,name:%s} ",temp.id,temp.name);
			temp = temp.next;
		}while(temp != null);
		
		System.out.println();

	}

	//3.2,展示链表内的所有元素（不带异常）
	public void showList(){
		if(head == null){
			//哈希表遍历的时候，空表不可抛异常，因为有可能数组中下个表不是空表，抛异常导致后面的无法展示
			System.out.println("链表为空{}");
			return;  //别忘了加return，否则还会向下执行，在116行包NullPointerException.
		}
		UserNode temp = head;
		do{
			System.out.printf("list==>{id:%d,name:%s} ",temp.id,temp.name);
			temp = temp.next;
		}while(temp != null);
		
		System.out.println();

	}

	//4,查询元素，根据id
	public UserNode getById(int id){
		//判断链表是否为空
		if(head == null){
			System.out.println("链表为空{}");
			return null;  //别忘了加return，否则还会向下执行，在116行包NullPointerException.
		}
		
		UserNode temp = head;
		do{
			if(id == temp.id){
				return temp;
			}
			temp = temp.next;
		}while(temp != null);
		
		return null;

	}

	//5,根据id删除节点
	public int deleteById(int id){
		

		if(head == null){
			System.out.println("链表为空{}");
			return -1;
		}

		//5.1 删除头节点
		if(head.id == id){
			head = head.next;  //直接让链表的头节点指向下一个next引用即可。
			System.out.println("删除成功！");
			return 1;  //删除后记得return，否则还往下走
		}
		//5.2 删除其他节点,从第二个节点开始遍历
		UserNode temp = head;
		while(true){
			if(temp.next == null){
				System.out.println("无此id");
				break;
			}
			if(temp.next.id == id){
				//删除就直接被删节点A的上节点的next指向A的下一个节点即可。
				//删除最后一个节点时 temp.next.next 是 null
				temp.next = temp.next.next;  
				System.out.println("删除成功！");
				return 1;
			}
			
		}
		return -1;
	
	}


}