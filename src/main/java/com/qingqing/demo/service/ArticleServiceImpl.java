//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qingqing.demo.service;

import com.qingqing.demo.dto.ArticleDto;
import com.qingqing.demo.dto.ArticleWithPictureDto;
import com.qingqing.demo.entity.ArticleCategory;
import com.qingqing.demo.entity.ArticleCategoryExample;
import com.qingqing.demo.entity.ArticleContent;
import com.qingqing.demo.entity.ArticleInfo;
import com.qingqing.demo.entity.ArticleInfoExample;
import com.qingqing.demo.entity.ArticlePicture;
import com.qingqing.demo.entity.ArticlePictureExample;
import com.qingqing.demo.entity.CategoryInfo;
import com.qingqing.demo.entity.CategoryInfoExample;
import com.qingqing.demo.entity.CategoryInfoExample.Criteria;
import com.qingqing.demo.mapper.ArticleCategoryMapper;
import com.qingqing.demo.mapper.ArticleContentMapper;
import com.qingqing.demo.mapper.ArticleInfoMapper;
import com.qingqing.demo.mapper.ArticlePictureMapper;
import com.qingqing.demo.mapper.CategoryInfoMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleInfoMapper articleInfoMapper;
    @Resource
    private ArticleCategoryMapper articleCategoryMapper;
    @Resource
    private ArticlePictureMapper articlePictureMapper;
    @Resource
    private ArticleContentMapper articleContentMapper;
    @Resource
    private CategoryInfoMapper categoryInfoMapper;
    @Autowired
    private CategoryService categoryService;

    public ArticleServiceImpl() {
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void addArticle(ArticleDto articleDto) {
        boolean haveCategory = false;
        CategoryInfoExample categoryExampleFind = new CategoryInfoExample();
        Criteria categoryCriteria = categoryExampleFind.createCriteria();
        categoryCriteria.andNameEqualTo(articleDto.getCategoryName());
        List<CategoryInfo> categoryInfo_find = this.categoryInfoMapper.selectByExample(categoryExampleFind);
        if (categoryInfo_find.size() == 0) {
            CategoryInfo categoryInfo_creat = new CategoryInfo();
            categoryInfo_creat.setNumber((byte)0);
            categoryInfo_creat.setName(articleDto.getCategoryName());
            String CategoryUuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            articleDto.setCategoryId(CategoryUuid);
            articleDto.setArticleCategoryId(CategoryUuid);
            categoryInfo_creat.setId(articleDto.getCategoryId());
            categoryInfo_creat.setCreate_by(new Date());
            categoryInfo_creat.setModified_by(new Date());
            this.categoryService.addCategory(categoryInfo_creat);
        } else {
            articleDto.setCategoryId(((CategoryInfo)categoryInfo_find.get(0)).getId());
            articleDto.setArticleCategoryId(articleDto.getCategoryId());
            haveCategory = true;
        }

        ArticleContent articleContent = new ArticleContent();
        articleContent.setId(articleDto.getId());
        articleContent.setArticle_id(articleDto.getArticleContentId());
        articleContent.setContent(articleDto.getContent());
        articleContent.setCreate_by(new Date());
        articleContent.setModifield_by(new Date());
        this.articleContentMapper.insert(articleContent);
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(articleDto.getId());
        articleInfo.setTitle(articleDto.getTitle());
        articleInfo.setIs_top(articleDto.getTop());
        articleInfo.setSummary(articleDto.getSummary());
        articleInfo.setTraffic(articleDto.getTraffic());
        articleInfo.setCreate_by(new Date());
        articleInfo.setModified_by(new Date());
        this.articleInfoMapper.insert(articleInfo);
        ArticlePicture articlePicture = new ArticlePicture();
        articlePicture.setId(articleDto.getId());
        articlePicture.setPicture_url(articleDto.getPictureUrl());
        articlePicture.setArticle_id(articleDto.getArticleContentId());
        articlePicture.setCreate_by(new Date());
        articlePicture.setModified_by(new Date());
        this.articlePictureMapper.insert(articlePicture);
        ArticleCategory articleCategory = new ArticleCategory();
        articleCategory.setId(UUID.randomUUID().toString().replace("-", "").toLowerCase());
        articleCategory.setSort_id(articleDto.getArticleCategoryId());
        articleCategory.setArticle_id(articleDto.getArticleContentId());
        articleCategory.setCreate_by(new Date());
        articleCategory.setModified_by(new Date());
        this.articleCategoryMapper.insert(articleCategory);
        CategoryInfo categoryInfo = this.categoryInfoMapper.selectByPrimaryKey(articleDto.getArticleCategoryId());
        categoryInfo.setNumber((byte)(categoryInfo.getNumber() + 1));
        if (haveCategory) {
            categoryInfo.setModified_by(new Date());
        }

        this.categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void deleteArticleById(String id) {
        this.articleInfoMapper.deleteByPrimaryKey(id);
        this.articleContentMapper.deleteByPrimaryKey(id);
        ArticlePictureExample example1 = new ArticlePictureExample();
        com.qingqing.demo.entity.ArticlePictureExample.Criteria criteria = example1.createCriteria();
        criteria.andArticle_idEqualTo(id);
        ArticlePicture articlePicture = (ArticlePicture)this.articlePictureMapper.selectByExample(example1).get(0);
        this.articlePictureMapper.deleteByPrimaryKey(articlePicture.getId());
        ArticleCategoryExample example2 = new ArticleCategoryExample();
        com.qingqing.demo.entity.ArticleCategoryExample.Criteria criteria1 = example2.createCriteria();
        criteria1.andArticle_idEqualTo(id);
        ArticleCategory articleCategory = (ArticleCategory)this.articleCategoryMapper.selectByExample(example2).get(0);
        this.articleCategoryMapper.deleteByPrimaryKey(articleCategory.getId());
        CategoryInfo categoryInfo = this.categoryInfoMapper.selectByPrimaryKey(articleCategory.getSort_id());
        if (categoryInfo.getNumber() > 1) {
            categoryInfo.setNumber((byte)(categoryInfo.getNumber() - 1));
            this.categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
        } else {
            this.categoryInfoMapper.deleteByPrimaryKey(categoryInfo.getId());
        }

    }

    @Transactional(
            rollbackFor = {Exception.class}
    )
    public void updateArticle(ArticleDto articleDto) {
        ArticleContent articleContent = this.articleContentMapper.selectByPrimaryKey(articleDto.getId());
        articleContent.setArticle_id(articleDto.getArticleContentId());
        articleContent.setContent(articleDto.getContent());
        articleContent.setModifield_by(new Date());
        this.articleContentMapper.updateByPrimaryKeySelective(articleContent);
        ArticleInfo articleInfo = this.articleInfoMapper.selectByPrimaryKey(articleDto.getId());
        articleInfo.setId(articleDto.getId());
        articleInfo.setTitle(articleDto.getTitle());
        articleInfo.setIs_top(articleDto.getTop());
        articleInfo.setSummary(articleDto.getSummary());
        articleInfo.setTraffic(articleDto.getTraffic());
        articleInfo.setModified_by(new Date());
        this.articleInfoMapper.updateByPrimaryKey(articleInfo);
        ArticlePictureExample articlePictureExample = new ArticlePictureExample();
        com.qingqing.demo.entity.ArticlePictureExample.Criteria articlePicturecriteria = articlePictureExample.createCriteria();
        articlePicturecriteria.andArticle_idEqualTo(articleDto.getArticleContentId());
        List<ArticlePicture> articlePictures = this.articlePictureMapper.selectByExample(articlePictureExample);
        ArticlePicture articlePicture = (ArticlePicture)articlePictures.get(0);
        articlePicture.setPicture_url(articleDto.getPictureUrl());
        articlePicture.setArticle_id(articleDto.getArticleContentId());
        articlePicture.setModified_by(new Date());
        this.articlePictureMapper.updateByPrimaryKey(articlePicture);
        ArticleCategoryExample articleCategoryExample = new ArticleCategoryExample();
        com.qingqing.demo.entity.ArticleCategoryExample.Criteria articleCategorycriteria = articleCategoryExample.createCriteria();
        articleCategorycriteria.andArticle_idEqualTo(articleDto.getArticleContentId());
        List<ArticleCategory> articleCategory_find = this.articleCategoryMapper.selectByExample(articleCategoryExample);
        String oldSort_id = ((ArticleCategory)articleCategory_find.get(0)).getSort_id();
        CategoryInfo categoryInfo_find = this.categoryInfoMapper.selectByPrimaryKey(((ArticleCategory)articleCategory_find.get(0)).getSort_id());
        if (categoryInfo_find.getNumber() > 0) {
            categoryInfo_find.setNumber((byte)(categoryInfo_find.getNumber() - 1));
            this.categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo_find);
        }

        CategoryInfoExample categoryExampleFind = new CategoryInfoExample();
        Criteria categoryCriteria = categoryExampleFind.createCriteria();
        categoryCriteria.andNameEqualTo(articleDto.getCategoryName());
        List<CategoryInfo> categoryInfo_findByname = this.categoryInfoMapper.selectByExample(categoryExampleFind);
        if (categoryInfo_findByname.size() == 0) {
            CategoryInfo categoryInfo_creat = new CategoryInfo();
            categoryInfo_creat.setNumber((byte)0);
            categoryInfo_creat.setName(articleDto.getCategoryName());
            String CategoryUuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            articleDto.setCategoryId(CategoryUuid);
            articleDto.setArticleCategoryId(CategoryUuid);
            categoryInfo_creat.setId(articleDto.getCategoryId());
            categoryInfo_creat.setCreate_by(new Date());
            categoryInfo_creat.setModified_by(new Date());
            this.categoryService.addCategory(categoryInfo_creat);
        } else {
            articleDto.setCategoryId(((CategoryInfo)categoryInfo_findByname.get(0)).getId());
            articleDto.setArticleCategoryId(articleDto.getCategoryId());
        }

        ArticleCategoryExample articleCategoryExample1 = new ArticleCategoryExample();
        com.qingqing.demo.entity.ArticleCategoryExample.Criteria articleCategoryCriteria = articleCategoryExample1.createCriteria();
        articleCategoryCriteria.andArticle_idEqualTo(articleDto.getId());
        List<ArticleCategory> articleCategories = this.articleCategoryMapper.selectByExample(articleCategoryExample1);
        ArticleCategory articleCategory = (ArticleCategory)articleCategories.get(0);
        articleCategory.setSort_id(articleDto.getArticleCategoryId());
        articleCategory.setArticle_id(articleDto.getArticleContentId());
        articleCategory.setModified_by(new Date());
        this.articleCategoryMapper.updateByPrimaryKey(articleCategory);
        CategoryInfo categoryInfo = this.categoryInfoMapper.selectByPrimaryKey(articleDto.getArticleCategoryId());
        categoryInfo.setNumber((byte)(categoryInfo.getNumber() + 1));
        categoryInfo.setModified_by(new Date());
        this.categoryInfoMapper.updateByPrimaryKeySelective(categoryInfo);
        CategoryInfo categoryInfo_findNew = this.categoryInfoMapper.selectByPrimaryKey(oldSort_id);
        if (categoryInfo_findNew.getNumber() <= 0) {
            this.categoryInfoMapper.deleteByPrimaryKey(oldSort_id);
        }

    }

    public void updateArticleCategory(String articleId, String categoryId) {
    }

    public ArticleDto getOneById(String id, Map<String, String> datamap, String isvisited) {
        ArticleDto articleDto = new ArticleDto();
        ArticleInfo articleInfo = this.articleInfoMapper.selectByPrimaryKey(id);
        articleDto.setTitle(articleInfo.getTitle());
        articleDto.setTop(articleInfo.getIs_top());
        articleDto.setTraffic(articleInfo.getTraffic());
        articleDto.setSummary(articleInfo.getSummary());
        articleDto.setId(articleInfo.getId());
        if (isvisited != "true") {
            articleInfo.setTraffic(articleInfo.getTraffic() + 1);
            this.articleInfoMapper.updateByPrimaryKey(articleInfo);
        }

        ArticleContent articleContent = this.articleContentMapper.selectByPrimaryKey(id);
        articleDto.setContent(articleContent.getContent());
        Date date = articleContent.getCreate_by();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date mdate = articleContent.getModifield_by();
        datamap.put("creat", format.format(date));
        datamap.put("modify", format.format(mdate));
        ArticleCategoryExample example2 = new ArticleCategoryExample();
        com.qingqing.demo.entity.ArticleCategoryExample.Criteria criteria1 = example2.createCriteria();
        criteria1.andArticle_idEqualTo(id);
        ArticleCategory articleCategory = (ArticleCategory)this.articleCategoryMapper.selectByExample(example2).get(0);
        articleDto.setArticleCategoryId(articleCategory.getSort_id());
        ArticlePictureExample example1 = new ArticlePictureExample();
        com.qingqing.demo.entity.ArticlePictureExample.Criteria criteria = example1.createCriteria();
        criteria.andArticle_idEqualTo(id);
        ArticlePicture articlePicture = (ArticlePicture)this.articlePictureMapper.selectByExample(example1).get(0);
        articleDto.setPictureUrl(articlePicture.getPicture_url());
        articleDto.setArticlePictureId(articlePicture.getId());
        CategoryInfo categoryInfo = this.categoryInfoMapper.selectByPrimaryKey(articleCategory.getSort_id());
        articleDto.setCategoryName(categoryInfo.getName());
        articleDto.setCategoryNumber(categoryInfo.getNumber());
        articleDto.setCategoryId(categoryInfo.getId());
        return articleDto;
    }

    public ArticlePicture getPictureByArticleId(String id) {
        return null;
    }

    public List<ArticleWithPictureDto> listAll() {
        ArticleInfoExample example = new ArticleInfoExample();
        example.setOrderByClause("create_by desc");
        List<ArticleInfo> articleInfos = this.articleInfoMapper.selectByExample(example);
        List<ArticleWithPictureDto> articles = new ArrayList();
        Iterator var4 = articleInfos.iterator();

        while(var4.hasNext()) {
            ArticleInfo articleInfo = (ArticleInfo)var4.next();
            ArticleWithPictureDto articleWithPictureDto = new ArticleWithPictureDto();
            articleWithPictureDto.setId(articleInfo.getId());
            articleWithPictureDto.setTitle(articleInfo.getTitle());
            articleWithPictureDto.setSummary(articleInfo.getSummary());
            articleWithPictureDto.setTop(articleInfo.getIs_top());
            articleWithPictureDto.setTraffic(articleInfo.getTraffic());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = sdf.format(articleInfo.getCreate_by());
            articleWithPictureDto.setDate(str);
            ArticlePictureExample example1 = new ArticlePictureExample();
            com.qingqing.demo.entity.ArticlePictureExample.Criteria criteria = example1.createCriteria();
            criteria.andIdEqualTo(articleInfo.getId());
            ArticlePicture articlePicture = (ArticlePicture)this.articlePictureMapper.selectByExample(example1).get(0);
            articleWithPictureDto.setArticlePictureId(articlePicture.getId());
            articleWithPictureDto.setPictureUrl(articlePicture.getPicture_url());
            articles.add(articleWithPictureDto);
        }

        return articles;
    }

    public List<ArticleWithPictureDto> listByCategoryId(String id) {
        return null;
    }

    public List<ArticleWithPictureDto> listLastest() {
        return null;
    }
}
