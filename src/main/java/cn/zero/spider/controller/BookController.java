package cn.zero.spider.controller;

import cn.zero.spider.pojo.Article;
import cn.zero.spider.pojo.Book;
import cn.zero.spider.service.IArticleService;
import cn.zero.spider.service.IBookService;
import cn.zero.spider.webmagic.page.BiQuGePageProcessor;
import cn.zero.spider.webmagic.page.BiQuGeSearchPageProcessor;
import cn.zero.spider.webmagic.pipeline.BiQuGePipeline;
import cn.zero.spider.webmagic.task.AgainSpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 小说控制器
 *
 * @author 蔡元豪
 * @date 2018 /6/24 08:57
 */
@Controller
public class BookController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private IBookService bookService;

    @Autowired
    private IArticleService articleService;
    /**
     * 小说详情和章节保存组件
     */
    @Autowired
    private BiQuGePipeline biQuGePipeline;

    @Autowired
    private BiQuGePageProcessor biQuGePageProcessor;

    @Autowired
    private AgainSpider againSpider;

    @Autowired
    private RedisScheduler redisScheduler;

    @Value("${spider.url}")
    private String spiderUrl;

    /**
     * 小说详情页面
     *
     * @param bookUrl 小说url
     * @return book book
     */
    @RequestMapping(value = "/{bookUrl}")
    public ModelAndView book(@PathVariable("bookUrl") String bookUrl, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(bookUrl)) {
                Article article = articleService.getByUrl(bookUrl, cookie.getValue());
                article.setContent(null);
                modelAndView.addObject("record", article);
            }
        }
        Book book = bookService.getById(bookUrl);
        if (book != null) {
            modelAndView.addObject("book", book);
            modelAndView.setViewName("book/index");
            //如果小说不存在 开始爬取
        } else {
            logger.info("开始新抓小说：" + bookUrl);
            Spider.create(biQuGePageProcessor)
                    .addUrl(spiderUrl+"/" + bookUrl).addPipeline(biQuGePipeline)
                    //url管理
                    .setScheduler(redisScheduler)
                    .thread(20).runAsync();
            modelAndView.setViewName("book/info");
        }

        return modelAndView;
    }

    /**
     * 查询小说
     *
     * @param key  查询关键字
     * @param page 分页
     * @return m
     */
    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam(value = "key") String key,
                               @RequestParam(value = "page", required = false) Integer page) {
        ModelAndView modelAndView = new ModelAndView();
        ResultItems resultItems = null;
        try {
            String encodeKey = URLEncoder.encode(key, "gb2312");
            resultItems = Spider.create(new BiQuGeSearchPageProcessor())
                    .get(spiderUrl+"/modules/article/soshu.php?searchkey=+"
                            + encodeKey + (page == null ? "" : "&page=" + page));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (resultItems != null) {
            resultItems.getAll().forEach(modelAndView::addObject);
        }
        //搜索关键字
        modelAndView.addObject("key", key);
        //当前页面
        modelAndView.addObject("page", page != null ? page : 1);
        modelAndView.setViewName("book/result");
        return modelAndView;
    }


    /**
     * 手动更新小说
     */
    @RequestMapping("booksUpdate")
    public void update() {
        againSpider.books();
    }

}
