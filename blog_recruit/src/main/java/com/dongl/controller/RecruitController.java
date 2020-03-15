package com.dongl.controller;

import com.dongl.entity.PageResult;
import com.dongl.entity.Recruit;
import com.dongl.entity.Result;
import com.dongl.service.RecruitService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/15 1:28
 * @Version: 1.0
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;


    @RequestMapping(method = RequestMethod.GET)
    public Result getRecruitAll() {
        List<Recruit> recruitList = recruitService.getRecruitAll();
        return Result.success("查询成功！",recruitList);
    }


    @RequestMapping(value = "/{recruitId}" ,method = RequestMethod.GET)
    public Result getRecruitById(@PathVariable String recruitId) {
        Recruit recruit = recruitService.getRecruitById(recruitId);
        return Result.success("查询成功！",recruit);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result saveRecruit(@RequestBody Recruit recruit) {
        recruitService.saveRecruit(recruit);
        return Result.success("新增成功！");
    }

    @RequestMapping(value = "/{recruitId}" ,method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String recruitId) {
        if (StringUtils.isNotBlank(recruitId)){
            return Result.error("职位不存在，删除失败！");
        }
        recruitService.deleteById(recruitId);
        return Result.success("删除成功！");
    }


    @RequestMapping(value = "/{recruitId}" ,method = RequestMethod.PUT)
    public Result updateRecruit(@RequestBody Recruit recruit,String recruitId) {
        if (StringUtils.isNotBlank(recruitId)){
            return Result.error("职位不存在，修改失败！");
        }
        recruit.setId(recruitId);
        recruitService.updateRecruit(recruit);
        return Result.success("修改成功！");
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result getRecruitBySearch(@RequestBody Recruit recruit) {
        List<Recruit> recruitList = recruitService.getRecruitBySearch(recruit);
        return Result.success("查询成功！",recruitList);
    }

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result getRecruitBySearch(@RequestBody Recruit recruit,@PathVariable int page ,@PathVariable int size) {
        Page<Recruit> recruitPage = recruitService.querySearchPage(recruit,page,size);
        return Result.success("查询成功！",new PageResult<>(recruitPage.getTotalElements(),recruitPage.getContent()));
    }

    @RequestMapping(value = "/search/recommend",method = RequestMethod.GET)
    public Result getRecommendByState() {
        List<Recruit> recruitList = recruitService.getRecommendByState("2");
        return Result.success("查询成功！",recruitList);
    }

    @RequestMapping(value = "/search/position",method = RequestMethod.GET)
    public Result getNewPositionByState() {
        List<Recruit> recruitList = recruitService.getNewPositionByState("0");
        return Result.success("查询成功！",recruitList);
    }








}
