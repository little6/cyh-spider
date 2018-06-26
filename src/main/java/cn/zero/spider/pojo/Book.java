package cn.zero.spider.pojo;

import java.io.Serializable;

/**
 * @author 蔡元豪
 * @date 2018/6/23 19:30
 */

public class Book implements Serializable {

    /**
     * 小说链接
     */
    private String bookUrl;
    /**
     * 作者
     */
    private String author;
    /**
     * 书名
     */
    private String title;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 简介
     */
    private String intro;
    /**
     * 最新文章
     */
    private String latestChapterTitle;
    /**
     * 最新文章链接
     */
    private String latestChapterUrl;
    /**
     * 封面图片链接
     */
    private String titlePageUrl;
    /**
     * 来源地址
     */
    private String sourceUrl;
    /**
     * 章节页面
     */
    private String chapterPage;

    /**
     * 小说状态
     */
    private String status;

    /**
     * 字数
     */
    private String wordCount;


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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
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
                ", status='" + status + '\'' +
                ", wordCount='" + wordCount + '\'' +
                '}';
    }
}
