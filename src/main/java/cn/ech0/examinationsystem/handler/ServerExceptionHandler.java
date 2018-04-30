package cn.ech0.examinationsystem.handler;


import cn.ech0.examinationsystem.exception.BaseServerException;
import cn.ech0.examinationsystem.response.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/20/2018 12:24 AM
 */
@ControllerAdvice
public class ServerExceptionHandler {

    @ExceptionHandler(value = BaseServerException.class)
    @ResponseBody
    public BaseResponse handleServiceException(BaseServerException e){
        return new BaseResponse(e.getCode(),e.getMessage(),null);
    }
}
