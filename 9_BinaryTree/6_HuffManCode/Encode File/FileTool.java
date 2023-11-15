
import java.io.*;
import java.nio.charset.Charset;

/*
 * A util to deal with file.
 * */
public class FileTool {

	//Get the bytes of the file which is encoded.
	public boolean getInputBytes(File file){

		FileInputStream in = null;

		try{
			in = new FileInputStream(file); 
			byte[] bytes = new byte[10];

			int count = 0;
			while((count = in.read(bytes)) != -1){
				//System.out.println(new String(bytes,0,count,Charset.forName("UTF-8")));
				for(int i = 0; i < bytes.length; i++){
					System.out.println(Integer.toBinaryString(bytes[i]));
				}
				break;
			}
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		} finally{
			if(in != null){
				try{
					in.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			
			}
		}

		return true;
	}

}
