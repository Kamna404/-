package edu.sbs.cs.repository;

import edu.sbs.cs.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    
    List<Book> findByPublishedDateBetween(LocalDate start, LocalDate end);
    
    List<Book> findByTitleContaining(String keyword);
}
