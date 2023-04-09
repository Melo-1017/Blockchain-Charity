package com.neu.mybatisdemo1.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private String userName;

    /**
     * 用户性别 0女 1男
     */
    private Integer userGender;

    /**
     * 
     */
    private Integer userAge;

    /**
     * 用户角色 0管理员 1求助人 2帮助人
     */
    private Integer userRoleId;

    /**
     * 
     */
    private String userPassword;

    private String userEmail;

    private String userPhone;

    private static final long serialVersionUID = 1L;
}