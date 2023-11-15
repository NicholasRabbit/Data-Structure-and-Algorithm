
import java.util.*;

public class CharMapAndByteTool{
	
	//使用霍夫曼编码加密原句子。生成字符串
	public static String createStringCode(byte[] contentBytes,Map<Byte,String> encodeMap){
		StringBuilder encodeBuilder = new StringBuilder();
		for(int i = 0; i < contentBytes.length; i++){
			//获取各个字符对应的霍夫曼编码，进行拼接
			String huffmamCode = encodeMap.get(contentBytes[i]);
			encodeBuilder.append(huffmamCode);
		}
		System.out.println("encode String==>" + encodeBuilder.toString());
		CharMapAndByteTool.printBytesString(encodeBuilder.toString());	//把字串八位一组隔开打印，方便验证结果
		return encodeBuilder.toString();
	}

	//把“0101...”这样的字符串转换为byte[]
	/* 注意：如果最后一个是0开头的字符串，如"001101(13)"，转换为byte数值是13，再转换为二进制就是"1101"，
	 * 前面的两个“0”就丢掉了，导致解码错误。需要采取方法避免这种情况
	 * */
	public static byte[] stringToByteArray(String codeString){
		//入参校验
		if(codeString == null || "".equals(codeString))
			return null;

		//1，计算字符串总共有几个byte长度
		//下面得到结果是上取整，如果除不尽还要加1才是正确的长度。
		int len = codeString.length();
		int size = 0;
		if(len % 8 == 0){
			size = len / 8;
		}else{
			size = len / 8 + 1;
		}
		//还有一种直接的算法
		//int size = (countChars.length + 7) / 8;

		//2，由上步得得byte数组的长度
		byte[] arr = new byte[size]; 
		//byte[] arr = new byte[size + 1];  //这里不写size而写size + 1，因为数组最后一个要存标记，来表示最后一节编码是否0开头。

		//3，按照每8位一节，截断原字符串
		//下面对arr数组循环添加，实际循环字符串也可以。
		int index = 0;  //字符串的初始下标。
		for(int i = 0; i < arr.length; i++){
			String str = "";
			//注意这里是 index + 8, 如果index + 8超过其总长，说明最后剩的不够8位，则有多少就截断多少。
			if(index + 8 > codeString.length()){
				str = codeString.substring(index);
			}else{
				//未超出长度的情况,或者最后刚好剩下8位。例{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15} 0+8=8,8+8=16(=length())
				str = codeString.substring(index,index + 8);  //包前不包后
			}

			//把String转化为byte类型的二进制,放到数组中。
			/*
			 * 下行报错！！这里不可用Byte类型来转换二进制，因为byte范围(-128~127)，有的编码是8各1是超限的。
			 * 因此需要强制转换，强转不会造成误差，因为255(1111 1111)强转后得到-1，而在计算机底层它就是代表-1，
			 * */
			//byte b = Byte.parseByte(str,2);   

			/* 方法错误， 重点：处理最后一节编码是“0”开头的情况。
			 * 1,如果是最后一个，而且以0开头，就把开头的0换成1，转换为数值；
			 * 2,再向数组最后一个位置添加一个元素，不参与编码，只做标记，“0”表示最后一节编码开头0，需要替换回来，“1”表示最后一节编码开头是1，不用替换。
			 * */
			/*
			if(index + 8 >= codeString.length()){
				if(str.startsWith("0")){
					str.replaceFirst("0","1");  //替换第一个“0”
					arr[i + 1] = 0;
				}else{
					arr[i + 1] = 1;
				}	
				i ++;  //这里自加1，数组最后一位已添加，不用再循环添加了。
			}
			*/

			byte b = (byte)Integer.parseInt(str,2);
			arr[i] = b;

			//截断之后，下标后移8位。
			index += 8;

		}

		return arr;
	}



	//把字符串每8个隔开打印
	public static boolean printBytesString(String str){
		for(int i = 0; i < str.length(); i += 8){
			String subStr = "";
			if(i + 8 > str.length()){
				subStr = str.substring(i);
			}else{
				subStr = str.substring(i,i + 8);
			}
			System.out.print(subStr + ", ");

		}
		System.out.println();

		return true;
	
	}

	//遍历权值Map
	public static void showMap(Map<Byte,Integer> weightMap){
		System.out.println("=========weightMap============");
		Set<Map.Entry<Byte,Integer>> entrySet = weightMap.entrySet();
		Iterator<Map.Entry<Byte,Integer>> it = entrySet.iterator();
		while(it.hasNext()){
			Map.Entry<Byte,Integer> entry = it.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

	//遍历编码后的Map
	public static void showEncodeMap(Map<Byte,String> encodeMap){
		System.out.println("=========encodeMap============");
		Set<Map.Entry<Byte,String>> huffmanEntry = encodeMap.entrySet();
		for(Map.Entry<Byte,String> entry : huffmanEntry){
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}

}

