
/*
 * 解码霍夫曼编码的方法写在这里
 *
 * 思路：
 * 1，把接收到的霍夫曼加密后的byte数组还原为霍夫曼编码字符串;
 *    还原的时候注意，byte数组中最后一个有可能原来的字符串不是8位的，可能是“1001”这种样子。
 * 2，把原来的放ASCII字符编码和霍夫曼编码的Map的k v反转；
 * 3，逐个遍历编码字符串，在Map中找到匹配的值，解码为ASCII字符，因为霍夫曼编码没有歧义，所以找到第一个匹配的就可以。
 *
 * */
public class DecodeHuffmanCode {

	//把整个编码还原为字符串
	public static String decodeHuffman(byte[] codeBytes){
		
		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < codeBytes.length; i++){
			//当走到最后一个元素时，设置为true，不管最后那个byte是由8位，还是小于8位转过来的都给它特殊处理
			boolean isLast = (i == codeBytes.length);
			String str = byteToString(isLast, codeBytes[i]);
			builder.append(str);
		}

		return builder.toString();
		
	}


	//把单个字符还原为字符串
	public static String byteToString(boolean isLast, byte byteCode){

		/*
		 * 注意：原霍夫曼编码字符串最后可能剩下不到8个，
		 * 例如原来可能剩下“1001”(9),那么这里还原时要在前面补齐8位0
		 * */

		//这里获取到的是int类型长度的二进制字符串，共32位。需要截取最后的8位。
		String stringDecode = Integer.toBinaryString(byteCode);
		stringDecode = stringDecode.substring(stringDecode.length() - 8);

		return stringDecode;

	}


} 
