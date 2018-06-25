package cn.zero.spider.dao;

import cn.zero.spider.pojo.Article;
import org.apache.ibatis.annotations.Param;

/**
 * @author 蔡元豪
 * @date 2018/6/23 22:32
 */
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 通过url获取文章
     *
     * @param bookUrl
     * @param articleUrl
     * @return
     */
    Article getByUrl(@Param("bookUrl") String bookUrl, @Param("articleUrl") String articleUrl);
}
