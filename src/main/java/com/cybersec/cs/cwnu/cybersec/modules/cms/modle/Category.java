package com.cybersec.cs.cwnu.cybersec.modules.cms.modle;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

// model/Category.java
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    // 在Article实体中添加
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // 查询方法
    public interface ArticleRepository extends JpaRepository<Article, Long> {
        Page<Article> findByCategoryIdAndStatus(Long categoryId, ArticleStatus status, Pageable pageable);
    }
}
