package efr.iv.igr.thriftlimit.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;

@RestControllerAdvice
public class ConnectExceptionHandler {
    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ErrorMessage> handleConnectException(ConnectException e) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorMessage("Sorry, service unavailable ;C"));
    }
}
