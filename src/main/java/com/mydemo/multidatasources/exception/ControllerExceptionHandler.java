package com.mydemo.multidatasources.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author FCG
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

//    @ExceptionHandler(ServiceException.class)
//    public ResponseBean handleServiceException(ServiceException ex) {
//        logger.info(ex.getMessage(),ex);
//        return new ResponseBean(ex, ex.getExCode());
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseBean handleIllegalArgumentException(IllegalArgumentException ex) {
//        logger.info(ex.getMessage(),ex);
//        return new ResponseBean(ex, "400");
//    }
//
//    @ExceptionHandler(Throwable.class)
//    public ResponseBean handleException(Throwable ex) {
//        logger.info(ex.getMessage(), ex);
//        return new ResponseBean(ex, "500");
//    }
}


















