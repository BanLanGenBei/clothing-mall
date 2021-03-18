package com.example.common.reponse;

import com.example.common.exception.ExceptionEnum;

/**
 * 返回给前端的数据
 * @param <T>
 */

public class BaseResult<T> {


	public void setErrCodeAndMsg(ExceptionEnum exceptionEnum) {
		String code = exceptionEnum.getCode();
		this.isError = !"0".equals(code);
		this.code = code;
		this.message = exceptionEnum.getMessage();

	}
	public BaseResult() {}

	private Boolean isError;
	private T data;
	private String code;
	private String message;

	public Boolean getIsError() {
		return isError;
	}

	public void setIsError(Boolean isError) {
		this.isError = isError;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BaseResult(Boolean isError, T data, String code, String message) {
		this.isError = isError;
		this.data = data;
		this.code = code;
		this.message = message;
	}

	private static<T> BaseResult<T> getInstance(Boolean isError, T data, String code, String message){
		return new BaseResult<T>(isError, data, code, message);
	}


	public static<T> BaseResult<T> paramError(){
		return getInstance(true, null, ExceptionEnum.PARAM_ERROR.getCode(), ExceptionEnum.PARAM_ERROR.getMessage());
	}

	public static<T> BaseResult<T> exceptionError(){
		return getInstance(true, null, ExceptionEnum.RESULT_EXCEPTION.getCode(), ExceptionEnum.RESULT_EXCEPTION.getMessage());
	}

	public static<T> BaseResult<T> success(){
		return getInstance(false, null, ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMessage());
	}

	public static<T> BaseResult<T> success(T data){
		return getInstance(false, data, ExceptionEnum.SUCCESS.getCode(), ExceptionEnum.SUCCESS.getMessage());
	}

	public static<T> BaseResult<T> error(){
		return getInstance(true, null, ExceptionEnum.ERROR.getCode(), ExceptionEnum.ERROR.getMessage());
	}

	public static <T> BaseResult<T> error(String code, String message) {
		return getInstance(true, null, code, message);
	}
}
