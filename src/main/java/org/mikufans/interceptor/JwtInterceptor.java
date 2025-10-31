package org.mikufans.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Result;
import org.mikufans.util.JwtUtil;
import org.mikufans.util.UserContent;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * JWT认证拦截器
 * 用于拦截请求并验证JWT令牌
 */
@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {

  private final JwtUtil jwtUtil;

  private final ObjectMapper objectMapper;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    // 从请求头中获取token
    String token = request.getHeader("token");

    // 如果token不存在或为空，返回未授权错误
    if (token == null || token.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(Result.error("未授权：请提供token", 401)));
      return false;
    }

    try {
      // 验证token
      if (!jwtUtil.validateToken(token)) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(Result.error("未授权：无效的token", 401)));
        return false;
      }

      // 从token中获取用户信息并放入请求属性中
      Integer userId = jwtUtil.getUserIdFromToken(token);
      UserContent.setUserId(userId);
      return true;
    } catch (Exception e) {
      // token解析失败
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json;charset=UTF-8");
      response.getWriter().write(objectMapper.writeValueAsString(Result.error("未授权：token解析失败", 401)));
      return false;
    }
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    UserContent.removeUserId();
  }
}
