
import java.io.*;

/*
 * 测试使用霍夫曼编码压缩文件
 *
 * */

public class TestEncodeFile {

	public static void main(String[] args){

		FileTool ft = new FileTool();
		File file = new File(".\\sentence.txt");
		ft.getInputBytes(file);
		
	}
	
}


