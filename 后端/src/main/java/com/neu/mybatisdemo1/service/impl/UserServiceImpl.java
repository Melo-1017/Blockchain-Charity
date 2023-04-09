package com.neu.mybatisdemo1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.mybatisdemo1.entity.User;
import com.neu.mybatisdemo1.mapper.UserMapper;
import com.neu.mybatisdemo1.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public PageInfo<User> getPaged(int pageNum, int pageSize, String userName, Integer userRoleId) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.search("%"+userName+"%",userRoleId);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    @Override
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User login(String userName, String userPassword){
        return userMapper.login(userName,userPassword);
    }
}
