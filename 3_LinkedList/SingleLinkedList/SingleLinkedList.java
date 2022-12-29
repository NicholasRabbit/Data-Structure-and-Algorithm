
import java.util.Stack;

public class SingleLinkedList {
	
	//(1)�����ͷ�ڵ�����õ�ַ�����������ĵ�ַ���ҵ�ͷ�ڵ���ҵ�����������
	//(2)���SingleLinkedList�಻�Ǵ���������ֻ�ǽ���ά������Ĳ�����
	//(3)֮��������ͷ�ڵ�Ϊ�������ݵĽڵ㣬����Ϊ�˺���Եڶ����ڵ�(����һ����Ч�ڵ�)������ʱ��������ڵ�����һ��
	//   ��Ϊ��ڶ����ڵ�������ָ����Ҳ�и�nextָ��������ڵ㣬����head�ڵ����Ľڵ�����ͳһ������д����ͳһ����
	private NodeDemo head = new NodeDemo(0,"");  //��ʼ��ͷ�ڵ㣬���治������
	
	//0.��ȡͷ�ڵ㣬����ͷ�ڵ���ڴ��ַ���Ǵ�������ĵ�ַ��һ�������������Ȼ�ȡͷ�ڵ�
	public NodeDemo getHead(){
		return this.head;
	}
	
	//1,��ӽڵ㷽��add()��������
	public int add(NodeDemo newNode){
		//��ͷ�ڵ㸳ֵ����ʱ������headͷ�ڵ㲻�ɶ������˾��Ҳ���������ڴ��ַ�ˣ�����Ҫ�ı�temp��nextָ��
		NodeDemo temp = head;   
		int count = 0;
		//���ڵ���滹�нڵ�ʱ������ѭ��
		while(temp.next != null){
			temp = temp.next;
			count ++;
		}
		/*
		* ����ѭ��ֱ��tempָ�����һ���ڵ�ʱ��temp.next=null
		* ���ߵ�������ֻ��һ���ڵ�ʱ��temp.next=null�������whileѭ����ֱ���ߵ����︳ֵ
		*/
		temp.next = newNode;
		return count;
	}

