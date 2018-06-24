package cn.zero.spider;

import cn.zero.spider.webmagic.page.BiQuGeIndexPageProcessor;
import cn.zero.spider.webmagic.pipeline.BiQuGePipeline;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author chaterhower
 */
@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "cn.zero.spider")
@MapperScan({"cn.zero.spider.dao"})
public class SpiderApplication {

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
