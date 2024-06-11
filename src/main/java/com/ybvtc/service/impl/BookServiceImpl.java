package com.ybvtc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybvtc.domain.entity.Book;
import com.ybvtc.domain.entity.User;
import com.ybvtc.service.BookService;
import com.ybvtc.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author kaimier
* @description 针对表【book】的数据库操作Service实现
* @createDate 2024-05-14 18:57:58
*/
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
    implements BookService{
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Page<Book> getPageList(int pageNum, int pageSize) {
        Page<Book> bookPage = new Page<>(pageNum, pageSize);
        Page<Book> pageResult = bookMapper.selectPage(bookPage, null);
        System.out.println("Page Result: " + pageResult);
        pageResult.getRecords().forEach(System.out::println);
        System.out.println("Total records found: " + pageResult.getTotal());
        return pageResult;
    }

    @Override
    public boolean borrowBook(Book inputBook) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        inputBook.setBorrowtime(dateFormat.format(new Date()));
        inputBook.setStatus("1");
        Book book = getById(inputBook.getId() + "");
        inputBook.setPrice(book.getPrice());
        inputBook.setUploadtime(book.getUploadtime());
        return saveOrUpdate(inputBook);
    }

    @Override
    public IPage<Book> search(Book book, Integer pageNum, Integer pageSize) {
        Page<Book> bookPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        // 添加条件
        queryWrapper.ne(Book::getStatus, "3"); // 条件1: book_status 不等于 '3'

        if (book.getName() != null) {
            queryWrapper.like(Book::getName, book.getName()); // 条件2: 如果 name 不为空, 添加 book_name 的模糊查询
        }

        if (book.getPress() != null) {
            queryWrapper.like(Book::getPress, book.getPress()); // 条件3: 如果 press 不为空, 添加 book_press 的模糊查询
        }

        if (book.getAuthor() != null) {
            queryWrapper.like(Book::getAuthor, book.getAuthor()); // 条件4: 如果 author 不为空, 添加 book_author 的模糊查询
        }

        // 排序
        queryWrapper.orderByDesc(Book::getBorrowtime); // 按照 book_borrowtime 排序

        IPage<Book> pageResult = page(bookPage, queryWrapper);

        System.out.println("Page Result: " + pageResult);
        pageResult.getRecords().forEach(System.out::println);
        System.out.println("Total records found: " + pageResult.getTotal());

        return pageResult;
    }

    @Override
    public boolean addBook(Book book) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        book.setUploadtime(dateFormat.format(new Date()));
        return save(book);
    }

    @Override
    public IPage<Book> searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize) {
        Page<Book> bookPage = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Book::getBorrower, user.getName()); // 条件1: book_borrower 等于 user.getName()

        // 动态添加搜索条件
        if (book.getName() != null) {
            queryWrapper.like(Book::getName, book.getName()); // 条件2: 如果 name 不为空, 添加 book_name 的模糊查询
        }
        if (book.getPress() != null) {
            queryWrapper.like(Book::getPress, book.getPress()); // 条件3: 如果 press 不为空, 添加 book_press 的模糊查询
        }
        if (book.getAuthor() != null) {
            queryWrapper.like(Book::getAuthor, book.getAuthor()); // 条件4: 如果 author 不为空, 添加 book_author 的模糊查询
        }

        if ("ADMIN".equals(user.getRole())) {
//            queryWrapper.and(i -> i.eq(Book::getStatus, "1").or().eq(Book::getStatus, "2")); // 条件5: 如果是管理员, 查询 book_status 等于 '1' 或 '2'
            queryWrapper.and(wrapper -> {
                wrapper.eq(Book::getBorrower, user.getName())
                        .eq(Book::getStatus, "1")
                        .or()
                        .eq(Book::getStatus, "2");
            });
        } else {
            queryWrapper.eq(Book::getBorrower, user.getName())
                    .and(wrapper->{
                        wrapper.eq(Book::getStatus, "1")
                                .or()
                                .eq(Book::getStatus, "2");
                    });
        }

        queryWrapper.orderByDesc(Book::getBorrowtime); // 按照 book_borrowtime 排序

        return page(bookPage, queryWrapper);
    }
}




