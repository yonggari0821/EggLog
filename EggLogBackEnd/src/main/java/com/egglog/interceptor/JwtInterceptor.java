package com.egglog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.egglog.util.JwtUtil;

@Component
public class JwtInterceptor implements HandlerInterceptor {

  private static final String HEADER_AUTH = "access-token";
  @Autowired
  private JwtUtil jwtUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    if (request.getMethod().equals("OPTIONS"))
      return true;

    String token = request.getHeader(HEADER_AUTH);

 // 로그아웃 요청일 경우 세션 무효화
    if (request.getRequestURI().endsWith("/logout")) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return true;
    }
    if (token != null) {
      jwtUtil.valid(token);
      return true;
    }
    throw new Exception("유효하지 않은 접근이다.");
  }
}
