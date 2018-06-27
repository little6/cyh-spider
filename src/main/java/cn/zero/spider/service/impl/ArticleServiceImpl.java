package cn.zero.spider.service.impl;

import cn.zero.spider.dao.ArticleMapper;
import cn.zero.spider.pojo.Article;
import cn.zero.spider.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 蔡元豪
 * @date 2018/6/23 22:35
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article getByUrl(String bookUrl, String articleUrl) {
        return articleMapper.getByUrl(bookUrl, articleUrl);
    }

    @Override
    public Article getNext(String bookUrl, String articleUrl) {
        return articleMapper.getNext(bookUrl,articleUrl);
    }

    @Override
    public Article getPrevious(String bookUrl, String articleUrl) {
        return articleMapper.getPrevious(bookUrl,articleUrl);
    }
}
