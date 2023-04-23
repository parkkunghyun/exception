package helloException.exception.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.LogRecord;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 =(HttpServletRequest) request;
        String requestURI = request1.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
           log.info("REQUEST [{}] [{}] [{}]", uuid,request1.getDispatcherType(),requestURI);
           chain.doFilter(request,response);
        } catch (Exception e) {
            log.info("exception! {}", e.getMessage());
            throw e;
        } finally {
            log.info("RESPONSE [{}] [{}] [{}]", uuid,request1.getDispatcherType(),requestURI);
        }
     }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
