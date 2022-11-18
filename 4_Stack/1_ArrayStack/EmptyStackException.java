//个人自定义栈空异常
public class EmptyStackException extends RuntimeException {

	public EmptyStackException(){
	
	}
	public EmptyStackException(String message){
		super(message);
	}

}