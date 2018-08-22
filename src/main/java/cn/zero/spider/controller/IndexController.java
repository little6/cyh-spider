package cn.zero.spider.controller;

import cn.zero.spider.pojo.NovelsList;
import cn.zero.spider.webmagic.page.BiQuGeIndexPageProcessor;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 首页 controller.
 *
 * @author 蔡元豪
 * @date 2018 /6/23 21:55
 */
@Controller
public class IndexController extends BaseController {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BiQuGeIndexPageProcessor biQuGeIndexPageProcessor;

    @Autowired
    private RedisScheduler redisScheduler;

    /**
     * 上传文件的根路径
     */
    @Value("${upload.root.path}")
    private String uploadRootPath;


    /**
     * 首页
     *
     * @return model and view
     */
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

    /**
     * 手动更新首页
     *
     * @return model and view
     */
    @RequestMapping("/updateIndex")
    public ModelAndView spiderIndex() {
        ModelAndView modelAndView = new ModelAndView();
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
        try {
            FileUtils.cleanDirectory(new File(uploadRootPath + "img/index/"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        opsForSet.remove("set_www.biquge.com.tw", "http://www.biquge.com.tw/");
        Spider.create(biQuGeIndexPageProcessor)
                .addUrl("http://www.biquge.com.tw/")
                .setScheduler(redisScheduler)
                .runAsync();
        modelAndView.setViewName("index");
        return modelAndView;
    }

}
