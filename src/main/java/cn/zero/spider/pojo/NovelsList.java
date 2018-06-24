package cn.zero.spider.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 首页小说栏目封装模块
 *
 * @author 蔡元豪
 * @date 2018/6/24 14:10
 */
public class NovelsList implements Serializable {
    /**
     * 栏目置顶文章
     */
    private Book top;
    private List<Book> books;
    private String type;

    public Book getTop() {
        return top;
    }

    public void setTop(Book top) {
        this.top = top;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Index{" +
                "top=" + top +
                ", books=" + books +
                ", type='" + type + '\'' +
                '}';
    }
}
