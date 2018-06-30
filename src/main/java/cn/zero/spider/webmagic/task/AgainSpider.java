package cn.zero.spider.webmagic.task;

import cn.zero.spider.webmagic.page.BiQuGeIndexPageProcessor;
import cn.zero.spider.webmagic.page.BiQuGePageProcessor;
import cn.zero.spider.webmagic.pipeline.BiQuGePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蔡元豪
 * @date 2018/6/29 22:14
 */
@Component
public class AgainSpider {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BiQuGeIndexPageProcessor biQuGeIndexPageProcessor;

    /**
     * 小说详情和章节保存组件
     */
    @Autowired
    private BiQuGePipeline biQuGePipeline;

    @Autowired
    private BiQuGePageProcessor biQuGePageProcessor;

    /**
     * 定时更新首页   每6小时
     */
    @Scheduled(cron = "0 0 0/6 * * ? ")
    public void startPa() {
        Spider.create(biQuGeIndexPageProcessor)
                .addUrl("http://www.biquge.com.tw/")
                .setScheduler(new RedisScheduler("127.0.0.1"))
                .runAsync();
    }

    @Scheduled(cron = "0 0 0/6 * * ? ")
    public void books() {
        //获取爬取过的小说
        BoundSetOperations<String, String> books = stringRedisTemplate.boundSetOps("books");
        Set<String> members = books.members();
        BoundSetOperations<String, String> setOps = stringRedisTemplate.boundSetOps("set_www.biquge.com.tw");
        Set<String> membersLinks = new HashSet<>();
        for (String member :
                members) {
            membersLinks.add("http://www.biquge.com.tw/" + member);
        }
        //删除已经爬取的url的redis记录
        String[] urls = membersLinks.toArray(new String[0]);
        setOps.remove(urls);
        //重新更新爬取小说
        Spider.create(biQuGePageProcessor)
                .addUrl(urls).addPipeline(biQuGePipeline)
                //url管理
                .setScheduler(new RedisScheduler("127.0.0.1"))
                .thread(20).runAsync();
    }

}
