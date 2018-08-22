package cn.zero.spider.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author 蔡元豪
 * @date 2018/8/15 17:05
 */
@Aspect
@Component
public class BookAop {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Pointcut("execution(* cn.zero.spider.controller.BookController.book(..))")
    public void bookAop() {

    }


    @Before(value = "bookAop()")
    public void bookStatistical(JoinPoint joinPoint) {
        stringRedisTemplate.opsForHash().increment("views", joinPoint.getArgs()[0], 1);
    }
}
