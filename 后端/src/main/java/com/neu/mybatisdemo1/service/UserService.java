package com.neu.mybatisdemo1.service;

import com.github.pagehelper.PageInfo;
import com.neu.mybatisdemo1.entity.User;

public interface UserService {
    PageInfo<User> getPaged(int pageNum, int pageSize, String userName, Integer userRoleId);

    User getById(Long id);

    int insert(User user);

    int update(User user);

    int delete(Long id);

    User login(String userName, String userPassword);
}
