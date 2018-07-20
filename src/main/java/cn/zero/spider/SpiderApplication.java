package cn.zero.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chaterhower
 */
@SpringBootApplication
@Configuration
@MapperScan({"cn.zero.spider.dao"})
@EnableScheduling
public class SpiderApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpiderApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }
}
