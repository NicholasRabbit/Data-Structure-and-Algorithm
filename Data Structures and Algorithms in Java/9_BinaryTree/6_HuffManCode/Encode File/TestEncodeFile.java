
import java.io.*;

/*
 * 测试使用霍夫曼编码压缩文件
 * 因为组成文件的最小单位就是一个byte，所以不用担心上节出现的霍夫曼编码字符串最后0丢失的情况。
 *
 * 压缩文件思路：
 * 1，
 * */

public class TestEncodeFile {

	public static void main(String[] args){

		FileTool ft = new FileTool();

		//压缩文件
		File srcFile = new File("sentence.txt");
		File destFile = new File("zip.rar");   //注意这里使用 rar 后缀并不能用解压工具打开，还得用IO流，因为加密方式不同。
		ft.compress(srcFile,destFile);

		//解压文件
		File decFile = new File("zip.rar");
		File reFile = new File("ss.txt");
		ft.decompress(decFile,reFile);
		
	}
	
}


