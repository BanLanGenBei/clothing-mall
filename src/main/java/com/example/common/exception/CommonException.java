package com.example.common.exception;




/**
 * @author jimson
 */
public class CommonException extends RuntimeException {

  private static final long serialVersionUID = 1033482752147310350L;
  // 错误提示信息
  private String message;
  // 是否直接显示错误
  private Boolean showError = false;
  // code
  private String code;
  private String exceptionName;

  public CommonException(ExceptionEnum exceptionEnum) {
    super();
    this.message = exceptionEnum.getMessage();
    this.code = exceptionEnum.getCode();
  }



  @Override
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getExceptionName() {
    return exceptionName;
  }

  public void setExceptionName(String exceptionName) {
    this.exceptionName = exceptionName;
  }

  public Boolean getShowError() {
    return showError;
  }

  public void setShowError(Boolean showError) {
    this.showError = showError;
  }

}
