package cn.zero.spider.webmagic.page;

import cn.zero.spider.pojo.Book;
import cn.zero.spider.pojo.NovelsList;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.UrlUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * 笔趣阁首页爬虫
 *
 * @author 蔡元豪
 * @date 2018/6/24 13:55
 */
@Component
public class BiQuGeIndexPageProcessor implements PageProcessor {

    /**
     * 上传文件的根路径
     */
    @Value("${upload.root.path}")
    private String uploadRootPath;

    private Logger logger = LoggerFactory.getLogger(BiQuGeIndexPageProcessor.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private Site site = Site.me()
            //下载失败的url重新放入队列尾部重试
            .setCycleRetryTimes(3)
            //重试次数
            .setRetryTimes(3)
            //2页处理间隔 单位毫秒
            .setSleepTime(1000)
            //超时时间
            .setTimeOut(3000)
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");

    /**
     * 处理页面，提取网址以提取数据并存储
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
                top.setBookUrl(content.xpath("//*div/dl/dt/a/@href").regex(siteUrl + "(\\w+)").toString());
                //小说名
                top.setTitle(content.xpath("//*div/dl/dt/a/text()").toString());
                //小说简介
                top.setIntro(content.xpath("//*[@class=top]/dl/dd/text()").toString());
                //小说封面
                //封面图片下载
                try {
                    URL url = new URL(content.xpath("//*[@class=\"top\"]/[@class=\"image\"]/img/@src").toString());
                    URLConnection con = url.openConnection();
                    con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
                    con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                    con.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
                    InputStream inStream = con.getInputStream();
                    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int len = 0;
                    while ((len = inStream.read(buf)) != -1) {
                        outStream.write(buf, 0, len);
                    }

                    File file = new File(uploadRootPath + "img/index/" + content.xpath("//*[@class=\"top\"]/[@class=\"image\"]/img/@src")
                            .replace(UrlUtils.getHost(page.getUrl().toString()) + "/", "").toString());
                    if (!file.exists()) {
                        if (!file.getParentFile().exists()) {
                            if (file.getParentFile().mkdirs()) {
                                logger.info("文件夹创建失败");
                            }
                        }
                        file.createNewFile();
                    }
                    FileOutputStream op = new FileOutputStream(file);
                    op.write(outStream.toByteArray());
                    inStream.close();
                    outStream.close();
                    op.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //保存2封面链接到对象
                top.setTitlePageUrl("img/index/" + content.xpath("//*[@class=\"top\"]/[@class=\"image\"]/img/@src")
                        .replace(UrlUtils.getHost(page.getUrl().toString()) + "/", "")
                        .toString());
                novelsList.setTop(top);
                List<Book> list = new ArrayList<>();
                //栏目文章信息
                for (Selectable li :
                        content.xpath("//*ul/li").nodes()) {
                    Book book = new Book();
                    book.setBookUrl(li.xpath("//*a/@href").regex(siteUrl + "(\\w+)").toString());
                    book.setTitle(li.xpath("//*a/text()").toString());
                    book.setAuthor(li.xpath("//*li/text()").regex("/(.*)").toString());
                    list.add(book);
                }
                novelsList.setBooks(list);
                System.out.println(novelsList);
                //保存首页缓存到redis
                stringRedisTemplate.opsForHash().put("novelsList", novelsList.getType(), JSON.toJSONString(novelsList));
            }
        }

    }

    /**
     * 返回设置
     *
     * @return site
     * @see Site
     */
    @Override
    public Site getSite() {
        return site;
    }

}
