package cn.zero.spider.service.impl;

import cn.zero.spider.pojo.Book;
import cn.zero.spider.service.IBookService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 蔡元豪
 * @date 2018/6/23 22:48
 */
@Component
@Service("bookService")
public class BookServiceImpl extends BaseServiceImpl<Book> implements IBookService {

}
