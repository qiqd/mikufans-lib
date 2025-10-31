package org.mikufans.util;

public class UserContent {
  private static final ThreadLocal<Integer> userIdThreadLocal = new ThreadLocal<>();

  public static void setUserId(Integer userId) {
    userIdThreadLocal.set(userId);
  }

  public static Integer getUserId() {
    return userIdThreadLocal.get();
  }

  public static void removeUserId() {
    userIdThreadLocal.remove();
  }
}
