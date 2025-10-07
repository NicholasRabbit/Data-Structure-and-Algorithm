
import java.io.*;
import java.util.*;
import java.nio.charset.Charset;

/*
 * A util to deal with file.
 * */
public class FileTool {

	//compress file
	public boolean compress(File srcFile,File destFile){

		FileInputStream in = null;
		FileOutputStream out = null;
		ObjectOutputStream oos = null;
		try{
			//获取输入流
			in = new FileInputStream(srcFile); 
			//设置数组的容量为文件的容量，把全部文件放到数组中。
			byte[] bytes = new byte[in.available()];  

			//读入输入流
			in.read(bytes);
			//把文件进行压缩
			byte[] encodeBytes = HuffmanCodeTool.getEncodeBytes(bytes);

			//输出压缩后的byte[]
			out = new FileOutputStream(destFile);
			//使用对象输出流
			oos = new ObjectOutputStream(out);

			//1,先把压缩后的byte[]输出
			oos.writeObject(encodeBytes);
			//2,把对应的编码也得输出，将来解码用
			oos.writeObject(HuffmanCodeTool.getHuffmanCodeMap());
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		} finally{

			//从前到后，从里到外逐个关闭流
			if(in != null){
				try{
					in.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			}

			if(out != null){
				try{
					out.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			}

			if(oos!= null){
				try{
					oos.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			}

		}

		return true;
	}


	//decompress
	//Caution: the srcFile is the compressed file.
	public boolean decompress(File srcFile,File destFile){

		FileInputStream in = null;	
		FileOutputStream out = null;
		ObjectInputStream ois = null;

		try{
			//获取输入流
			in = new FileInputStream(srcFile); 
			
			ois = new ObjectInputStream(in);

			//读取对象，顺序跟写入时的一样
			byte[] encodeBytes = (byte[])ois.readObject();
			Map<Byte,String> encodeMap = (Map<Byte,String>)ois.readObject();
			
					

			
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		} finally{

			//从前到后，从里到外逐个关闭流
			if(in != null){
				try{
					in.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			}

			if(out != null){
				try{
					out.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			}

			if(ois!= null){
				try{
					ois.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			}

	  }

	  return true;

	}

}
