package cn.zero.spider.webmagic.pipeline;

import cn.zero.spider.pojo.Article;
import cn.zero.spider.pojo.Book;
import cn.zero.spider.service.IArticleService;
import cn.zero.spider.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * 爬取结果持久化
 *
 * @author 蔡元豪
 * @date 2018/6/23 22:56
 */
@Component
public class BiQuGePipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(BiQuGePipeline.class);

    @Autowired
    private IBookService bookService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Process extracted results.
     *
     * @param resultItems resultItems
     * @param task        task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        Book book = resultItems.get("book");
        Article article = resultItems.get("article");
        if (book != null) {
            SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
            //查看是否已经爬取过 检测是否为更新操作
            Boolean member = opsForSet.isMember("books", book.getBookUrl());
            if (member) {
                Book old = bookService.getById(book.getBookUrl());
                if (!old.getLatestChapterUrl().equals(book.getLatestChapterUrl())) {
                    bookService.update(book);
                    logger.info("更新小说详情成功--《" + book.getTitle() + "》");
                }else {
                    logger.info("小说详情--《" + book.getTitle() + "》还没有更新信息");
                }
            } else {
                bookService.save(book);
                //保存已经爬取的小说列表
                SetOperations<String, String> books = stringRedisTemplate.opsForSet();
                books.add("books", book.getBookUrl());
                logger.info("保存小说详情成功--《" + book.getTitle() + "》");
            }


        }
        if (article != null) {
            articleService.save(article);
            logger.info("保存小说章节--《" + article.getTitle() + "》");
        }
    }
}
