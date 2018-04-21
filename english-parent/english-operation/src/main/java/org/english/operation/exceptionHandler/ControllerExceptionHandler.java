package org.english.operation.exceptionHandler;



import org.english.operation.exception.BussinessException;
import org.english.operation.model.responseBean.ResultPOJO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



import com.github.pagehelper.util.StringUtil;

@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(BussinessException.class)
	public ResultPOJO modelExceptionHandler(BussinessException e) {
		
		if(!StringUtil.isEmpty(e.getMessage())) {
			e.printStackTrace();
			return new ResultPOJO(501, null, e.getMessage());
			
		}else {
			e.printStackTrace();
			return new ResultPOJO(501, null, "服务运行异常");
		}
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResultPOJO runtimeExceptionHandler(RuntimeException e) {
		
		e.printStackTrace();
		
		return new ResultPOJO(500, null, "服务器异常 "+e );
	}
	
	
}
