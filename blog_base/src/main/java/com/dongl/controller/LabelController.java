package com.dongl.controller;

import com.dongl.entity.Label;
import com.dongl.entity.Result;
import com.dongl.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Result getAll(){
        List<Label> labels = labelService.getLabelAll();
        return Result.success("查询成功",labels);
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId){
        Label label = labelService.findById(labelId);
        return Result.success("查询成功",label);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return Result.success("添加成功");
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String labelId, @RequestBody Label label){
        Label labelInfo = labelService.findById(labelId);
        if (null == labelInfo){
            return Result.error("标签不存在，修改失败！");
        }
        label.setId(labelId);
        labelService.update(label);
        return Result.success("修改成功");
    }
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("labelId") String labelId){
        Label labelInfo = labelService.findById(labelId);
        if (null == labelInfo){
            return Result.error("标签不存在，删除失败！");
        }
        labelService.deleteById(labelId);
        return Result.success("删除成功");
    }

}
