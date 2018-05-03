package cn.ech0.examinationsystem.handler;

import cn.ech0.examinationsystem.enums.ResponseCodeEnum;
import cn.ech0.examinationsystem.response.BaseResponse;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/03/2018 09:20 PM
 */
@ControllerAdvice
public class MIssRequestParamExceptionHandler {

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public BaseResponse handleServiceException(MissingServletRequestParameterException e){
        return new BaseResponse(ResponseCodeEnum.ILLEGAL_ARGUMENT.getCode(),"请求参数错误，请检查！",e.getMessage());
    }
}
