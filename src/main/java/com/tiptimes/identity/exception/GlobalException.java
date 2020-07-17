package com.tiptimes.identity.exception;

import com.tiptimes.identity.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@RestControllerAdvice
public class GlobalException {

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseResult commonBusinessExceptionHandler(BusinessException e) {
        //log.error(e.getMessage());
        //log.error(exceptionStackTrace2String(e));
        return ResponseResult.error(e.getCode(), e.getMessage());
    }

    /**
     * 异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseResult exceptionHandler(Exception e) {
        log.error(e.getMessage());
        log.error(exceptionStackTrace2String(e));
        return ResponseResult.error("未知异常");
    }

    /**
     * 运行时异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseResult runtimeExceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        log.error(exceptionStackTrace2String(e));
        return ResponseResult.error("系统运行异常");
    }

    /**
     * 空指针异常
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseResult nullPointerExceptionHandler(NullPointerException e) {
        log.error(e.getMessage());
        log.error(exceptionStackTrace2String(e));
        return ResponseResult.error("系统空指针异常");
    }


    /*----- REQUEST ERROR -----*/

    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseResult requestNotReadable(HttpMessageNotReadableException e){
        log.error(e.getMessage());
        return ResponseResult.error("参数格式错误(缺少分隔符或结束标签)");
    }

    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseResult requestTypeMismatch(TypeMismatchException e){
        log.error(e.getMessage());
        return ResponseResult.error("参数类型不匹配");
    }

    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseResult requestMissingServletRequest(MissingServletRequestParameterException e){
        log.error(e.getMessage());
        log.error(exceptionStackTrace2String(e));
        return ResponseResult.error("缺少请求参数");
    }

    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ResponseResult request405(HttpRequestMethodNotSupportedException e){
        log.error(e.getMessage());
        return ResponseResult.error("不支持该请求方式");
    }

    private String exceptionStackTrace2String(Exception e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
