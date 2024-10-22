package com.mycompany.propertymanagement.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger logger= LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorModel>> handleFieldValidation(MethodArgumentNotValidException manv) {

        List<ErrorModel> errorModelList = new ArrayList<>();
        List<FieldError> fieldErrorList = manv.getBindingResult().getFieldErrors();

        for (FieldError fe : fieldErrorList) {
            logger.debug("Inside field validation: {} - {}",fe.getField(),fe.getDefaultMessage());
            logger.info("Inside field validation: {} - {}",fe.getField(),fe.getDefaultMessage());
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(fe.getField());            // Set field name as code
            errorModel.setMessage(fe.getDefaultMessage()); // Set validation message as message

            // Add the populated errorModel to the list
            errorModelList.add(errorModel);
        }

        // Return the list of error models with a BAD_REQUEST status
        return new ResponseEntity<>(errorModelList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<List<ErrorModel>> handleBusinessException(BusinessException bex){

        System.out.println("Inside business exception");
        for(ErrorModel em : bex.getErrors()) {
            logger.debug("Inside field validation-level-debug: {} - {}", em.getCode(), em.getMessage());
            logger.info("Inside field validation-level-info: {} - {}", em.getCode(), em.getMessage());
            logger.warn("Inside field validation-level-warn: {} - {}", em.getCode(), em.getMessage());
            logger.error("Inside field validation-level-error: {} - {}", em.getCode(), em.getMessage());
        }
        return new ResponseEntity<List<ErrorModel>>(bex.getErrors(), HttpStatus.BAD_REQUEST);
    }

}
