package efr.iv.igr.thriftlimit.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LimitExceptionHandler {
    @ExceptionHandler(InvalidLimitAmountException.class)
    public ResponseEntity<ErrorMessage> handleInvalidLimitException(InvalidLimitAmountException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage()));
    }
}
