package com.github.marcoscouto.exception;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

public class AuthenticationEntryPointExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setStatus(403);
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(getJsonResponse());
    }

    private String getJsonResponse(){
        StandardError standardError = new StandardError("Erro de autenticação - Acesso Negado", Arrays.asList("Token inválido"), LocalDateTime.now(ZoneId.of("UTC")));
        return new JSONObject(standardError).toString(4);
    }
}
