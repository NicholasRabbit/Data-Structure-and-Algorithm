

/*
* 哈希表范例
*/
public class HashTableDemo {
	

	private SingleLinkedList[] listArray;
	private int size;

	public HashTableDemo(){
	
	}
	
	//构造函数初始化哈希表内数组的尺寸
	public HashTableDemo(int size){
		this.size = size;
		listArray = new SingleLinkedList[size];
		//注意这里初始化后，数组内每个元素的值都是null
		//需要给每个元素赋值一个SingleLinkedList对象，否则添加元素或遍历的时候会报空指针异常
		for(int i = 0; i < listArray.length; i++){
			listArray[i] = new SingleLinkedList();
		}
		
	}

	//1,添加节点
	public void add(UserNode user){
		//1.1,添加元素的时候要根据新节点的编号，取模来判断放到数组中哪个位置的链表里
		int no = hashIndex(user.id);  
		listArray[no].add(user);
	
	}

	//1.2,按顺序添加节点
	public void addByOrder(UserNode user){
		//1.1,添加元素的时候要根据新节点的编号，取模来判断放到数组中哪个位置的链表里
		int no = hashIndex(user.id);  
		listArray[no].addByOrder(user);
	
	}


	
	//2,展示节点
	public void showTable(){
		for(int i = 0; i < listArray.length; i++){
			listArray[i].showList();
		}
	
	}
	
	//3,根据id查找节点
	public UserNode findById(int id){
		//查找时也是根据散列函数，先找到数组中元素的位置
		int no = hashIndex(id);
		//然后在链表中查找
		return listArray[no].getById(id);
	}

	//4,删除节点，根据id
	public int deleteById(int id){
		//删除时也是先找到数组中元素的位置
		int no = hashIndex(id);
		//在链表中删除
		return listArray[no].deleteById(id);
	}

	
	/*
	* 9,取模方法，简单版的散列函数。
	* 因为保证把新元素均匀的添加到哈希表中，实际链表的散列函数的算法要复杂。
	*/
	public int hashIndex(int no){
		//取余数。
		return no % size;
	}


}