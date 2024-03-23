package com.dlg.reader.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dlg.reader.entity.Book;
import com.dlg.reader.service.BookService;
import com.dlg.reader.utils.ResponseUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/list")
    public ResponseUtils list(Long categoryId,String order,Integer page){
        ResponseUtils resp = null;
        try {
          IPage<Book> p = bookService.selectPage(categoryId, order, page, 10);
          resp = new ResponseUtils().put("page",p);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;

    }

    @GetMapping("/id/{id}")
    public ResponseUtils selectById(@PathVariable("id") Long id){
        ResponseUtils resp = null;
        try {
            Book book = bookService.selectById(id);
            resp = new ResponseUtils().put("book",book);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;

    }
}
