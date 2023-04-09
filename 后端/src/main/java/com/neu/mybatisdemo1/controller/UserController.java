package com.neu.mybatisdemo1.controller;

import com.github.pagehelper.PageInfo;
import com.neu.mybatisdemo1.entity.Result;
import com.neu.mybatisdemo1.entity.User;
import com.neu.mybatisdemo1.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("getPaged")
    public Result<PageInfo> getPaged(
            @RequestParam(defaultValue = "1") int pageNum,
            @RequestParam(defaultValue = "5") int pageSize,
            @RequestParam(defaultValue = "") String username,
            @RequestParam(defaultValue = "-1") Integer userRoleId
    ){
        PageInfo<User> pageInfo = userService.getPaged(pageNum, pageSize,username,userRoleId);
        return new Result<>(pageInfo);
    }

    @GetMapping("{id}")
    public Result<User> getById(@PathVariable("id") long id){

        return new Result<>(userService.getById(id));
    }

    @PostMapping
    public Result<Integer> insert(@RequestBody User user){
        return new Result<>(userService.insert(user));
    }

    @PutMapping
    public Result<Integer> update(@RequestBody User user){
        return new Result<>(userService.update(user));
    }

    @DeleteMapping("{id}")
    public Result<Integer> delete(@PathVariable("id") long id){
        return new Result<>(userService.delete(id));
    }

    @GetMapping("login")
    public Result<User> login(@RequestParam String userName,@RequestParam String userPassword){
        System.out.println(userService.login(userName,userPassword));
        return new Result<>(userService.login(userName,userPassword));
    }
}
