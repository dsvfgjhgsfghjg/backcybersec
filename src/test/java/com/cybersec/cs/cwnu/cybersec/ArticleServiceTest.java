package com.cybersec.cs.cwnu.cybersec;

import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.Article;
import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.dto.CreateArticleDTO;
import com.cybersec.cs.cwnu.cybersec.modules.cms.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    private ArticleService service;

    @Test
    void shouldCreateArticleSuccess() {
        CreateArticleDTO dto = new CreateArticleDTO();
        dto.setTitle("测试标题");
        dto.setContent("测试内容");

        Article article = service.createArticle(dto, 1L);
        assertNotNull(article.getId());
    }
}