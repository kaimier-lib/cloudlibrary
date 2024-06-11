package com.ybvtc.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ybvtc.common.Result;
import com.ybvtc.domain.entity.Book;
import com.ybvtc.domain.entity.User;
import com.ybvtc.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/selectNewbooks")
    public ModelAndView selectNewbooks() {

        //查询最新上架的5个的图书信息
        int pageNum = 1;
        int pageSize = 5;
        Page<Book> pageResult = bookService.getPageList(pageNum, pageSize);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books_new");
        modelAndView.addObject("pageResult", pageResult);

        return modelAndView;
    }

    /**
     * 根据图书id查询图书信息
     *
     * @param id 查询的图书id
     */
    @ResponseBody
    @RequestMapping("/findById")
    public Result<Book> findById(@RequestParam("id") String id) {
        try {
            Book book = bookService.getById(id);
//            Book book=bookService.findById(id);
            if (book == null) {
                return new Result(false, "查询图书失败！");
            }
            return new Result(true, "查询图书成功", book);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询图书失败！");
        }
    }

    /**
     * 借阅图书
     *
     * @param book 借阅的图书
     * @return
     */
    @ResponseBody
    @RequestMapping("/borrowBook")
    public Result borrowBook(Book book, HttpSession session) {
        //获取当前登录的用户姓名
        String pname = ((User) session.getAttribute("user")).getName();
        book.setBorrower(pname);
        System.out.println(book);
        try {
            //根据图书的id和用户进行图书借阅
            boolean updated = bookService.borrowBook(book);

            if (!updated) {
                return new Result(false, "借阅图书失败!");
            }
            return new Result(true, "借阅成功，请到行政中心取书!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "借阅图书失败!");
        }
    }


    @RequestMapping("/search")
    public ModelAndView search(Book book,
                               @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               HttpServletRequest request) {
        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
        User user = (User) request.getSession().getAttribute("user");
        IPage<Book> pageResult = bookService.search(book, pageNum, pageSize);
        System.out.println(pageResult.getTotal());
        ModelAndView modelAndView = new ModelAndView().addObject("pageResult", pageResult);
        modelAndView.setViewName("books");
        modelAndView.addObject("search", book);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("gourl", request.getRequestURI());
        return modelAndView;
    }


    @RequestMapping("/addBook")
    @ResponseBody
    public Result<Book> addBook(Book book) {
        boolean saved = bookService.addBook(book);
        if (saved) {
            System.out.println("添加图书成功！");
            return new Result<Book>(true,"添加图书成功！");
        } else {
            System.out.println("添加图书失败！");
            return new Result<Book>(false,"添加图书失败！");
        }
    }

    @RequestMapping("/searchBorrowed")
    public ModelAndView searchBorrowed(Book book, HttpSession session,
                                       @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        User user = (User) session.getAttribute("user");
        IPage<Book> pageResult = bookService.searchBorrowed(book, user, pageNum, pageSize);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("book_borrowed");
        modelAndView.addObject("search", book);
        modelAndView.addObject("pageResult", pageResult);
        return modelAndView;
    }

}
