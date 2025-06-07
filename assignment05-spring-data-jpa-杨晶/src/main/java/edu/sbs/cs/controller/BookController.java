package edu.sbs.cs.controller;

import edu.sbs.cs.entity.Book;
import edu.sbs.cs.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookRepository bookRepository;
    
    // 创建新书
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }
    
    // 查询所有书籍
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    // 根据ID查询单本书籍
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // 更新书籍
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPrice(bookDetails.getPrice());
            book.setPublishedDate(bookDetails.getPublishedDate());
            return ResponseEntity.ok(bookRepository.save(book));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // 删除书籍
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // 分页和排序查询
    @GetMapping("/paged")
    public Page<Book> getBooksPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "publishedDate,asc") String[] sort) {
        
        Sort sortOrder = Sort.by(sort[0]);
        if (sort.length > 1 && sort[1].equalsIgnoreCase("desc")) {
            sortOrder = sortOrder.descending();
        } else {
            sortOrder = sortOrder.ascending();
        }
        
        Pageable pageable = PageRequest.of(page, size, sortOrder);
        return bookRepository.findAll(pageable);
    }
    
    // 查询指定作者的所有书籍
    @GetMapping("/by-author")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookRepository.findByAuthor(author);
    }
    
    // 查询某一年出版的所有书籍
    @GetMapping("/by-year")
    public List<Book> getBooksByYear(@RequestParam int year) {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        return bookRepository.findByPublishedDateBetween(start, end);
    }
    
    // 通过书名模糊搜索
    @GetMapping("/search")
    public List<Book> searchBooksByTitle(@RequestParam String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }
}
