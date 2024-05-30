package com.ybvtc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybvtc.domain.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybvtc.domain.User;

/**
* @author kaimier
* @description 针对表【book】的数据库操作Service
* @createDate 2024-05-14 18:57:58
*/
public interface BookService extends IService<Book> {

    Page<Book> getPageList(int pageNum, int pageSize);

    boolean borrowBook(Book book);

    IPage<Book> search(Book book, Integer pageNum, Integer pageSize);

    boolean addBook(Book book);

    IPage<Book> searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize);
}
