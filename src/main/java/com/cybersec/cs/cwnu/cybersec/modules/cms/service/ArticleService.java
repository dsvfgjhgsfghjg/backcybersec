package com.cybersec.cs.cwnu.cybersec.modules.cms.service;

import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.Article;
import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.dto.CreateArticleDTO;
import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.dto.UpdateArticleDTO;
import com.cybersec.cs.cwnu.cybersec.modules.cms.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

// service/ArticleService.java
@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepo;

    // 创建文章
    @Transactional
    public Article createArticle(CreateArticleDTO dto, Long authorId) {
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setAuthorId(authorId);
        return articleRepo.save(article);
    }

    // 更新文章
    @Transactional
    public Article updateArticle(Long id, UpdateArticleDTO dto) {
        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("文章不存在"));

        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setStatus(dto.getStatus());
        article.setUpdateTime(LocalDateTime.now());
        return articleRepo.save(article);
    }

    //根据id获取文章
    @Transactional
    public Article getArticleById(Long id) {
        return articleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("文章不存在"));
    }
}
