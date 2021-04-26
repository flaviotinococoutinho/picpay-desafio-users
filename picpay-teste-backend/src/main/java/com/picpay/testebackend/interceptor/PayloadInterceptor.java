package com.picpay.testebackend.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.picpay.testebackend.interceptor.PayloadInterceptor;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class PayloadInterceptor extends HandlerInterceptorAdapter {

    /**
     * Executed before actual handler is executed
     **/
    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        log.info("Incoming request. Request details: ", request.getPathInfo());
        return true;
    }

    /**
     * Executed before after handler is executed
     **/
    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
                           final ModelAndView modelAndView) throws Exception {
    }

    /**
     * Executed after complete request is finished
     **/
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
                                final Exception ex) throws Exception {
        log.info("Request handling done. Details: ", response.getStatus());
    }
}
