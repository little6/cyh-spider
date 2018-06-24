package cn.zero.spider.controller;

import cn.zero.spider.webmagic.page.BiQuGePageProcessor;
import cn.zero.spider.webmagic.pipeline.BiQuGePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * @author 蔡元豪
 * @date 2018/6/24 08:57
 */
@Controller
@RequestMapping("book")
public class BookController extends BaseController {

    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private BiQuGePipeline biQuGePipeline;
    @Autowired
    public void setBiQuGePipeline(BiQuGePipeline biQuGePipeline) {
        this.biQuGePipeline = biQuGePipeline;
    }

    @RequestMapping(value = "/{bookUrl}")
    public ModelAndView getBook(@PathVariable("bookUrl") String bookUrl) {
        SetOperations<String, String> removeBookUrl = stringRedisTemplate.opsForSet();
        //移出已经爬取的小说详情页面 重新爬取
        removeBookUrl.remove("set_www.biquge.com.tw", "http://www.biquge.com.tw/" + bookUrl);
        Spider.create(new BiQuGePageProcessor())
                .addUrl("http://www.biquge.com.tw/"+bookUrl).addPipeline(biQuGePipeline)
                .setScheduler(new RedisScheduler("127.0.0.1"))
                .thread(5).run();
        return new ModelAndView();
    }

}
