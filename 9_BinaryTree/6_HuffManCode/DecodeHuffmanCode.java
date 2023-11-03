
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
			//如果不是最后一个元素，则设置位true，下面要补0
			boolean isMiddle = (i != codeBytes.length);
			String str = byteToString(isMiddle, codeBytes[i]);
			builder.append(str);
		}

		return builder.toString();
		
	}


	//把单个字符还原为字符串
	public static String byteToString(boolean isMiddle, byte byteCode){

		/*
		 * 注意：中间的byte如果大于等于0的话，要把二进制字符串前面的0不回来。具体参照TestHuffmanCode.java解码处的个人解释。
		 * 如果不是最后一个元素，不管是不是正数，都跟1,0000,0000(256)按位与(|)，即使是负数，其后面8位按位与后还是不变，
		 * 而正数则可以把前面的0补齐，因为Integer.toBinaryString(..)方法对例如 1111,1111(127)这样的正数返回的二进制字符串不会补齐前面的0，来凑够8位。
		 * 如果是最后一位元素，即使是正数，toBinaryString()解码不够8位，项上面的127，那么也不用补0，因为原来就就是
		 * */
		int temp = byteCode;
		if(isMiddle){
			temp = 256 | temp;  //例：(256)1,0000,0000 | 111,1111(127) = 1,0111,1111。这样Integer.toBinaryString()方法截取的就是"1,0111,1111"，然后只用后8位即可。
		}

		//这里获取到的是int类型长度的二进制字符串，共32位。需要截取最后的8位。
		String stringDecode = Integer.toBinaryString(byteCode);
		//数组中间的元素，非最后的都要截取字符串
		if(isMiddle){
			return stringDecode.substring(stringDecode.length() - 8);
		}else{
			//最后一个元素即使是正数，例如：111,1111(127)这样的7位，toBinaryString(127)得到的也是原来字符串就是7位，直接返回。
			return stringDecode;

		}


	}


} 
