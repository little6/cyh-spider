package cn.zero.spider.controller;

import cn.zero.spider.pojo.NovelsList;
import cn.zero.spider.webmagic.page.BiQuGeIndexPageProcessor;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 蔡元豪
 * @date 2018/6/23 21:55
 */
@Controller
public class IndexController extends BaseController {

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private BiQuGeIndexPageProcessor biQuGeIndexPageProcessor;

    @Autowired
    public void setBiQuGeIndexPageProcessor(BiQuGeIndexPageProcessor biQuGeIndexPageProcessor) {
        this.biQuGeIndexPageProcessor = biQuGeIndexPageProcessor;
    }

    @RequestMapping(value = {"", "index"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        BoundHashOperations<String, String, String> boundHashOperations = stringRedisTemplate.boundHashOps("novelsList");
        List<NovelsList> novelsLists = new ArrayList<>(6);
        boundHashOperations.entries().forEach((k, v) -> novelsLists.add(JSON.parseObject(v, NovelsList.class)));
        modelAndView.addObject("novelsLists", novelsLists);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/pa")
    public ModelAndView spiderIndex() {
        ModelAndView modelAndView = new ModelAndView();
        Spider.create(biQuGeIndexPageProcessor)
                .addUrl("http://www.biquge.com.tw/")
                .setScheduler(new RedisScheduler("127.0.0.1"))
                .runAsync();
        return modelAndView;
    }

}
