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
 * 供应商信息：(SupplierInformation)表实体类
 *
 */
@TableName("`supplier_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class SupplierInformation implements Serializable {

    // SupplierInformation编号
    @TableId(value = "supplier_information_id", type = IdType.AUTO)
    private Integer supplier_information_id;

    // 供应商姓名
    @TableField(value = "`supplier_name`")
    private String supplier_name;
    // 手机号码
    @TableField(value = "`mobile_phone_number`")
    private String mobile_phone_number;
    // 供应类型
    @TableField(value = "`supply_type`")
    private String supply_type;
    // 供应商品
    @TableField(value = "`supply_of_goods`")
    private String supply_of_goods;
    // 供应时间
    @TableField(value = "`supply_time`")
    private Timestamp supply_time;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
