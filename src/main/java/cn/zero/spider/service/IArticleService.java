package cn.zero.spider.service;

import cn.zero.spider.pojo.Article;

/**
 * @author 蔡元豪
 * @date 2018/6/23 22:35
 */
public interface IArticleService extends IBaseService<Article> {
    /**
     *
     * @param bookUrl
     * @param articleUrl
     * @return
     */
    Article getByUrl(String bookUrl, String articleUrl);

    Article getNext(String bookUrl, String articleUrl);
    Article getPrevious(String bookUrl, String articleUrl);

}
