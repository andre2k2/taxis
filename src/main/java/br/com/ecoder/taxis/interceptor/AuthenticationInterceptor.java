package br.com.ecoder.taxis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.ecoder.taxis.exception.UnauthorizedException;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    private static final String X_AUTH_TOKEN = "X-Auth-Token";
    private static final String LOGIN_URI = "/login";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (needAuthentication(request)) {

            String token = request.getHeader(X_AUTH_TOKEN);

            if (!isValidToken(token)) {
                throw new UnauthorizedException("O servico que voce esta tentando usar requer autenticacao.");
            }
        }

        return super.preHandle(request, response, handler);
    }

    private boolean needAuthentication(HttpServletRequest request) {
        return !request.getRequestURI().startsWith(LOGIN_URI);
    }

    private boolean isValidToken(String token) {
        return true;
    }

}
