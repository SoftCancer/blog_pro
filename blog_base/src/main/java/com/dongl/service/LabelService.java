package com.dongl.service;

import com.dongl.dao.ILabelDao;
import com.dongl.entity.Label;
import com.dongl.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Label> getLabelAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }
}
