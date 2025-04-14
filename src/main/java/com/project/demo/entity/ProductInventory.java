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
 * 商品仓库：(ProductInventory)表实体类
 *
 */
@TableName("`product_inventory`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductInventory implements Serializable {

    // ProductInventory编号
    @TableId(value = "product_inventory_id", type = IdType.AUTO)
    private Integer product_inventory_id;

    // 商品名称
    @TableField(value = "`product_name`")
    private String product_name;
    // 商品编号
    @TableField(value = "`product_number`")
    private String product_number;
    // 商品类型
    @TableField(value = "`product_type`")
    private String product_type;
    // 商品规格
    @TableField(value = "`product_specifications`")
    private String product_specifications;
    // 库存数量
    @TableField(value = "`inventory_quantity`")
    private Integer inventory_quantity;
    // 备注详情
    @TableField(value = "`note_details`")
    private String note_details;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
