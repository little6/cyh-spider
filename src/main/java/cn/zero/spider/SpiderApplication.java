package cn.zero.spider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * @author chaterhower
 */
@SpringBootApplication
@Configuration
@MapperScan({"cn.zero.spider.dao"})
@EnableCaching
@EnableScheduling
public class SpiderApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpiderApplication.class);
    }

    @Bean
    public RedisScheduler redisScheduler() {
        return new RedisScheduler("127.0.0.1");
    }
    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }
}
