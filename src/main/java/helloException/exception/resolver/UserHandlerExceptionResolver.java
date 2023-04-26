package helloException.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import helloException.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if(ex instanceof UserException) {
                log.info("UserException을 400으로!!");
                // json 이나 html이냐
                String acceptHeader =request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                if("application/json".equals(acceptHeader)) {
                    Map<String,Object> errorResult = new HashMap<>();
                    errorResult.put("ex",ex.getClass());
                    errorResult.put("message", ex.getMessage());

                    // json을 문자로!
                    String result  =mapper.writeValueAsString(errorResult);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(result);
                    return new ModelAndView();
                }
                // html
                else {
                    return new ModelAndView("error/500");
                }
            }

        }catch (IOException e) {
            log.error("resolver error");
        }
        return  null;
    }
}
