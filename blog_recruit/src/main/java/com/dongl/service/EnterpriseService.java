package com.dongl.service;

import com.dongl.dao.IEnterpriseDao;
import com.dongl.entity.Enterprise;
import com.dongl.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/14 23:41
 * @Version: 1.0
 */
@Service
public class EnterpriseService {

    @Autowired
    private IEnterpriseDao enterpriseDao;

    @Autowired
    private IdWorker idWorker;

    public List<Enterprise> findByIshot(String ishot) {
        return enterpriseDao.findByIshot(ishot);
    }

    public List<Enterprise> getEnterpriseAll() {
        return enterpriseDao.findAll();
    }

    public Enterprise findById(String id) {
        return enterpriseDao.findById(id).get();
    }

    public void save(Enterprise enterprise) {
        enterprise.setId(idWorker.nextId() + "");
        enterpriseDao.save(enterprise);
    }

    public void update(Enterprise enterprise) {
        enterpriseDao.save(enterprise);
    }

    public void deleteById(String id) {
        enterpriseDao.deleteById(id);
    }

}
