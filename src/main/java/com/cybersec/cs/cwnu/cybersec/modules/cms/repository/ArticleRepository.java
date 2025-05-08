package com.cybersec.cs.cwnu.cybersec.modules.cms.repository;

import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    // 基础CRUD方法已由JPA自动实现
}