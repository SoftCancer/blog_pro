package com.dongl.service;

import com.dongl.dao.IRecruitDao;
import com.dongl.entity.Recruit;
import com.dongl.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 招聘职位Service 层
 * @author: YaoGuangXun
 * @date: 2020/3/15 0:58
 * @Version: 1.0
 */
@Service
@CrossOrigin
public class RecruitService {

    @Autowired
    private IRecruitDao recruitDao;

    @Autowired
    private IdWorker idWorker;


    public List<Recruit> getRecruitAll(){
        return recruitDao.findAll();
    }

    public Recruit getRecruitById(String id){
        return recruitDao.findById(id).get();
    }

    public void saveRecruit(Recruit recruit){
        recruit.setId(idWorker.nextId()+"");
        recruitDao.save(recruit);
    }

    public void deleteById(String id){
        recruitDao.deleteById(id);
    }

    public void updateRecruit(Recruit recruit){
        recruitDao.save(recruit);
    }

    public List<Recruit> getRecruitBySearch(Recruit recruit){
        return recruitDao.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                String jobName = recruit.getJobName();
                String  type = recruit.getType();
                String condition = recruit.getCondition();

                if (!StringUtils.isEmpty(jobName)){
                    Predicate predicate = criteriaBuilder.like(root.get("jobName").as(String.class),jobName+"%");
                    predicateList.add(predicate);
                }
                if (!StringUtils.isEmpty(condition)){
                    Predicate predicate = criteriaBuilder.like(root.get("condition").as(String.class),condition+"%");
                    predicateList.add(predicate);
                }

                if (!StringUtils.isEmpty(type)){
                    Predicate predicate = criteriaBuilder.equal(root.get("type").as(String.class),type);
                    predicateList.add(predicate);
                }

                Predicate[] predicates = new Predicate[predicateList.size()];
                predicates = predicateList.toArray(predicates);

                return criteriaBuilder.and(predicates);
            }
        });
    }

    public Page<Recruit> querySearchPage(Recruit recruit, int page, int size){
        Pageable pageable = PageRequest.of(page-1,size);

        return recruitDao.findAll(new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                String jobName = recruit.getJobName();
                String  type = recruit.getType();
                String condition = recruit.getCondition();

                if (!StringUtils.isEmpty(jobName)){
                    Predicate predicate = criteriaBuilder.like(root.get("jobName").as(String.class),"%"+jobName+"%");
                    predicateList.add(predicate);
                }
                if (!StringUtils.isEmpty(condition)){
                    Predicate predicate = criteriaBuilder.like(root.get("condition").as(String.class),"%"+condition+"%");
                    predicateList.add(predicate);
                }

                if (!StringUtils.isEmpty(type)){
                    Predicate predicate = criteriaBuilder.equal(root.get("type").as(String.class),type);
                    predicateList.add(predicate);
                }

                Predicate[] predicates = new Predicate[predicateList.size()];
                predicates = predicateList.toArray(predicates);

                return criteriaBuilder.and(predicates);
            }
        },pageable);
    }

    public List<Recruit> getRecommendByState(String state){
        return recruitDao.findTop6ByStateOrderByCreateTimeDesc(state);
    }

    public List<Recruit> getNewPositionByState(String state){
        return recruitDao.findTop6ByStateNotOrderByCreateTimeDesc(state);
    }



}
