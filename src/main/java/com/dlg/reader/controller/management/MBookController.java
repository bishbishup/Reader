package com.dlg.reader.controller.management;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dlg.reader.entity.Book;
import com.dlg.reader.service.BookService;
import com.dlg.reader.utils.ResponseUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/management/book")
public class MBookController {
    @Resource
    private BookService bookService;

    @GetMapping("/list")
    public ResponseUtils list(Integer page,Integer rows){
        if (page == null){
            page = 1;
        };
        if (rows == null){
            rows = 10;
        }
        ResponseUtils resp = null;
        try{
            IPage p = bookService.selectBookMap(page,rows);
            resp = new ResponseUtils().put("list",p.getRecords()).put("count",p.getTotal() );
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;

    }

    @PostMapping("/upload")
    public Map upload(@RequestParam("img") MultipartFile file, HttpServletRequest request) throws IOException {
        // 得到上传文件目录
        String uplaodpath = request.getServletContext().getResource("./").getPath()+"/upload/";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fileName = sdf.format(new Date());
        // abc.jpg
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        file.transferTo(new File(uplaodpath + fileName + suffix));
        Map result = new LinkedHashMap();
        result.put("errno",0);
        result.put("data",new String[]{"/upload/"+fileName+suffix});
        return result;
    }

    @PostMapping("/create")
    public ResponseUtils createBook(Book book){
        ResponseUtils resp;
        try {
            Document doc  = Jsoup.parse(book.getDescription());
            Elements elements = doc.select("img");
            if (elements.size() == 0){
                resp = new ResponseUtils("ImageNotFoundError","图书描述未包含封面图片");
                return resp;
            }
            String cover = elements.first().attr("src");
            book.setCover(cover);
            book.setEvaluationScore(0f);
            book.setEvaluationQuantity(0);
            bookService.createBook(book);
            resp = new ResponseUtils().put("book",book);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }

    @PostMapping("/update")
    public ResponseUtils updateBook(Book book){
        ResponseUtils resp;
        try{
            Document doc  = Jsoup.parse(book.getDescription());
            Elements elements = doc.select("img");
            if (elements.size() == 0){
                resp = new ResponseUtils("ImageNotFoundError","图书描述未包含封面图片");
                return resp;
            }
            String cover = elements.first().attr("src");
            Book b = bookService.selectById(book.getBookId());
            b.setBookName(book.getBookName());
            b.setSubTitle(book.getSubTitle());
            b.setAuthor(book.getAuthor());
            b.setCategoryId(book.getCategoryId());
            b.setDescription(book.getDescription());
            b.setCover(cover);
            bookService.updateBook(b);
            resp = new ResponseUtils().put("book",b);
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(),e.getMessage());
        }
        return resp;
    }

    @PostMapping("/delete")
    public ResponseUtils deleteBook(Long bookId){
        ResponseUtils resp = null;
        try{
            bookService.deleteBook(bookId);
            resp = new ResponseUtils();
        }catch (Exception e){
            e.printStackTrace();
            resp = new ResponseUtils(e.getClass().getSimpleName(), e.getMessage());
        }
        return resp;
    }
}
