package com.dongl.controller;

import com.dongl.entity.Label;
import com.dongl.entity.PageResult;
import com.dongl.entity.Result;
import com.dongl.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 标签控制类
 * @author: YaoGuangXun
 * @date: 2020/3/14 1:22
 * @Version: 1.0
 */

@CrossOrigin
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result getAll() {
        List<Label> labels = labelService.getLabelAll();
        return Result.success("查询成功", labels);
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId) {
        Label label = labelService.findById(labelId);
        return Result.success("查询成功", label);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label) {
        labelService.save(label);
        return Result.success("添加成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label) {
        Label labelInfo = labelService.findById(labelId);
        if (null == labelInfo) {
            return Result.error("标签不存在，修改失败！");
        }
        label.setId(labelId);
        labelService.update(label);
        return Result.success("修改成功");
    }

    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("labelId") String labelId) {
        Label labelInfo = labelService.findById(labelId);
        if (null == labelInfo) {
            return Result.error("标签不存在，删除失败！");
        }
        labelService.deleteById(labelId);
        return Result.success("删除成功");
    }


    /**
     * @Description: 条件查询及模糊查询
     * @Author: YaoGuangXun
     * @Date: 2020/3/14 21:58
     **/
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label) {
        List<Label> labelInfo = labelService.findSearch(label);
        return Result.success("查询成功",labelInfo);
    }


    /**
     * @Description: 分页条件查询
     * http://localhost:9001/label/search/1/3
     * @Author: YaoGuangXun
     * @Date: 2020/3/14 22:16
     **/
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result queryPage(@RequestBody Label label,@PathVariable(value = "page") int page ,@PathVariable(value = "size") int size ) {
        Page<Label> labelPage = labelService.queryPage(label,page,size);
        return Result.success("查询成功",new PageResult<>(labelPage.getTotalElements(),labelPage.getContent()));
    }

}
