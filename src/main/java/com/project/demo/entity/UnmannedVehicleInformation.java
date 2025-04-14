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
 * 无人车信息：(UnmannedVehicleInformation)表实体类
 *
 */
@TableName("`unmanned_vehicle_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class UnmannedVehicleInformation implements Serializable {

    // UnmannedVehicleInformation编号
    @TableId(value = "unmanned_vehicle_information_id", type = IdType.AUTO)
    private Integer unmanned_vehicle_information_id;

    // 员工用户
    @TableField(value = "`employee_users`")
    private Integer employee_users;
    // 无人车名称
    @TableField(value = "`unmanned_vehicle_name`")
    private String unmanned_vehicle_name;
    // 无人车编号
    @TableField(value = "`unmanned_vehicle_number`")
    private String unmanned_vehicle_number;
    // 无人车型号
    @TableField(value = "`unmanned_vehicle_model_number`")
    private String unmanned_vehicle_model_number;
    // 无人车状态
    @TableField(value = "`unmanned_vehicle_status`")
    private String unmanned_vehicle_status;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
