//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.service;

import com.qingqing.demo.dto.ArticleCategoryDto;
import com.qingqing.demo.entity.ArticleCategory;
import com.qingqing.demo.entity.CategoryInfo;
import com.qingqing.demo.mapper.CategoryInfoMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryInfoMapper categoryInfoMapper;

    public CategoryServiceImpl() {
    }

    public void addCategory(CategoryInfo categoryInfo) {
        this.categoryInfoMapper.insert(categoryInfo);
    }

    public void deleteCategoryById(String id) {
        this.categoryInfoMapper.deleteByPrimaryKey(id);
    }

    public void updateCategory(CategoryInfo categoryInfo) {
        this.categoryInfoMapper.updateByPrimaryKey(categoryInfo);
    }

    public void updateArticleCategory(ArticleCategory articleCategory) {
    }

    public CategoryInfo getOneById(String id) {
        return null;
    }

    public List<CategoryInfo> listAllCategory() {
        return null;
    }

    public ArticleCategoryDto getCategoryByArticleId(String id) {
        return null;
    }
}
