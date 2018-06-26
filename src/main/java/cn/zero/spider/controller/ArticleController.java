package cn.zero.spider.controller;

import cn.zero.spider.pojo.Article;
import cn.zero.spider.service.IArticleService;
import cn.zero.spider.webmagic.page.BiQuGePageProcessor;
import cn.zero.spider.webmagic.pipeline.BiQuGePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * 章节控制器
 *
 * @author 蔡元豪
 * @date 2018/6/26 17:43
 */
@Controller
public class ArticleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private IArticleService articleService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BiQuGePipeline biQuGePipeline;


    /**
     * 小说章节内容页面
     *
     * @param bookUrl    小说url
     * @param articleUrl 章节url
     * @return article
     */
    @RequestMapping(value = "/{bookUrl}/{articleUrl}.html")
    public ModelAndView article(@PathVariable("bookUrl") String bookUrl, @PathVariable("articleUrl") String articleUrl) {
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.getByUrl(bookUrl, articleUrl);
        if (article == null) {
            SetOperations<String, String> removeBookUrl = stringRedisTemplate.opsForSet();
            //移出已经爬取的小说章节记录
            logger.info("移出redis爬取章节记录："+"http://www.biquge.com.tw/" + bookUrl + "/" + articleUrl + ".html");
            removeBookUrl.remove("set_www.biquge.com.tw", "http://www.biquge.com.tw/" + bookUrl + "/" + articleUrl + ".html");
            Spider.create(new BiQuGePageProcessor()).addUrl("http://www.biquge.com.tw/" + bookUrl + "/" + articleUrl + ".html").addPipeline(biQuGePipeline)
                    .setScheduler(new RedisScheduler("127.0.0.1"))
                    .thread(1).run();
            modelAndView.addObject("article", articleService.getByUrl(bookUrl, articleUrl));
        } else {
            modelAndView.addObject("article", article);
        }
        modelAndView.setViewName("book/article");
        return modelAndView;
    }

}
