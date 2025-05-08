package com.cybersec.cs.cwnu.cybersec.modules.cms.modle.dto;

import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.ArticleStatus;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

// model/dto/UpdateArticleDTO.java
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateArticleDTO extends CreateArticleDTO {
    @NotNull
    private ArticleStatus status;
}