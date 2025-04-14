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
 * 入库信息：(StorageInformation)表实体类
 *
 */
@TableName("`storage_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class StorageInformation implements Serializable {

    // StorageInformation编号
    @TableId(value = "storage_information_id", type = IdType.AUTO)
    private Integer storage_information_id;

    // 商品编号
    @TableField(value = "`product_number`")
    private String product_number;
    // 商品名称
    @TableField(value = "`product_name`")
    private String product_name;
    // 商品规格
    @TableField(value = "`product_specifications`")
    private String product_specifications;
    // 入库数量
    @TableField(value = "`inventory_quantity`")
    private Integer inventory_quantity;
    // 入库日期
    @TableField(value = "`storage_date`")
    private Timestamp storage_date;
    // 入库备注
    @TableField(value = "`storage_remarks`")
    private String storage_remarks;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
