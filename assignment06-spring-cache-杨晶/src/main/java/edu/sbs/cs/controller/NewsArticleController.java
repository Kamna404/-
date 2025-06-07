package edu.sbs.cs.controller;

import edu.sbs.cs.entity.NewsArticle;
import edu.sbs.cs.service.NewsArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/news")
@Tag(name = "新闻文章管理", description = "新闻文章的增删改查接口")
public class NewsArticleController {

    @Autowired
    private NewsArticleService newsArticleService;

    @GetMapping("/{id}")
    @Operation(summary = "根据ID获取新闻文章", description = "通过ID查询新闻文章详情")
    public ResponseEntity<NewsArticle> getNewsArticleById(@PathVariable Long id) {
        Optional<NewsArticle> newsArticle = newsArticleService.findById(id);
        return newsArticle.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "获取所有新闻文章", description = "查询所有新闻文章列表")
    public List<NewsArticle> getAllNewsArticles() {
        return newsArticleService.findAll();
    }

    @PostMapping
    @Operation(summary = "创建新闻文章", description = "添加新的新闻文章")
    public NewsArticle createNewsArticle(@RequestBody NewsArticle newsArticle) {
        return newsArticleService.save(newsArticle);
    }

    @PutMapping("/{id}")
    @Operation(summary = "更新新闻文章", description = "根据ID更新新闻文章内容")
    public ResponseEntity<NewsArticle> updateNewsArticle(@PathVariable Long id, @RequestBody NewsArticle newsArticle) {
        Optional<NewsArticle> existingArticle = newsArticleService.findById(id);
        if (existingArticle.isPresent()) {
            newsArticle.setId(id);
            return ResponseEntity.ok(newsArticleService.save(newsArticle));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除新闻文章", description = "根据ID删除新闻文章")
    public ResponseEntity<Void> deleteNewsArticle(@PathVariable Long id) {
        Optional<NewsArticle> existingArticle = newsArticleService.findById(id);
        if (existingArticle.isPresent()) {
            newsArticleService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
