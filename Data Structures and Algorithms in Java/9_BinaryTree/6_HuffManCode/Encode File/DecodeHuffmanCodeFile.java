
//解码文件
//此处写法与第一节解码ASCII的不一样
public class DecodeHuffmanCodeFile {

	//把整个编码还原为字符串
	public static String decodeHuffman(byte[] codeBytes){

		StringBuilder builder = new StringBuilder();

		for(int i = 0; i < codeBytes.length; i++){
			String str = byteToString(isMiddle, codeBytes[i]);
			builder.append(str);
		}

		return builder.toString();

	}

	//解码文件是，因为文件最小单位就是byte，不用考虑最后不够8为的情况
	//把单个使用byte类型数值表示的字符还原为字符串
	public static String byteToString(boolean isMiddle, byte byteCode){

		int temp = byteCode;
		if(isMiddle){
			temp = 256 | temp;  //例：(256)1,0000,0000 | 111,1111(127) = 1,0111,1111。这样Integer.toBinaryString()方法截取的就是"1,0111,1111"，然后只用后8位即可。
		}

		//这里获取到的是int类型长度的二进制字符串，共32位。需要截取最后的8位。
		String stringDecode = Integer.toBinaryString(temp);
		//都是8位，都要截取
		return stringDecode.substring(stringDecode.length() - 8);


	}


} 
