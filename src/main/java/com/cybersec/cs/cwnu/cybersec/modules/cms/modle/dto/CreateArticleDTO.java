package com.cybersec.cs.cwnu.cybersec.modules.cms.modle.dto;

import com.cybersec.cs.cwnu.cybersec.modules.cms.modle.ArticleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

// model/dto/CreateArticleDTO.java
@Data
public class CreateArticleDTO {
    @NotBlank(message = "标题不能为空")
    @Size(max = 200, message = "标题最长200字符")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;
}

