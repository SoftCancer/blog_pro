package com.dongl.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dongl.article.entity.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * @Description: 修改审核状态
     * @Date: 2020/3/15 17:01
     **/
    @Modifying
    @Query(value = "UPDATE tb_article SET state = 1 WHERE id = ?" ,nativeQuery = true)
    void  updateState(String id);

    @Modifying
    @Query(value = "UPDATE `tb_article` SET `thumbup` =thumbup+ 1  WHERE `id` = ? ;" ,nativeQuery = true)
    void  addThumbup(String id);


	
}
