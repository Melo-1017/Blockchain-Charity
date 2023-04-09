package com.neu.mybatisdemo1.controller;

import com.github.pagehelper.PageInfo;
import com.neu.mybatisdemo1.entity.Helplist;
import com.neu.mybatisdemo1.entity.Result;
import com.neu.mybatisdemo1.service.HelplistService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("helplist")
public class HelplistController {
    @Resource
    private HelplistService helplistService;


    @GetMapping("getNewId")
    public Result<Integer> getNewId()
    {
        return new Result<>(helplistService.getNewId());
    }

    @GetMapping("getPaged")
    public Result<PageInfo> getPaged(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "") String donationDemand,
            @RequestParam(defaultValue = "2") Integer donationState
    ){
        PageInfo<Helplist> pageInfo = helplistService.getPaged(pageNum, pageSize,donationDemand,donationState);
        return new Result<>(pageInfo);
    }

    @GetMapping("getPagedByGiverId")
    public Result<PageInfo> getPagedByGiverId(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int giverId
    ){
        System.out.println(giverId);
        PageInfo<Helplist> pageInfo = helplistService.getPagedByGiverId(pageNum, pageSize,giverId);
        return new Result<>(pageInfo);
    }

    @GetMapping("getPagedByRecipientId")
    public Result<PageInfo> getPagedByRecipientId(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "1") int recipientId
    ){
        PageInfo<Helplist> pageInfo = helplistService.getPagedByRecipientId(pageNum, pageSize,recipientId);
        return new Result<>(pageInfo);
    }

    @GetMapping("{id}")
    public Result<Helplist> getById(@PathVariable("id") long id){

        return new Result<>(helplistService.getById(id));
    }

    @PostMapping
    public Result<Integer> insert(@RequestBody Helplist helplist){
        return new Result<>(helplistService.insert(helplist));
    }

    @PutMapping
    public Result<Integer> update(@RequestBody Helplist helplist){
        return new Result<>(helplistService.update(helplist));
    }

    @DeleteMapping("{id}")
    public Result<Integer> delete(@PathVariable("id") long id){
        return new Result<>(helplistService.delete(id));
    }

}
