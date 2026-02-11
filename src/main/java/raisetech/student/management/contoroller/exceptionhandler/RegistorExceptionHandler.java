package raisetech.student.management.contoroller.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import raisetech.student.management.exception.TestException;
import raisetech.student.management.exception.TestException;

@RestControllerAdvice
public class RegistorExceptionHandler {

    @ExceptionHandler(TestException.class)
    public ResponseEntity<String> handleRegistorException(TestException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

    }
}
