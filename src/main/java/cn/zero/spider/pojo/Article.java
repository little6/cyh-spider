package cn.zero.spider.pojo;

import java.io.Serializable;

/**
 * @author 蔡元豪
 * @date 2018/6/23 19:52
 */
public class Article implements Serializable {
    private String bookUrl;
    private int url;
    private String title;
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
