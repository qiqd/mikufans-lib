package org.mikufans.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Plus配置类
 * 配置分页插件等功能
 */
@Configuration
public class MybatisPlusConfig {

  /**
   * 配置MyBatis-Plus拦截器
   * 添加分页插件
   */
  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    // 添加分页插件
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
    return interceptor;
  }
}
