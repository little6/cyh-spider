package cn.zero.spider.webmagic.page;

import cn.zero.spider.pojo.Book;
import cn.zero.spider.pojo.NovelsList;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 蔡元豪
 * @date 2018/6/24 13:55
 */
@Component
public class BiQuGeIndexPageProcessor implements PageProcessor {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private Site site = Site.me()
            //下载失败的url重新放入队列尾部重试
            .setCycleRetryTimes(3)
            //重试次数
            .setRetryTimes(3)
            //2页处理间隔 单位毫秒
            .setSleepTime(100)
            //超时时间
            .setTimeOut(3000)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");

    /**
     * process the page, extract urls to fetch, extract the data and store
     *
     * @param page page
     */
    @Override
    public void process(Page page) {
        String siteUrl = UrlUtils.getHost(page.getUrl().toString()) + "/";
        //2个大栏目
        for (Selectable selectable :
                page.getHtml().xpath("//*[@id=\"main\"]/[@class=\"novelslist\"]").nodes()) {
            //循环栏目热门 一共6个大栏目
            for (Selectable content :
                    selectable.xpath("//*[@class=\"content\"]").nodes()) {
                //开始设置栏目信息
                NovelsList novelsList = new NovelsList();
                //设置分类
                novelsList.setType(content.xpath("//*h2/text()").toString());
                //分类置顶
                Book top = new Book();
                top.setBookUrl(content.xpath("//*div/dl/dt/a/@href").regex(siteUrl+"(\\w+)").toString());
                //小说名
                top.setTitle(content.xpath("//*div/dl/dt/a/text()").toString());
                //小说简介
                top.setIntro(content.xpath("//*[@class=top]/dl/dd/text()").toString());
                //小说封面
                top.setTitlePageUrl(content.xpath("//*[@class=\"top\"]/[@class=\"image\"]/img/@src").toString());
                novelsList.setTop(top);
                List<Book> list = new ArrayList<>();
                //栏目文章信息
                for (Selectable li:
                     content.xpath("//*ul/li").nodes()) {
                    Book book=new Book();
                    book.setBookUrl(li.xpath("//*a/@href").regex(siteUrl+"(\\w+)").toString());
                    book.setTitle(li.xpath("//*a/text()").toString());
                    book.setAuthor(li.xpath("//*li/text()").regex("/(.*)").toString());
                    System.out.println(book.toString());
                    list.add(book);
                }
                novelsList.setBooks(list);
                BoundHashOperations<String, String, String> boundHashOperations = stringRedisTemplate.boundHashOps("novelsList");
                boundHashOperations.put(novelsList.getType(),JSON.toJSONString(novelsList));

            }
        }

    }

    /**
     * get the site settings
     *
     * @return site
     * @see Site
     */
    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BiQuGeIndexPageProcessor()).addUrl("http://www.biquge.com.tw/").run();
    }
}
