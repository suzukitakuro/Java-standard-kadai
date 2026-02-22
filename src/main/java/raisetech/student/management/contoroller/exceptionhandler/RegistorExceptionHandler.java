package raisetech.student.management.contoroller.exceptionhandler;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import raisetech.student.management.exception.RegistorTestException;
import raisetech.student.management.exception.TestException;
import raisetech.student.management.exception.TestException;

@RestControllerAdvice
public class RegistorExceptionHandler {

    @ExceptionHandler(RegistorTestException.class)
    public ResponseEntity<String> handleRegistorException(RegistorTestException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }
}
