package org.mikufans.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * JWT工具类
 * 用于生成和解析JWT令牌
 */
@Component
public class JwtUtil {

  // 生成一个足够安全的密钥
  private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

  /**
   * 生成JWT令牌
   *
   * @param userId 用户ID
   * @param email  用户邮箱
   * @return JWT令牌字符串
   */
  public String generateToken(Integer userId, String email) {
    Date now = new Date();
    // 令牌过期时间（一个月）
    long EXPIRATION_TIME = 86400000L * 30; // 一个月的毫秒数
    Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

    return Jwts.builder()
            .claim("userId", userId)
            .claim("email", email)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(secretKey)
            .compact();
  }

  /**
   * 解析JWT令牌
   *
   * @param token JWT令牌字符串
   * @return 令牌中的声明信息
   */
  public Claims parseToken(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  /**
   * 从令牌中获取用户ID
   *
   * @param token JWT令牌字符串
   * @return 用户ID
   */
  public Integer getUserIdFromToken(String token) {
    Claims claims = parseToken(token);
    return claims.get("userId", Integer.class);
  }

  /**
   * 从令牌中获取用户邮箱
   *
   * @param token JWT令牌字符串
   * @return 用户邮箱
   */
  public String getEmailFromToken(String token) {
    Claims claims = parseToken(token);
    return claims.get("email", String.class);
  }

  /**
   * 验证令牌是否有效
   *
   * @param token JWT令牌字符串
   * @return 是否有效
   */
  public boolean validateToken(String token) {
    try {
      parseToken(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
