
import java.util.*;

/*
* 霍夫曼树范例：
* 相关名词解释：
* 路径：从一个节点走到另一个节点走过的路径。
* 路径长度：路径上经过的分支数目。
* 结点的权：给树的节点赋予的一个有意义的实数，这个数称为节点的权。
* 带权路径长度: 路径长度和节点权的乘积。
* 树的带权路径长度(WPL)：树中所有叶子节点的带权路径长度之和。
* 
*/

public class HuffManTreeTest {

	public static void main(String[] args){
		
		//1，原始数组进行排序
		int[] arr = {12,6,5,27,9,8,3,65};
		//int[] arr = { 13, 7, 8, 3, 29, 6, 1 };
		HeapSortUtil.autoAdjust(arr);
		HeapSortUtil.heatSort(arr);
		
		System.out.println("arr==>" + Arrays.toString(arr));
		//结果： [3, 5, 6, 8, 9, 12, 27, 65],注意3+5=8，有可能和原有的8互换位置，详见个人图解。

		//一，手动分步的方式
		createHuffManTree(arr);

		//二，自动循环的方式
		autoCreate(arr);

		
	}

	/*
	* 创建霍夫曼树步骤：
	* 1，把每个节点看成最小的二叉树，取出头两个元素作为左右子节点，
	*   它们权值的和作为新的父节点的权值；
	* 2，删除这两个节点，再把新的父节点放回原数组和第一个元素比较，小的作为左节点，
	*   按照步骤1的方式组成新的二叉树；
	* 3，重复上述两个步骤，直到最后，一般最后数组中剩下一个元素(最后求得权值之后添加进去的)，即根节点。   
	*/
	public static void createHuffManTree(int[] arr){
		
		
		List<NodeDemo> nodeList = new ArrayList<>();

		//把数组转换成集合，方便操作，因为原数组已排好序，所以集合不用排序了，
		//Comparable用来排序新的集合(即删除头两个节点，添加新的父节点时)。
		for(int i = 0; i < arr.length; i++){
			nodeList.add(new NodeDemo(arr[i]));
		}
		
		System.out.println("nodeList==>" + nodeList);
		
		//一，先手动分步删除头两个节点，实际循环也是反复删除头两个节点，直到数组中所有的比较完毕

		//(1)创建父节点，并设置左右子节点
		NodeDemo left = nodeList.get(0);
		NodeDemo right = nodeList.get(1);
		NodeDemo parentNode = new NodeDemo(left.weight + right.weight);
		parentNode.left = left;
		parentNode.right = right;

		//(2)删除头两个节点
		/*注意这里不要写作下方的删除下标的形式，0下标的删除后，第二个下标就变成0了。错误。
		nodeList.remove(0);
		nodeList.remove(1);
		*/
		nodeList.remove(left);
		nodeList.remove(right);
		
		//(3)添加父节点到新的集合，并排序
		nodeList.add(parentNode);
		Collections.sort(nodeList);
		System.out.println("new nodeList==>" + nodeList);

		//重复以上步骤。。。。

	
	}

	//二，循环的方式创建
	public static void autoCreate(int[] arr){

		List<NodeDemo> nodeList = new ArrayList<>();

		for(int i = 0; i < arr.length; i++){
			nodeList.add(new NodeDemo(arr[i]));
		}

		//循环,经过验证，有n个元素的数组比较n-1次，所以这里是 j < arr.length -1;
		//老师源码是while(nodeList.size() > 1),根据的是数组最后只剩一个元素说明比较完毕。
		for(int j = 0; j < arr.length - 1; j++){

			//先排序
			Collections.sort(nodeList);

			NodeDemo left = nodeList.get(0);
			NodeDemo right = nodeList.get(1);
			NodeDemo parentNode = new NodeDemo(left.weight + right.weight);
			parentNode.left = left;
			parentNode.right = right;

			//(2)删除头两个节点
			/*注意这里不要写作下方的删除下标的形式，0下标的删除后，第二个下标就变成0了。错误。
			  nodeList.remove(0);
			  nodeList.remove(1);
			  */
			nodeList.remove(left);
			nodeList.remove(right);

			//(3)添加父节点到新的集合，并排序
			nodeList.add(parentNode);
		

		}
		
		System.out.println("autoCreate==>" + nodeList);

		//前序遍历验证下
		preShow(nodeList.get(0));

	}

	public static void preShow(NodeDemo node){
		if(node == null)
			return;
		node.preShow();
	}
	




}



//二叉树节点对象，继承Comparable接口，方便比较。
class NodeDemo implements Comparable<NodeDemo> {

	//权重，就是节点的值，可能以后别的复杂的功能有不同的解释。
	int weight;
	String name;

	//左右节点
	NodeDemo left;
	NodeDemo right;

	public NodeDemo(){

	}
	//Args Constructor
	public NodeDemo(int weight){
		this.weight = weight;
	}

	//二叉树前序遍历
	public void preShow(){
		//打印父节点
		System.out.println("node==>" + this);
		//左节点
		if(this.left != null){
			this.left.preShow();
		}
		//右节点
		if(this.right != null){
			this.right.preShow();
		}


	}



	public int compareTo(NodeDemo node){
		return this.weight - node.weight;
	}

	public String toString(){
		return "{" +  "weight:" + this.weight + "}";
	}


}
