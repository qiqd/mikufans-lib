package org.mikufans.config;

import com.github.xiaoymin.knife4j.spring.configuration.Knife4jProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger/Knife4j 配置类
 * 用于配置API文档的基本信息和功能
 */
@Configuration
@ConditionalOnClass(Knife4jProperties.class)
public class SwaggerConfig {

  /**
   * 配置OpenAPI信息
   *
   * @return OpenAPI 配置对象
   */
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .info(new Info()
                    .title("MikufansLib API 文档")
                    .description("动漫、漫画、小说元数据管理系统的API接口文档")
                    .version("1.0.0")
                    .contact(new Contact()
                            .name("Qiqd")
                            .url("https://github.com/qiqd"))
                    .license(new License()
                            .name("GNU General Public License v3.0")
                            .url("https://www.gnu.org/licenses/gpl-3.0.html")));
  }
}
