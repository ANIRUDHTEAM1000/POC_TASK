package com.poc.demo.exception;

import com.poc.demo.exception.userdefined.EmptyFieldsException;
import com.poc.demo.exception.userdefined.NoSuchEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(EmptyFieldsException.class)
    public ResponseEntity<String> handleEmptyFielsException(){
        return new ResponseEntity<>("Fields Cannot be empty",HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchEmployeeException.class)
    public ResponseEntity<?> handleNoSuchElementException(){
        return new ResponseEntity<>("The Employee is Not Found",HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatchException(){
        return new ResponseEntity<>("Expected value of one type, But Got value of other type.",HttpStatus.BAD_REQUEST);
    }

}
