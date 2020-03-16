package com.dongl.spit.dao;

import com.dongl.spit.entity.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * @Description: 吐槽数据层
 * @author: YaoGuangXun
 * @date: 2020/3/16 17:44
 * @Version: 1.0
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    /**
     * @Description: 根据文章的id 获取该文章下的评论和吐槽。
     * @Author: YaoGuangXun
     * @Date: 2020/3/16 19:03
     **/
   public Page<Spit> findByParentid(String parentId, Pageable pageable);



}
