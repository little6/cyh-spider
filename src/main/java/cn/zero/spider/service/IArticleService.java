package cn.zero.spider.service;

import cn.zero.spider.pojo.Article;

/**
 * The interface Article service.
 *
 * @author 蔡元豪
 * @date 2018 /6/23 22:35
 */
public interface IArticleService extends IBaseService<Article> {
    /**
     * Gets by url.
     *
     * @param bookUrl    the book url
     * @param articleUrl the article url
     * @return by url
     */
    Article getByUrl(String bookUrl, String articleUrl);

    /**
     * Gets next.
     *
     * @param bookUrl    the book url
     * @param articleUrl the article url
     * @return the next
     */
    Article getNext(String bookUrl, String articleUrl);

    /**
     * Gets previous.
     *
     * @param bookUrl    the book url
     * @param articleUrl the article url
     * @return the previous
     */
    Article getPrevious(String bookUrl, String articleUrl);

}
