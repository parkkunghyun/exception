package helloException.exception.api;

import helloException.exception.exception.UserException;
import helloException.exceptionHandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionControllerV2 {

    /*
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

     */

    @GetMapping("/api2/members/{id}")
    public MemberDTO getMember(@PathVariable("id") String id) {
        if(id.equals("ex")) {
            // html 페이지가 나와버림! -> json 오류 처리를 안했을때!
            //
            throw  new RuntimeException("잘못된 사용자");
        }
        if(id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력값");
        }
        if(id.equals("user-ex")){
            throw new UserException("사용자 오류");
        }
        return new MemberDTO(id,"hello "+id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDTO{
        private String memberId;
        private String name;
    }
}
