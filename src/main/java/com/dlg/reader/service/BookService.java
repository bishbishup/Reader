package com.dlg.reader.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dlg.reader.entity.Book;

import java.util.Map;

public interface BookService {
    /**
     * 分页查询图书
     * @param categoryId 分类编号
     * @param order 排序方式
     * @param page 页号
     * @param rows 每页记录数
     * @return 分页对象
     */
    public IPage<Book> selectPage(Long categoryId, String order, Integer page, Integer rows);
    /**
     * 根据图书编号查询图书对象
     * @param bookId 图书编号
     * @return 图书对象
     */
    public Book selectById(Long bookId);

    public void updateScore();

    public IPage<Map> selectBookMap(Integer page, Integer rows);

    public Book createBook(Book book);

    public Book updateBook(Book book);

    public void deleteBook(Long bookId);


}
