package com.cloud.common.core.error;

import com.cloud.common.core.result.AjaxResult;
import com.cloud.common.core.result.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartException;
import java.util.List;

/**
 * 开发公司：XX公司
 * 版权：XX公司
 * <p>
 * ExceptionHandler
 *
 * @author 刘志强
 * @created Create Time: 2019/1/19
 */
@RestController
@RestControllerAdvice
@Slf4j
public class AdviceController {
    /**
     * 方法参数无效异常处理异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @CrossOrigin
    public AjaxResult handlerUserNotExistException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        StringBuilder eorrs = new StringBuilder("|");
        List<FieldError> eorrList = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : eorrList) {
            eorrs.append(fieldError.getDefaultMessage()).append("|");
        }
        return AjaxResult.error(HttpStatus.ERROR,eorrs.toString());
    }

    @ExceptionHandler(BindException.class)
    @CrossOrigin
    public AjaxResult bindException(BindException ex) {
        log.error(ex.getMessage());
        StringBuilder eorrs = new StringBuilder("|");
        List<FieldError> eorrList = ex.getBindingResult().getFieldErrors();
        for (FieldError fieldError : eorrList) {
            eorrs.append(fieldError.getDefaultMessage()).append("|");
        }
        return AjaxResult.error(HttpStatus.ERROR,eorrs.toString());
    }

    /**
     * 文件流异常
     * @param ex
     * @return
     */
    @ExceptionHandler(MultipartException.class)
    @CrossOrigin
    public AjaxResult multipartException(MultipartException ex) {
        log.error(ex.getMessage());
        return AjaxResult.error(HttpStatus.ERROR,ex.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @CrossOrigin
    public AjaxResult nullPointerException(NullPointerException ex) {
        log.error(ex.getMessage());
        return AjaxResult.error(HttpStatus.ERROR,"空指针异常");
    }

}