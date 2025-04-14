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
 * 报废信息：(ScrapInformation)表实体类
 *
 */
@TableName("`scrap_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ScrapInformation implements Serializable {

    // ScrapInformation编号
    @TableId(value = "scrap_information_id", type = IdType.AUTO)
    private Integer scrap_information_id;

    // 员工用户
    @TableField(value = "`employee_users`")
    private Integer employee_users;
    // 车辆型号
    @TableField(value = "`vehicle_model`")
    private String vehicle_model;
    // 发动机号
    @TableField(value = "`engine_number`")
    private String engine_number;
    // 车辆架号
    @TableField(value = "`vehicle_frame_number`")
    private String vehicle_frame_number;
    // 车辆车牌
    @TableField(value = "`vehicle_license_plate`")
    private String vehicle_license_plate;
    // 登记日期
    @TableField(value = "`registration_date`")
    private Timestamp registration_date;
    // 备注详情
    @TableField(value = "`note_details`")
    private String note_details;
    // 报废日期
    @TableField(value = "`scrap_date`")
    private Timestamp scrap_date;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
