package helloException.exception.api;

import helloException.exception.exception.BadRequestException;
import helloException.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class ApiExceptionController {

    @GetMapping("/api/members/{id}")
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

    @GetMapping("/api/response-status-api")
    public String responseStatusEx1() {
        throw new BadRequestException();
    }

    @GetMapping("/api/response-status-ex2")
    public String responseStatusEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @GetMapping("/api/default-handler-ex")
    public String defaultException(@RequestParam Integer data) {
        return "ok";
    }

    @Data
    @AllArgsConstructor
    static class MemberDTO{
        private String memberId;
        private String name;
    }

}
