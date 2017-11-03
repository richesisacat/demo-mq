package priv.demo.mq.producer.config;

import com.google.common.base.Predicate;

import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // 启用 Swagger
public class SwaggerConfig {

  @Bean
  public Docket createRestApi() {
    final Predicate<RequestHandler> predicate = (RequestHandler input) -> {
      if (null != input) {
        final Class<?> declaringClass = input.declaringClass();
        // 排除
        if (declaringClass == BasicErrorController.class) {
          return false;
        }
        // 被注解的类
        if (declaringClass.isAnnotationPresent(RestController.class)) {
          return true;
        }
        // 被注解的方法
        return input.isAnnotatedWith(ResponseBody.class);
      }
      return false;
    };

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(false)
        .select()
        //.apis(predicate)
        .apis(RequestHandlerSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("消息生产")//大标题
        .version("1.0")//版本
        .build();
  }
}