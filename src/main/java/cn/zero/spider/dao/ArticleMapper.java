package cn.zero.spider.dao;

import cn.zero.spider.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * The interface Article mapper.
 *
 * @author 蔡元豪
 * @date 2018 /6/23 22:32
 */
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 通过url获取文章
     *
     * @param bookUrl    the book url
     * @param articleUrl the article url
     * @return by url
     */
    Article getByUrl(@Param("bookUrl") String bookUrl, @Param("articleUrl") String articleUrl);

    /**
     * 获取下一章
     *
     * @param bookUrl    the book url
     * @param articleUrl the article url
     * @return next
     */
    Article getNext(@Param("bookUrl") String bookUrl, @Param("articleUrl") String articleUrl);

    /**
     * 获取上一章
     *
     * @param bookUrl    the book url
     * @param articleUrl the article url
     * @return previous
     */
    Article getPrevious(@Param("bookUrl") String bookUrl, @Param("articleUrl") String articleUrl);

}