	/*2,��ӽڵ�ʱ��id��С��������addByOrder(..)
		(1),���Ҫ��id������Ҫ�����ж��������Ƿ������ͬid
		˼·�����Ȳ��ܰ��յ�ǰ�ڵ�������ڵ��idС�����ڸýڵ����ӣ���Ϊ���ܺ���Ľڵ��idҲ���½ڵ��idС
		Ӧ���ǣ�ѭ����һ���ڵ�ʱ���˽ڵ��ǵ�һ���Ҹպñ��½ڵ��id�󣬾��ڸýڵ�ǰ���
		(2),ע�⿼��ԭ����ֻ��һ���ڵ㣬�Ҹýڵ�id���½ڵ��С��������������������ͷ�ڵ�ʱ��Ч�ڵ㣬
		���Եڶ����Ž��бȽ�id������Ĵ�������ȷ�ġ�
		����д���������ĵ�һ���ڵ�����Ч�ڵ�ʱ����Ҫ�������������
	*/
	public int addByOrder(NodeDemo newNode){
		NodeDemo temp = head;
		boolean flag = false; 
		while(true){  //��һ��ѭ����д�����Ӹ�if����жϣ���1��һ������
			
			if(temp.next == null){
				break;
			}
			//(1)ѭ��ʱ�ҵ���һ�����½ڵ�id��Ľڵ㣬����temp.next��ʼֵ���ǵ�һ����Ч�ڵ�(���������ڵڶ���λ��)�������￪ʼ�Ƚ�id����Ҫ��©��
			if(temp.next.id > newNode.id){
				break; 
			}else if(temp.next.id == newNode.id){
				flag = true;
			}
			temp = temp.next;	//�ڵ��idС���½ڵ��idʱ��ָ�����
		}

		//(2)�ж���ӵ�id�Ƿ��ظ�
		if(flag){
			System.out.println("id�Ѵ��ڣ��������");
			return 0;
		}else{
			//(3)�ҵ���һ�����½ڵ�id��Ľڵ㣬���newNode���ýڵ�ǰ�档
			//�����нڵ��id��С���½ڵ�ʱ��������Ҳ����ӣ�ֻ�ǰ�newNode��ӵ����newNode.next = null����
			//
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

	/*
	*6,��������Ч�ڵ�ĸ�����������ͷ�ڵ�
	*/
	public static int getLength(NodeDemo head){
		int count = 0;
		if(head.next == null){
			return count;
		}

		NodeDemo temp = head.next;	
		while(temp != null){
			count++;
			temp = temp.next;  //������ָ�����
		}
		return count;
	}

	/*
	*7,��ȡ������n���ڵ�
	*˼·��
	*(1)�����±�Խ�����⣬�������ֻ��5���ڵ㣬������6����Ҫ�жϲ�����null;
	*(2)��������ܽ����m��Ȼ���ǰ����������Ҫ�Ӻ���ǰ������̫�鷳;
	*(3)��ʼ�Ȱ�ָ��ŵ���һ���ڵ��ϣ�������n���ڵ��������ƶ�m-n��λ��;
	*/
	public NodeDemo getBackwardIndex(NodeDemo head, int index){
		//�����ȣ�����Ч�ڵ����
		int length = getLength(head);
		//����ǵ�����0�������ŵ����Ľڵ㳬���������ȣ��򷵻�null
		if(index <= 0 || index > length){
			return null;
		}
		//��ָ��ָ���һ���ڵ㣬Ȼ����forѭ�����ƶ������������5���ڵ㣬������2������������ƶ�5-2=3��λ�ã�i<3����i=0ʱ�ƶ�һ�Σ�1��2ʱ�ƶ����μ��ɵ�ָ���ڵ�
		NodeDemo currentNode = head.next;
		for(int i=0; i < length - index; i++){  
			currentNode = currentNode.next;  //i=0ʱ��ִ��һ�κ���
		}
		return currentNode;
	}

	/*
	*8,����ת
	*˼·��
	*(1)�����ж������Ƿ�Ϊ��,����ֻ��һ���ӽڵ�
	*(2)�½�һ����ͷ����Ϊ��ת������±�ͷ��
	*(3)ȥ��ԭ������ĵ�һ���ڵ㣬���±�ͷָ������ԭ����Ѵ˽ڵ�ɾ������ԭ����ĵڶ����ڵ���˵�һ���ˣ�
	*(4)��ȥ��ԭ����ĵ�һ���ڵ�(ʵ���ǵڶ����ڵ��ɵ�)���Ѵ˽ڵ�ŵ�������ı�ͷ�͵�һ���ڵ��м䣬����ظ����ɣ�
	*
	*/
	public static NodeDemo reverseLinkedList(NodeDemo head){
		//�ж������Ƿ�Ϊ��
		if(head.next == null || head.next.next == null){
			return head;
		}
		//������з�ת
		NodeDemo reverseHead = new NodeDemo(0,"");   //������ת�����ͷ�ڵ�
		NodeDemo current = head.next;  //ȡ��ԭ����ĵ�ǰ�ĵ�һ���ڵ�
		NodeDemo next = null;  //��һ���ڵ����һ���ڵ㣬����
		while(current != null){
			next = current.next;   //(a)�ؼ����裬��ȡ��ԭ����ĵ�һ���ڵ�ʱ������next��ԭ����ĵڶ����ڵ㣬��������õ�
			current.next = reverseHead.next;  //(b)���µĽڵ����ͷ�ڵ�͵�һ���ڵ�֮��
			reverseHead.next = current;       //���µ�ͷ�ڵ��next��ֵ������ָ�����Ľڵ�
			current = next;   //��ǰ�ڵ���ƣ��´�ѭ����(a)��ʱ���ͱ�ʾȡ��ԭ����ĵ�һ���ڵ㣨�ڶ����ڵ�ǰ�ƣ�������
		}
		head.next = reverseHead.next;  //��ʹ��ԭ����ͷ�ڵ���Ϊ��ת������Ľڵ�
		return head;
	}

	/*
	*9,�����ӡ����
	*˼·��
	*(1)����˼·��ʹ��8�з�ת������������ٴ�ӡ���������У���Ϊ���ƻ�ԭ����Ľṹ���´������ӡʱ�ʹ�ӡ���ˣ�
	*(2)����ջ���Ƚ���������ԣ�������Ž�ջ����д�ӡ��
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

//�ڵ���
class NodeDemo {
	//���ﲻ��set,get������Ϊ�˿�ֱ���ö���������ԣ�ʹ�������׶�
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
