package com.example.common.exception;



import com.example.common.reponse.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * @ClassName GlobalExceptionHandler
 * @Description //全局异常处理
 * @Author yuyinghua@ovopark.com
 * @Date 2019/6/27 16:29
 * @Version 1.0
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 已知异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value=CommonException.class)
    public BaseResult errorHandler3 (CommonException ex) {
        String code = ex.getCode();
        if (code == null){
            return BaseResult.error("1",ex.getMessage());
        }else {
            if (StringUtils.equals(ex.getCode(),"1")){
                log.error(ex.toString());
            }
            return BaseResult.error(code,ex.getMessage());
        }
    }


    /**
     * 请求方式错误
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value= {HttpMediaTypeNotSupportedException.class,
            HttpRequestMethodNotSupportedException.class
    })
    public BaseResult errorHandler5 (Exception ex) {
        return BaseResult.error("-1","PARAM_ERROR");
    }



    @ResponseBody
    @ExceptionHandler( value = {
            BindException.class,
            MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
    })
    public BaseResult errorHandler6 (Exception ex) {
        return BaseResult.error("-1","PARAM_ERROR");
    }




    /**
     * 全局获取异常
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value= Exception.class)
    public BaseResult errorHandler2 (Exception ex) {
        ex.printStackTrace();
        return BaseResult.error("1","ERROR");
    }
}