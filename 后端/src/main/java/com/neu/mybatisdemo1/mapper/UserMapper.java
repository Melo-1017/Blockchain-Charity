package com.neu.mybatisdemo1.mapper;

import com.neu.mybatisdemo1.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-07-17 15:32:09
* @Entity com.neu.mybatisdemo1.entity.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> search(@Param("userName") String userName,@Param("userRoleId") int userRoleId);

    User login(@Param("userName") String userName,@Param("userPassword") String userPassword);

}
