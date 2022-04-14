public class SingleLinkedList {
	
	private NodeDemo head = new NodeDemo(0,"");  //��ʼ��ͷ�ڵ㣬���治������

	
	//1,��ӽڵ㷽��add()��������
	public int add(NodeDemo newNode){
		//��ͷ�ڵ㸳ֵ����ʱ������headͷ�ڵ㲻�ɶ������˾��Ҳ���������ڴ��ַ�ˣ�����Ҫ�ı�temp��nextָ��
		NodeDemo temp = head;  
		//���ڵ���滹�нڵ�ʱ������ѭ��
		while(temp.next != null){
			temp = temp.next;
		}
		//temp.next == nullʱ����һ��
		temp.next = newNode;
		int count = 1;
		return count;
	}

	/*2,��ӽڵ�ʱ��id��С��������addByOrder(..)
		(1),���Ҫ��id������Ҫ�����ж��������Ƿ������ͬid
		˼·�����Ȳ��ܰ��յ�ǰ�ڵ�������ڵ��idС�����ڸýڵ����ӣ���Ϊ���ܺ���Ľڵ��idҲ���½ڵ��idС
		Ӧ���ǣ�ѭ����һ���ڵ�ʱ���˽ڵ�պñ��½ڵ��id�󣬾��ڸýڵ�ǰ���
	*/
	public int addByOrder(NodeDemo newNode){
		NodeDemo temp = head;
		boolean flag = false; 
		while(true){  //��һ��ѭ����д�����Ӹ�if����жϣ���1��һ������
			
			if(temp.next == null){
				break;
			}
			//(1)ѭ��ʱ�ҵ���һ�����½ڵ�id��Ľڵ�
			if(temp.next.id > newNode.id){
				break; 
			}else if(temp.next.id == newNode.id){
				flag = true;
			}
			temp = temp.next;	//ָ�����
		}

		//(2)�ж���ӵ�id�Ƿ��ظ�
		if(flag){
			System.out.println("id�Ѵ��ڣ��������");
			return 0;
		}else{
			//�ҵ���һ�����½ڵ�id��Ľڵ㣬���newNode���ýڵ�ǰ��
			//�����нڵ��id��С���½ڵ�ʱ��������Ҳ����ӣ�ֻ�ǰ�newNode��ӵ����newNode.next = null����
			newNode.next = temp.next;  //��ӵ�B(temp.nextָ��Ľڵ�)�ڵ�ǰ
			temp.next = newNode;       //��newNode�ĵ�ַ��A.next(temp��next����)
		}

		return 1;
		
	}

	//3,������������
	public void show(){
		//(1)�����ж������Ƿ�Ϊ��
		if(head.next == null){
			System.out.println("����Ϊ�գ�");
			return;
		}
		//(2),�������Ȼ�ȡͷ�ڵ����ĵ�һ���ڵ㣬��Ϊͷ�ڵ㲻��Ҫ��ӡ����Ҫд�ɣ�temp = head,�������ӡͷ�ڵ�
		//     ע��ѧϰ��ʱ����temp���÷� 
		NodeDemo temp = head.next; 
		while(true){
			//ѭ�������һ���ڵ�ʱ����ֹѭ��
			if(temp == null){
				break;  
			}
			//��ӡ�ڵ�
			System.out.println(temp);
			//��ӡ���ָ�����һλ�����¸��ڵ�
			temp = temp.next;
		}
	}

	//4,�޸Ľڵ�
	public void updateNode(NodeDemo node){
		
		//4.(1)�����ж������Ƿ�Ϊ��
		
		if(head.next == null){
			System.out.println("����Ϊ�գ��޷��޸�");
			return;
		}
		//(2)����id�޸Ľڵ���Ϣ��ע��id���ɸ���
		NodeDemo temp = head.next;
		boolean flag = false;
		while(true){
			//����������û���ҵ���ͬ��id
			if(temp == null){
				System.out.println("û���ҵ���id");
				break;
			}
			//��������б���
			if(temp.id == node.id){
				flag = true;   //�ҵ��ڵ㣬flag��Ϊtrue
				break;     //�ҵ�����ֹѭ��������temp�ᱻ���¸�ֵ
			}
			temp = temp.next;  
		}
		//�ҵ��ڵ�󣬽����޸�
		if(flag){
			temp.name = node.name;
		}
	}

	/*
	*5,ɾ���ڵ㡣ע�⣺��ͨ���û�ָ���ڵ�����ɾ���������ڴ��ַ����Ϊ������Ԥ��֪���ڴ��ַ
	*˼·��
	*��Ϊ�ǵ���������ڵ�û��ǰ�ڵ�ĵ�ַ����˲��ܱ�����Ҫɾ���Ľڵ���ɾ�������Ǳ�������ɾ���ڵ��ǰһ���ڵ�ʱ��ͨ��temp.next����ɾ������
	*/
	public void deleteNode(int id){
		NodeDemo temp = head;
		boolean flag = false;
		while(true){
			if(temp.next == null){
				System.out.println("û���ҵ��ýڵ㣡");
				break;
			} else if(temp.next.id == id){
				flag = true;   //�����ҵ���Ҫɾ���Ľڵ㣬��flag=true
				break;
			}
			//�ڵ���ƣ�������
			temp = temp.next;
		}

		if(flag){
			temp.next = temp.next.next;   //����ѱ�ɾ���ڵ�N���¸��ڵ���ڴ��ַ��Nǰһ���ڵ��next�����ɴﵽɾ��Ŀ�ģ�����Nû�б�����ָ�򣬻ᱻ����
		}else{
			System.out.println("û���ҵ��ýڵ㣡");
		}
	}

}


class NodeDemo {

	public int id;
	public String name;
	public NodeDemo next; //ָ����һ���ڵ�

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