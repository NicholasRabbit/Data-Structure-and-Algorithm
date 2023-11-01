
import java.util.*;

public class CharMapAndByteTool{

	//计算字符在句子中出现的次数，放到Map中
	public static Map<Byte,Integer> countChars(byte[] byteArray){
		Map<Byte,Integer> countMap = new HashMap<>();
		int count = 0;
		for(int i = 0; i < byteArray.length; i++){
			//有的话，次数加1
			if(countMap.containsKey(byteArray[i])){
				count = countMap.get(byteArray[i]);
				count ++;
				countMap.put(byteArray[i],count);
			}else{
				//第一次放入Map
				count = 1;
				countMap.put(byteArray[i],count);
			}

		}
		return countMap;
	}


	//把“0101...”这样的字符串转换为byte[]
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

		//3，按照每8位一节，截断原字符串
		int index = 0;  //字符串的初始下标。
		for(int i = 0; i < arr.length; i++){
			String str = "";
			//如果字符串下标超过其总长，说明最后剩的不够8位，则有多少就截断多少。
			if(index > codeString.length()){
				str = codeString.substring(0);
			}else{
				//未超出长度的情况。
				str = codeString.substring(index,index + 8);  //包前不包后
			}

			//把String转化为byte类型的二进制,放到数组中。
			/*
			 * 下行报错！！这里不可用Byte类型来转换二进制，因为byte范围(-128~127)，有的编码是8各1是超限的。
			 * 用byte强转不会产生误差，因为地计算机底层用加法表示减法，8个1是255, byte范围内用128~255表示-128~-1
			 * */
			//byte b = Byte.parseByte(str,2);   
			byte b = (byte)Integer.parseInt(str,2);
			arr[i] = b;

			//截断之后，下标后移8位。
			index += 8;

		}

		return arr;
	}


}

