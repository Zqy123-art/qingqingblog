//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.service;

import com.qingqing.demo.dto.ArticleDto;
import com.qingqing.demo.dto.ArticleWithPictureDto;
import com.qingqing.demo.entity.ArticlePicture;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {
    void addArticle(ArticleDto articleDto);

    void deleteArticleById(String id);

    void updateArticle(ArticleDto articleDto);

    void updateArticleCategory(String articleId, String categoryId);

    ArticleDto getOneById(String id, Map<String, String> datamap, String isvisited);

    ArticlePicture getPictureByArticleId(String id);

    List<ArticleWithPictureDto> listAll();

    List<ArticleWithPictureDto> listByCategoryId(String id);

    List<ArticleWithPictureDto> listLastest();
}
