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
 * 商品信息：(ProductInformation)表实体类
 *
 */
@TableName("`product_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductInformation implements Serializable {

    // ProductInformation编号
    @TableId(value = "product_information_id", type = IdType.AUTO)
    private Integer product_information_id;

    // 员工用户
    @TableField(value = "`employee_users`")
    private Integer employee_users;
    // 商品名称
    @TableField(value = "`product_name`")
    private String product_name;
    // 商品类型
    @TableField(value = "`product_type`")
    private String product_type;
    // 商品价格
    @TableField(value = "`commodity_price`")
    private Integer commodity_price;
    // 库存数量
    @TableField(value = "`inventory_quantity`")
    private Integer inventory_quantity;
    // 商品简介
    @TableField(value = "`product_introduction`")
    private String product_introduction;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
