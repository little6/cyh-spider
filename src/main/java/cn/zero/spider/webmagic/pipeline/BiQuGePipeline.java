package cn.zero.spider.webmagic.pipeline;

import cn.zero.spider.pojo.Article;
import cn.zero.spider.pojo.Book;
import cn.zero.spider.service.IArticleService;
import cn.zero.spider.service.IBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
            logger.info("保存小说详情--《"+book.getTitle()+"》");
            bookService.save(book);
        } else if (article != null) {
            articleService.save(article);
        }
    }
}
