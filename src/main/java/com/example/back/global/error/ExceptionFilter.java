package com.example.back.global.error;

import com.example.back.global.error.exception.ErrorCode;
import com.example.back.global.error.exception.GlobalException;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (GlobalException e) {
            writerErrorCode(response, e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            writerErrorCode(response, ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    private void writerErrorCode(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        ErrorResponse errorResponse = new ErrorResponse(errorCode);

        response.setStatus(errorCode.getStatus());
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(errorResponse.toString());
    }
}