package helloException.exceptionHandler.advice;

import helloException.exception.exception.UserException;
import helloException.exceptionHandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler], ", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler], ",e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    // 기본 에러 이걸 상속받은 모든 예외들을 다처리가능!
    // 정의된 예외가 없으면 여기로 넘어옴!
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler], ",e);
        return new ErrorResult("EX", "내부 오류");
    }

}
