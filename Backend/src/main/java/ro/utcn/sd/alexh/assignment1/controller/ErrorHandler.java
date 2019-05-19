package ro.utcn.sd.alexh.assignment1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.utcn.sd.alexh.assignment1.dto.ErrorDTO;
import ro.utcn.sd.alexh.assignment1.exception.IllegalUserOperationException;

@Component
@RestControllerAdvice
public class ErrorHandler {

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(IllegalUserOperationException.class)
//    public ErrorDTO handlerIllegalUserOperationException(IllegalUserOperationException e) {
//        return new ErrorDTO("Illegal User Operation");
//    }
}
