package com.example.common.exception;

/**
 * 请求异常的枚举类  后期所有code 这里维护
 */
public enum ExceptionEnum {

    SUCCESS("0","SUCCESS"),
    ERROR("1","ERROR"),
    PARAM_ERROR("-1","PARAM_ERROR"),
    RESULT_FAILED("-2","RESULT_FAILED"),
    INVALID_TOKEN("-3","INVALID_TOKEN"),
    SIGNATURE_ERROR("-4","SIGNATURE_ERROR"),
    NO_PRIVILEGE("99990001","NO_PRIVILEGE"),
    //邮箱格式错误
    MAILBOX_FORMAT_ERROR("-5","MAILBOX_FORMAT_ERROR"),
    VERIFICATION_CODE_IS_EMPTY("-6","VERIFICATION_CODE_IS_EMPTY"),
    VERIFICATION_CODE_IS_ERROR("-7","VERIFICATION_CODE_IS_ERROR"),
    PASSWORD_IS_EMPTY("-8","PASSWORD_IS_EMPTY"),
    MAILBOX_IS_EMPTY("-9","MAILBOX_IS_EMPTY"),
    /**
     * 用户相关1000
     */
    USER_ALREADY_EXISTS("1001","USER_ALREADY_EXISTS"),
    USER_NOT_EXIST("1002","USER_NOT_EXIST"),
    WRONG_PASSWORD("1003","WRONG_PASSWORD"),



    /**
     * 异常错误
     */
    RESULT_EXCEPTION("99999","RESULT_EXCEPTION"),
    CLOUD_EXCEPTION("199999","CLOUD_EXCEPTION"),

    ;

    private String code;
    private String message;

    ExceptionEnum(String code, String message){
        this.code = code;
        this.message = message;
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
}