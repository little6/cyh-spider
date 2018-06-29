package cn.zero.spider;

import cn.zero.spider.webmagic.page.BiQuGeIndexPageProcessor;
import cn.zero.spider.webmagic.pipeline.BiQuGePipeline;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chaterhower
 */
@SpringBootApplication
@Configuration
@MapperScan({"cn.zero.spider.dao"})
@EnableScheduling
public class SpiderApplication  extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpiderApplication.class);
    }
    @Bean
    public BiQuGePipeline biQuGePipeline(){
        return new BiQuGePipeline();
    }

    @Bean
    public BiQuGeIndexPageProcessor biQuGeIndexPageProcessor(){
        return new BiQuGeIndexPageProcessor();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpiderApplication.class, args);
    }
}
