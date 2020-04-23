package com.github.marcoscouto.exception;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

@Component
public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler{
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(401);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(getJsonResponse());
    }

    private String getJsonResponse(){
        StandardError standardError = new StandardError("Erro de autenticação - Não autorizado", Arrays.asList("Email ou senha inválidos"), LocalDateTime.now(ZoneId.of("UTC")));
        return new JSONObject(standardError).toString(4);
    }
}
