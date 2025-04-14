package com.project.demo.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 员工用户：(EmployeeUsers)表实体类
 *
 */
@TableName("`employee_users`")
@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeUsers implements Serializable {

    // EmployeeUsers编号
    @TableId(value = "employee_users_id", type = IdType.AUTO)
    private Integer employee_users_id;

    // 用户姓名
    @TableField(value = "`user_name`")
    private String user_name;
    // 用户性别
    @TableField(value = "`user_gender`")
    private String user_gender;
    // 手机号码
    @TableField(value = "`mobile_phone_number`")
    private String mobile_phone_number;







    // 用户编号
    @TableField(value = "user_id")
    private Integer userId;



    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
