package com.cybersec.cs.cwnu.cybersec.modules.cms.controller;

import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.Article;
import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.dto.CreateArticleDTO;
import com.cybersec.cs.cwnu.cybersec.modules.cms.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.cybersec.cs.cwnu.cybersec.modules.auth.model.User;
import org.springframework.web.bind.annotation.*;

// controller/ArticleController.java
@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // 结合你的安全配置
    public ResponseEntity<?> createArticle(
            @RequestBody @Valid CreateArticleDTO dto,
            @AuthenticationPrincipal User user
    ) {
        Article article = articleService.createArticle(dto, user.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(article);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #article.authorId == authentication.principal.id")
    public ResponseEntity<?> getArticle(@PathVariable Long id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }
}