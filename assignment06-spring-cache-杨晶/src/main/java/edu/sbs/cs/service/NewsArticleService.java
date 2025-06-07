package edu.sbs.cs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import edu.sbs.cs.entity.NewsArticle;
import edu.sbs.cs.repository.NewsArticleRepository;

@Service
public class NewsArticleService {

    @Autowired
    private NewsArticleRepository newsArticleRepository;

    @Cacheable(value = "newsArticle", key = "#id")
    public Optional<NewsArticle> findById(Long id) {
        return newsArticleRepository.findById(id);
    }

    public List<NewsArticle> findAll() {
        return newsArticleRepository.findAll();
    }

    @CachePut(value = "newsArticle", key = "#result.id")
    public NewsArticle save(NewsArticle newsArticle) {
        return newsArticleRepository.save(newsArticle);
    }

    @CacheEvict(value = "newsArticle", key = "#id")
    public void deleteById(Long id) {
        newsArticleRepository.deleteById(id);
    }
}
