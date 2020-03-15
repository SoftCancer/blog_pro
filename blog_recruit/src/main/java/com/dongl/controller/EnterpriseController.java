package com.dongl.controller;

import com.dongl.entity.Enterprise;
import com.dongl.entity.Result;
import com.dongl.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/14 23:39
 * @Version: 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;



    @RequestMapping(method = RequestMethod.GET)
    public Result getAll() {
        List<Enterprise> enterprise = enterpriseService.getEnterpriseAll();
        return Result.success("查询成功", enterprise);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id) {
        Enterprise enterprise = enterpriseService.findById(id);
        return Result.success("查询成功", enterprise);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Enterprise enterprise) {
        enterpriseService.save(enterprise);
        return Result.success("添加成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable("id") String id, @RequestBody Enterprise enterprise) {
        Enterprise enterpriseInfo = enterpriseService.findById(id);
        if (null == enterpriseInfo) {
            return Result.error("企业不存在，修改失败！");
        }
        enterpriseInfo.setId(id);
        enterpriseService.update(enterprise);
        return Result.success("修改成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id) {
        Enterprise enterpriseInfo = enterpriseService.findById(id);
        if (null == enterpriseInfo) {
            return Result.error("企业不存在，删除失败！");
        }
        enterpriseService.deleteById(id);
        return Result.success("删除成功");
    }


    @RequestMapping(value = "/search/hot", method = RequestMethod.GET)
    public Result findByHot() {
       List<Enterprise> enterpriseList = enterpriseService.findByIshot("1");
        return Result.success("删除成功",enterpriseList);
    }



}
