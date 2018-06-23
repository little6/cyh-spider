package cn.zero.spider.webmagic.page;

import cn.zero.spider.pojo.Book;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

/**
 * @author 蔡元豪
 * @date 2018/6/23 15:57
 */
public class BiQuGePageProcessor implements PageProcessor {

    private String siteUrl = "http://www.biquge.com.tw/";

    private Site site = Site.me().setRetryTimes(3)
            .setSleepTime(1000)
            .setTimeOut(3 * 60 * 1000)
            .setDomain("siteUrl")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");

    @Override
    public void process(Page page) {
        Book book=new Book();
        book.setBookUrl(page.getUrl().regex(siteUrl+"(\\w+)").toString());
        book.setAuthor(page.getHtml().xpath("//*[@id=\"info\"]/p[1]/text()").toString());
        book.setTitle( page.getHtml().xpath("//*[@id=\"info\"]/h1/text()").toString());
        book.setUpdateTime(page.getHtml().xpath("//*[@id=\"info\"]/p[3]/text()").toString());
        book.setIntro(page.getHtml().xpath("//*[@id=\"intro\"]/p/text()").toString());
        book.setLatestChapterTitle(page.getHtml().xpath("//*[@id=\"info\"]/p[4]/a/text()").toString());
        book.setLatestChapterUrl(page.getHtml().xpath("//*[@id=\"info\"]/p[4]/a/@href").regex("(\\w+)\\.html").toString());
        book.setTitlePageUrl(page.getHtml().xpath("//*[@id=\"fmimg\"]/img/@src").toString());
        book.setSourceUrl(siteUrl);
        book.setChapterPage(page.getHtml().xpath("//*[@id=\"list\"]/dl")
                //取消域名
                .replace(siteUrl, "")
                .get());
        System.out.println(book.toString());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BiQuGePageProcessor())
                .addUrl("http://www.biquge.com.tw/14_14260/").addPipeline(new FilePipeline("d:\\xiaoshuo\\"))
                .thread(5).run();
    }
}
