package cn.zero.spider.webmagic.page;

import cn.zero.spider.pojo.Article;
import cn.zero.spider.pojo.Book;
import javafx.application.Application;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * 笔趣阁爬虫
 * http://www.biquge.com.tw/
 *
 * @author 蔡元豪
 * @date 2018/6/23 15:57
 */
@Component
public class BiQuGePageProcessor implements PageProcessor {

    /**
     * 上传文件的根路径
     */
    @Value("${upload.root.path}")
    private String uploadRootPath;

    private Logger logger = LoggerFactory.getLogger(BiQuGePageProcessor.class);

    private Site site = Site.me()
            //下载失败的url重新放入队列尾部重试
            .setCycleRetryTimes(3)
            //重试次数
            .setRetryTimes(3)
            //2页处理间隔 单位毫秒
            .setSleepTime(500)
            //超时时间
            .setTimeOut(3000)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");

    @Override
    public void process(Page page) {
        String siteUrl = UrlUtils.getHost(page.getUrl().toString()) + "/";
        logger.info("爬取地址：" + siteUrl);
        if (page.getUrl().regex(siteUrl + "\\w+/\\w+.html").toString() != null) {
            //获取单个章节
            getArticle(page);
        } else {
            //获取小说详情
            getBook(page);
        }

    }

    public void getArticle(Page page) {
        Article article = new Article();
        //设置章节目录
        article.setUrl(Integer.parseInt(page.getUrl().regex("http://www.biquge.com.tw/\\w+/(\\w+)").toString()));
        //设置小说目录
        article.setBookUrl(page.getUrl().regex("http://www.biquge.com.tw/(\\w+)").toString());
        //章节名
        article.setTitle(page.getHtml().css("#wrapper > div.content_read > div > div.bookname > h1").xpath("//*/text()").toString());
        //章节正文
        article.setContent(page.getHtml().xpath("//*[@id=\"content\"]/html()").toString());
        if (article.getTitle() == null
                || StringUtils.isBlank(article.getTitle())) {
            page.setSkip(true);
            logger.info("爬取小说章节失败：" + page.getUrl().toString());
        } else {
            page.putField("article", article);
            logger.info("爬取小说章节:" + article.getTitle() + "----成功--->" + page.getUrl().toString());
        }
    }

    /**
     * 小说详情
     *
     * @param page
     */
    public void getBook(Page page) {
        String siteUrl = UrlUtils.getHost(page.getUrl().toString()) + "/";
        logger.info("开始爬取小说详情：" + page.getUrl());
        Book book = new Book();
        //小说详情地址 一级目录
        book.setBookUrl(page.getUrl().regex(siteUrl + "(\\w+)").toString());
        book.setAuthor(page.getHtml().xpath("//*[@id=\"info\"]/p[1]/text()").toString());
        book.setTitle(page.getHtml().xpath("//*[@id=\"info\"]/h1/text()").toString());
        //设置更新时间
        book.setUpdateTime(page.getHtml().xpath("//*[@id=\"info\"]/p[3]/text()").replace("最后更新：", "").toString());
        //设置章节页面
        book.setIntro(page.getHtml().xpath("//*[@id=\"intro\"]/p/text()").toString());
        //设置最新章节
        book.setLatestChapterTitle(page.getHtml().xpath("//*[@id=\"info\"]/p[4]/a/text()").toString());
        //最新章节地址
        book.setLatestChapterUrl(page.getHtml().xpath("//*[@id=\"info\"]/p[4]/a/@href").regex("(\\w+)\\.html").toString());
        //封面图片
        try {
            FileUtils.copyURLToFile(new URL(UrlUtils.getHost(page.getUrl().toString()) + "/" + page.getHtml().xpath("//*[@id=\"fmimg\"]/img/@src").toString())
                    , new File(uploadRootPath + "img" + page.getHtml().xpath("//*[@id=\"fmimg\"]/img/@src").toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        book.setTitlePageUrl("img" + page.getHtml().xpath("//*[@id=\"fmimg\"]/img/@src").toString());
        book.setSourceUrl(siteUrl);
        book.setChapterPage(page.getHtml().xpath("//*[@id=\"list\"]/dl")
                //取消域名 只保存相对地址
                .replace(siteUrl, "")
                .get());
        page.addTargetRequests(page.getHtml().xpath("//*[@id=\"list\"]/dl").links().all());
        if (book.getTitle() == null
                || StringUtils.isBlank(book.getTitle())) {
            logger.info("爬取小说详情失败：" + page.getUrl());
            page.setSkip(true);
        }else {
            page.putField("book", book);
            logger.info("爬取《" + book.getTitle() + "》小说详情成功：" + page.getUrl());
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BiQuGePageProcessor()).test("http://www.biquge.com.tw/8_8568/");
    }
}
