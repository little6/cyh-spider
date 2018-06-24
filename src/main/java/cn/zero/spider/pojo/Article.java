package cn.zero.spider.pojo;

import java.io.Serializable;

/**
 * @author 蔡元豪
 * @date 2018/6/23 19:52
 */
public class Article implements Serializable {
    /**
     * 章节目录地址
     */
    private int url;
    /**
     * 小说详情地址
     */

    private String bookUrl;
    /**
     * 章节标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "url=" + url +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
