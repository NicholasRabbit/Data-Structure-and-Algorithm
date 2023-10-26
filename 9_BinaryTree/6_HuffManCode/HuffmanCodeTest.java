
import java.util.*;

/*
 * 霍夫曼编码的特点，每一个字符的编码都不会是另一个字符的前缀，这样当所有编码都紧挨着排在一起时，
 * 再进行解码时就不会有歧义。
 * 例：0101代表一个字符后，不会在出现010101代表另一个字符的情况，因为这样解码的话是解码0101呢，还是010101？
 * 此时就产生了歧义，导致无法解码或解码错误。
 *
 * 具体思路参照本节个人笔记。
 *
 * */
public class HuffmanCodeTest {

	public static void main(String[] args){
		//String sentence = "aaa bb hhh find her. She has a slim figure and a slender waist.";
		String sentence = "aaaa bb c dddd hhhhhhh jjk";
	 	buildTree(sentence);
	}


	public static void buildTree(String sentence){
		/*
		 * 一，统计句子中每个字符的个数，并放到 Map中。
		 * 1,首先把每个字符转换成byte，实际是转换成了字符对应的ASCII码。
		 * 2,以bye值为key，出现次数为value放到Map中。
		 *
		 * */
		byte[] bytes = sentence.getBytes();  //getBytes()无实参的话是使用系统默认的编码

		Map<Byte,Integer> countMap = CharMapTool.countChars(bytes);
		//查看统计结果。
		Set<Map.Entry<Byte,Integer>> entrySet = countMap.entrySet();
		Iterator<Map.Entry<Byte,Integer>> it = entrySet.iterator();
		while(it.hasNext()){
			Map.Entry<Byte,Integer> entry = it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		/*
		 * 二，创建霍夫曼树。
		 * */
		HuffmanTreeTool.getTree(countMap);
		
	
	}
}

