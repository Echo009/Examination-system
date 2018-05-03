package cn.ech0.examinationsystem.handler;

import cn.ech0.examinationsystem.enums.ResponseCodeEnum;
import cn.ech0.examinationsystem.exception.BaseServerException;
import cn.ech0.examinationsystem.response.BaseResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 03:17 PM
 */
//@ControllerAdvice
public class BindExceptionHandler {
    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public BaseResponse handleServiceException(BindException e){
        return new BaseResponse(ResponseCodeEnum.ILLEGAL_ARGUMENT.getCode(),"请求参数类型有误，请注意格式！",null);
    }
}
