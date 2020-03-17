package com.dongl.es.service;

import com.dongl.entity.PageResult;
import com.dongl.es.dao.ArticleESDao;
import com.dongl.es.entity.ArticleES;
import com.dongl.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/17 17:15
 * @Version: 1.0
 */
@Service
public class ArticleESService {

    @Autowired
    private ArticleESDao articleESDao;

    @Autowired
    private IdWorker idWorker;


    public void saveArticleES(ArticleES articleES) {
        articleES.setId(idWorker.nextId() + "");
        articleESDao.save(articleES);
    }

    public Page<ArticleES> findArticleByKeyWord(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<ArticleES> articleESPage = articleESDao.findByTitleOrContentLike(keyword,keyword,pageable);
        return articleESPage;
    }
}
