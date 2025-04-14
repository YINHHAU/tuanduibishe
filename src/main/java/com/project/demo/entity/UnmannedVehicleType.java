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
 * 无人车类型：(UnmannedVehicleType)表实体类
 *
 */
@TableName("`unmanned_vehicle_type`")
@Data
@EqualsAndHashCode(callSuper = false)
public class UnmannedVehicleType implements Serializable {

    // UnmannedVehicleType编号
    @TableId(value = "unmanned_vehicle_type_id", type = IdType.AUTO)
    private Integer unmanned_vehicle_type_id;

    // 车型名称
    @TableField(value = "`model_name`")
    private String model_name;
    // 车辆参数
    @TableField(value = "`vehicle_parameters`")
    private String vehicle_parameters;
    // 最大车速
    @TableField(value = "`maximum_vehicle_speed`")
    private Integer maximum_vehicle_speed;
    // 安全参数
    @TableField(value = "`security_parameter`")
    private String security_parameter;
    // 驱动参数
    @TableField(value = "`drive_parameters`")
    private String drive_parameters;
    // 联网系统
    @TableField(value = "`networking_system`")
    private String networking_system;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
