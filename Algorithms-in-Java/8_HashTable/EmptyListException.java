
//链表空异常，复习异常的写法。
public class EmptyListException extends RuntimeException {

	public EmptyListException(){
	
	}

	public EmptyListException(String message){
		super(message);
	}


}