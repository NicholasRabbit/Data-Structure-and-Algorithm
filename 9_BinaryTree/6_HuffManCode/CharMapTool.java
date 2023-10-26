
import java.util.*;

public class CharMapTool{

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
}
