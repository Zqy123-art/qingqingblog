//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.service;

import com.qingqing.demo.dto.ArticleCategoryDto;
import com.qingqing.demo.entity.ArticleCategory;
import com.qingqing.demo.entity.CategoryInfo;
import java.util.List;

public interface CategoryService {
    void addCategory(CategoryInfo categoryInfo);

    void deleteCategoryById(String id);

    void updateCategory(CategoryInfo categoryInfo);

    void updateArticleCategory(ArticleCategory articleCategory);

    CategoryInfo getOneById(String id);

    List<CategoryInfo> listAllCategory();

    ArticleCategoryDto getCategoryByArticleId(String id);
}
