
import java.util.*;

/*
 * (1)霍夫曼编码的特点，每一个字符的编码都不会是另一个字符的前缀，这样当所有编码都紧挨着排在一起时，
 * 再进行解码时就不会有歧义。
 * 例：0101代表一个字符后，不会在出现010101代表另一个字符的情况，因为这样解码的话是解码0101呢，还是010101？
 * 此时就产生了歧义，导致无法解码或解码错误。
 *
 * (2)注意：CLI手动编译时用 *.java，不要只编译HuffmanCodeTest.java，有的类无法自动编译。
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
		byte[] contentBytes = sentence.getBytes();  //getBytes()无实参的话是使用系统默认的编码

		Map<Byte,Integer> countMap = CharMapAndByteTool.countChars(contentBytes);
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
		TreeNode root = HuffmanTreeTool.getTree(countMap);

		System.out.println();

		/*
		 * 三，生成每个字符及其对应的霍夫曼编码，放到Map中。
		 * 向左一步记作“0”，向右一步记作“1”
		 * */
		StringBuilder builder = new StringBuilder();
		Map<Byte,String> encodeMap = HuffmanTreeTool.appendCode(root,"",builder);
		Set<Map.Entry<Byte,String>> huffmanEntry = encodeMap.entrySet();
		for(Map.Entry<Byte,String> entry : huffmanEntry){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}


		/*
		 * 四，把原文加密成霍夫曼编码的字符串。
		 * 生成的霍夫曼编码字符串，实际长度可能比原来的句子还长，
		 * 因此还需要处理，把“0101”这样的字符串按照byte类型的8位截开，转为byte类型的数组。
		 * */
		StringBuilder encodeBuilder = new StringBuilder();
		for(int i = 0; i < contentBytes.length; i++){
			encodeBuilder.append(encodeMap.get(contentBytes[i]));
		}
		System.out.println("encode String==>" + encodeBuilder.toString());
		byte[] encodeBytes = CharMapAndByteTool.stringToByteArray(encodeBuilder.toString());
		System.out.println("encode bytes: " + Arrays.toString(encodeBytes));

		//对比压缩前后长度：
		System.out.println("contentBytes' length==>" + contentBytes.length);   //原长：26
		System.out.println("encode bytes' length==>" + encodeBytes.length);    //压缩后：9，压缩比例 17/16=0.65
	
	}
}

