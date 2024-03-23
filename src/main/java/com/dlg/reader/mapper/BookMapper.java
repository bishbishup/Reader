package com.dlg.reader.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dlg.reader.entity.Book;

import java.util.Map;

public interface BookMapper extends BaseMapper<Book> {
    public void updateScore();

    public IPage<Map> selectBookMap(IPage page);
}
