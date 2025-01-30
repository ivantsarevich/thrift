package efr.iv.igr.thriftbank.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidAmountExceptionHandler {
    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<ErrorMessage> invalidAmountExceptionHandle(InvalidAmountException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage()));
    }
}
