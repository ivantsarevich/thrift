package efr.iv.igr.thriftlimit.exception;

import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConnectTransactionServiceExceptionHandler {
    @ExceptionHandler(ConnectTransactionServiceException.class)
    public ResponseEntity<ErrorMessage> handleConnectException(ConnectTransactionServiceException e) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(new ErrorMessage(e.getMessage()));
    }
}
