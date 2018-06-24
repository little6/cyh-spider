package cn.zero.spider.controller;

import cn.zero.spider.pojo.Book;
import cn.zero.spider.service.IArticleService;
import cn.zero.spider.service.IBookService;
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
 * The type Book controller.
 *
 * @author 蔡元豪
 * @date 2018 /6/24 08:57
 */
@Controller
public class BookController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    private IBookService bookService;

    /**
     * Sets book service.
     *
     * @param bookService the book service
     */
    @Autowired
    public void setBookService(IBookService bookService) {
        this.bookService = bookService;
    }

    private IArticleService articleService;

    /**
     * Sets article service.
     *
     * @param articleService the article service
     */
    @Autowired
    public void setArticleService(IArticleService articleService) {
        this.articleService = articleService;
    }

    private StringRedisTemplate stringRedisTemplate;

    /**
     * Sets string redis template.
     *
     * @param stringRedisTemplate the string redis template
     */
    @Autowired
    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private BiQuGePipeline biQuGePipeline;

    /**
     * Sets bi qu ge pipeline.
     *
     * @param biQuGePipeline the bi qu ge pipeline
     */
    @Autowired
    public void setBiQuGePipeline(BiQuGePipeline biQuGePipeline) {
        this.biQuGePipeline = biQuGePipeline;
    }

    /**
     * 小说详情页面
     *
     * @param bookUrl 小说url
     * @return book book
     */
    @RequestMapping(value = "/{bookUrl}")
    public ModelAndView getBook(@PathVariable("bookUrl") String bookUrl) {

        ModelAndView modelAndView = new ModelAndView();
        Book book = bookService.getById(bookUrl);
        if (book != null) {
            modelAndView.addObject("book", book);
            modelAndView.setViewName("book/index");
            //如果小说不存在 开始爬取
        } else {
            logger.info("开始新抓小说：http://www.biquge.com.tw/" + bookUrl);
            Spider.create(new BiQuGePageProcessor())
                    .addUrl("http://www.biquge.com.tw/" + bookUrl).addPipeline(biQuGePipeline)
                    //url管理
                    .setScheduler(new RedisScheduler("127.0.0.1"))
                    .thread(20).runAsync();
            modelAndView.setViewName("book/info");
        }

//        SetOperations<String, String> removeBookUrl = stringRedisTemplate.opsForSet();
        //移出已经爬取的小说详情页面 重新爬取
//        removeBookUrl.remove("set_www.biquge.com.tw", "http://www.biquge.com.tw/" + bookUrl);

        return modelAndView;
    }

    /**
     * 小说章节内容页面
     *
     * @param bookUrl    小说url
     * @param articleUrl 章节url
     * @return article
     */
    @RequestMapping(value = "/{bookUrl}/{articleUrl}.html")
    public ModelAndView getArticle(@PathVariable("bookUrl") String bookUrl, @PathVariable("articleUrl") String articleUrl) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("article", articleService.getByUrl(bookUrl, articleUrl));
        modelAndView.setViewName("book/article");
        return modelAndView;
    }

}
