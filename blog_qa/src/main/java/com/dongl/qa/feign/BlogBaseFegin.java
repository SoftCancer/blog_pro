package com.dongl.qa.feign;

import com.dongl.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:
 * @author: YaoGuangXun
 * @date: 2020/3/2122:01
 * @Version: 1.0
 */
@FeignClient(value = "blog-base")
public interface BlogBaseFegin {

    @RequestMapping(value = "/label/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId);

}
