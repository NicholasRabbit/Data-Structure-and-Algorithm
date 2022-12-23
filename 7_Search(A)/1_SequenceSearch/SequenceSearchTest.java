
/*
* 顺序查找算法，是一种线性查找算法。
* 指从前向后遍历数组查找，找到则返回其下标，找不到则返回-1。
*/
public class SequenceSearchTest {

	public static void main(String[] args){
		int[] array = new int[]{9,6,1,2,3,};
		
		SequenceSearchTest sst = new SequenceSearchTest();

		int index = sst.sequenceSearch(array,3);
		System.out.println("index==>" + index);
		
		index = sst.sequenceSearch(array,25);
		System.out.println("index==>" + index);

	}

	//顺序查找算法
	public int sequenceSearch(int[] array,int value){
		
		for(int i = 0; i < array.length; i++){
			if(array[i] == value){
				return i;
			}
		}
		return -1;
	}

}