package com.dongl.service;

import com.dongl.dao.ILabelDao;
import com.dongl.entity.Label;
import com.dongl.utils.IdWorker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/14 15:17
 * @Version: 1.0
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private ILabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> getLabelAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {

        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();

                String state = label.getState();
                String labelName = label.getLabelName();

                if (StringUtils.isNotBlank(labelName)) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelName").as(String.class), labelName + "%");
                    predicateList.add(predicate);
                }
                if (StringUtils.isNotBlank(state)) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), state);
                    predicateList.add(predicate);
                }

                // 定义一个 Predicate 的数组
                Predicate[] predicateArr = new Predicate[predicateList.size()];
                predicateArr = predicateList.toArray(predicateArr);

                Predicate predicate = criteriaBuilder.and(predicateArr);
                return predicate;
            }
        });
    }


    public Page<Label> queryPage(Label label, int page, int size) {

        // 封装 分页对象
        Pageable pageable = PageRequest.of(page - 1, size);

        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();

                String state = label.getState();
                String labelName = label.getLabelName();

                if (StringUtils.isNotBlank(labelName)) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelName").as(String.class), labelName + "%");
                    predicateList.add(predicate);
                }
                if (StringUtils.isNotBlank(state)) {
                    Predicate predicate = criteriaBuilder.equal(root.get("state").as(String.class), state);
                    predicateList.add(predicate);
                }

                // 定义一个 Predicate 的数组
                Predicate[] predicateArr = new Predicate[predicateList.size()];
                predicateArr = predicateList.toArray(predicateArr);

                Predicate predicate = criteriaBuilder.and(predicateArr);
                return predicate;
            }
        }, pageable);

    }
}
