package org.english.operation.exception;

/**
 * 
 * @author 冯盼
 * @time 2018-04-11下午1:13:10
 * 
 */
public class BussinessException extends Exception{
	
	private static final long serialVersionUID = 1L;
	public BussinessException(String msg) {  
        super(msg);  
       
          
    } 
	public BussinessException(Throwable cause) {
        super(cause);
       
    }

    public BussinessException(String message, Throwable cause) {
        super(message, cause);
    }
	
}
