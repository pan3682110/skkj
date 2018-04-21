package org.english.operation.model.responseBean;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResultPOJO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "状态码 200:业务执行成功 501:业务执行失败   500:服务器异常")
    private int code;
    
	@ApiModelProperty(value = "返回数据")
    private Object data;

	@ApiModelProperty(value = "错误消息,除200以外的系统反馈都在此处")
    private String errorMsg;
	
    public ResultPOJO(int code, Object data, String errorMsg) {
        this.code = code;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    /**
     * 业务执行失败
     */
    public transient static final int OPERATION_ACTION_FAILURE = 501;

    /**
     * 业务执行成功
     */
    public transient static final int OPERATION_SUCCESS = 200;

    
    /**
     *  服务器异常
     */
    public transient static final int SERVER_ERROR = 500;



}