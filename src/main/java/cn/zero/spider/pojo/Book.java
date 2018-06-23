package cn.zero.spider.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author 蔡元豪
 * @date 2018/6/23 19:30
 */
public class Book implements Serializable {

    private String bookUrl;
    private String author;
    private String title;
    private String updateTime;
    private String intro;
    private String latestChapterTitle;
    private String latestChapterUrl;
    private String titlePageUrl;
    private String sourceUrl;
    private String chapterPage;
    public String getBookUrl() {
        return bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLatestChapterTitle() {
        return latestChapterTitle;
    }

    public void setLatestChapterTitle(String latestChapterTitle) {
        this.latestChapterTitle = latestChapterTitle;
    }

    public String getLatestChapterUrl() {
        return latestChapterUrl;
    }

    public void setLatestChapterUrl(String latestChapterUrl) {
        this.latestChapterUrl = latestChapterUrl;
    }

    public String getTitlePageUrl() {
        return titlePageUrl;
    }

    public void setTitlePageUrl(String titlePageUrl) {
        this.titlePageUrl = titlePageUrl;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }


    public String getChapterPage() {
        return chapterPage;
    }

    public void setChapterPage(String chapterPage) {
        this.chapterPage = chapterPage;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookUrl='" + bookUrl + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", intro='" + intro + '\'' +
                ", latestChapterTitle='" + latestChapterTitle + '\'' +
                ", latestChapterUrl='" + latestChapterUrl + '\'' +
                ", titlePageUrl='" + titlePageUrl + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", chapterPage='" + chapterPage + '\'' +
                '}';
    }
}
