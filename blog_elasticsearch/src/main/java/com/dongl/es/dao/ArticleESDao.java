package com.dongl.es.dao;

import com.dongl.es.entity.ArticleES;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/17 17:14
 * @Version: 1.0
 */
public interface ArticleESDao extends ElasticsearchRepository<ArticleES,String> {


    /**
     *  实现搜索数据 title 和 Content 在 ElasticSearch 中。
     * @Author: YaoGuangXun
     * @Date: 2020/3/17 17:52
     **/
    public Page<ArticleES> findByTitleOrContentLike(String title, String content, Pageable pageable);

}
