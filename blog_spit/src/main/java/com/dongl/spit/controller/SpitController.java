package com.dongl.spit.controller;

import com.dongl.entity.PageResult;
import com.dongl.entity.Result;
import com.dongl.rediesutils.RedisUtil;
import com.dongl.spit.entity.Spit;
import com.dongl.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Description: 吐槽，评论控制类
 * @author: YaoGuangXun
 * @date: 2020/3/16 17:57
 * @Version: 1.0
 */
@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {


    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping(method = RequestMethod.GET)
    public Result getSpitAll() {
        List<Spit> spitList = spitService.findAll();
        return Result.success("查询成功", spitList);
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
    public Result getSpitById(@PathVariable String spitId) {
        Spit spit = spitService.finDById(spitId);
        return Result.success("查询成功", spit);
    }


    /**
     * {"content":"能分享一下资料吗？","publishtime":"2018-05-11T13:58.51Z","userid":"1016","nickname":"dlbvea3260","visits":100,"thumbup":21,"share":1,"comment":32,"state":"1","parentid":"2"}
     *
     * @Description: 吐槽，评论新增
     * @Author: YaoGuangXun
     * @Date: \ 18:49
     **/
    @RequestMapping(method = RequestMethod.POST)
    public Result saveSpit(@RequestBody Spit spit) {
        spitService.save(spit);
        return Result.success("新增成功");
    }

    /**
     * @Description: 修改吐槽/评论
     * @Author: YaoGuangXun
     * @Date: 2020/3/16 18:55
     **/
    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public Result updateSpit(@PathVariable String spitId, @RequestBody Spit spit) {
        spit.set_id(spitId);
        spitService.update(spit);
        return Result.success("修改成功");
    }

    /**
     * @Description: 删除吐槽/评论
     * @Author: YaoGuangXun
     * @Date: 2020/3/16 18:55
     **/
    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return Result.success("删除成功");
    }


    /**
     * @Description: 根据文章的id 获取该文章下的评论和吐槽。
     * @Author: YaoGuangXun
     * @Date: 2020/3/16 19:03
     **/
    @RequestMapping(value = "/comment/{spitId}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentId(@PathVariable String spitId, @PathVariable int page, @PathVariable int size) {
        Page<Spit> spitPage = spitService.findByParentId(spitId, page, size);
        return Result.successPage(spitPage);
    }

    /**
     * @Description: 评论点赞
     * @Author: YaoGuangXun
     * @Date: 2020/3/16 21:46
     **/
    @RequestMapping(value = "/thumbs/{spitId}", method = RequestMethod.PUT)
    public Result giveThumbsUp(@PathVariable String spitId) {

        String userId = "1015";
        String userKey = "thumbs_" + userId;
        boolean userIs = redisUtil.exists(userKey);
        if (userIs) {
            return Result.thumbsupsInfo();
        }
        redisUtil.set(userKey, spitId);
        spitService.giveThumbs(spitId);
        return Result.success("点赞成功！");
    }

}
