package com.dongl.qa.controller;

import com.dongl.entity.PageResult;
import com.dongl.entity.Result;
import com.dongl.qa.entity.Problem;
import com.dongl.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 问答控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return Result.success("查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return Result.success("查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  Result.success( "查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return  Result.success( "查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		problemService.add(problem);
		return  Result.success( "增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		problem.setId(id);
		problemService.update(problem);		
		return Result.success("修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		problemService.deleteById(id);
		return Result.success("删除成功");
	}

    @RequestMapping(value="/new/{labelId}/{page}/{size}",method= RequestMethod.GET)
	public Result getNewProblem(@PathVariable String labelId,@PathVariable int page,@PathVariable int size){
	    Page<Problem> problemPage = problemService.getNewProblem(labelId,page,size);
	    return Result.success("查询成功",new PageResult<>(problemPage.getTotalElements(),problemPage.getContent()));
    }
	
    @RequestMapping(value="/hot/{labelId}/{page}/{size}",method= RequestMethod.GET)
	public Result getHotProblem(@PathVariable String labelId,@PathVariable int page,@PathVariable int size){
	    Page<Problem> problemPage = problemService.getHotProblem(labelId,page,size);
	    return Result.success("查询成功",new PageResult<>(problemPage.getTotalElements(),problemPage.getContent()));
    }

    @RequestMapping(value="/wait/{labelId}/{page}/{size}",method= RequestMethod.GET)
	public Result getWaitProblem(@PathVariable String labelId,@PathVariable int page,@PathVariable int size){
	    Page<Problem> problemPage = problemService.getWaitProblem(labelId,page,size);
	    return Result.success("查询成功",new PageResult<>(problemPage.getTotalElements(),problemPage.getContent()));
    }

}
