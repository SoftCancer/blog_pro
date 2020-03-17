package com.dongl.spit.service;

import com.dongl.spit.dao.SpitDao;
import com.dongl.spit.entity.Spit;
import com.dongl.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.provider.ConfigFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/16 17:49
 * @Version: 1.0
 */
@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit finDById(String id) {
        Optional<Spit> spit = spitDao.findById(id);
        return spit.get();
    }


    public void save(Spit spit) {

        spit.set_id(idWorker.nextId() + "");
        /** 发布日期 **/
        spit.setPublishtime(new Date());
        /** 浏览量 **/
        spit.setVisits(0);
        /** 分享量 **/
        spit.setShare(0);
        /** 点赞数 **/
        spit.setThumbup(0);
        /** 评论数 **/
        spit.setComment(0);
        /** 状态 **/
        spit.setState("1");

        /** 实现文章总评论数的计算 **/
        String parentId = spit.getParentid();
        /** 如果 parentId 不为空 说明是对文章的评论 ，否则说明是新文章 或新评论 **/
        if (null != parentId && !"".equals(parentId)) {

            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(parentId));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }

        spitDao.save(spit);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentId(String id, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Spit> spitPage = spitDao.findByParentid(id, pageable);
        return spitPage;
    }


    public void giveThumbs(String spitId) {

        /** 方式一、 操作两次数据库，效率低
         Spit spit = spitDao.findById(spitId).get();
         Integer thumbup = spit.getThumbup();
         spit.setThumbup(thumbup == null ? 1 : thumbup + 1);
         spitDao.save(spit);
         **/

        // 方式二、 使用MongoDB的自增
        // 封装条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        // 实现自增操作
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");

    }


}
