package com.dongl.es.controller;

import com.dongl.entity.Result;
import com.dongl.es.entity.ArticleES;
import com.dongl.es.service.ArticleESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 使用 ElasticSearch实现对文章的搜索
 * @author: YaoGuangXun
 * @date: 2020/3/17 17:18
 * @Version: 1.0
 */

@RestController
@RequestMapping("article/es")
@CrossOrigin
public class ArticleESController {

    @Autowired
    private ArticleESService articleESService;

    /**
     * @Description: 向 ElasticSearch 中存储数据
     * @Author: YaoGuangXun
     * @Date: 2020/3/17 17:35
     **/
    @RequestMapping(method = RequestMethod.POST)
    public Result saveArticle(@RequestBody ArticleES articleES){
        articleESService.saveArticleES(articleES);
        return Result.success("添加成功！");
    }

    /**
     * @Description: 实现在 ElasticSearch 中搜索数据。
     * @Author: YaoGuangXun
     * @Date: 2020/3/17 17:52
     **/
    @RequestMapping(value = "/{keyword}/{page}/{size}",method = RequestMethod.GET)
    public Result findArticleByKeyWord(@PathVariable String keyword,@PathVariable int page,@PathVariable int size ){
        Page<ArticleES> articleESPage = articleESService.findArticleByKeyWord(keyword,  page,  size);
        return Result.successPage(articleESPage);
    }
}
