package excepciones;

public class AppExceptions extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private Throwable cause;
	
	public String getMessage(){
		return message;
	}
	
	
	public Throwable getCause(){
		return cause;
	}
	
	
	public AppExceptions(String message, Throwable cause){
		
		this.message=message;
		this.cause=cause;
		
	}
	
	
	
	public AppExceptions(String message){
		
		this.message=message;
		
		
	}

}
