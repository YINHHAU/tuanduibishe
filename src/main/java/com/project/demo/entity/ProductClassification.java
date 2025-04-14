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
 * 商品分类：(ProductClassification)表实体类
 *
 */
@TableName("`product_classification`")
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductClassification implements Serializable {

    // ProductClassification编号
    @TableId(value = "product_classification_id", type = IdType.AUTO)
    private Integer product_classification_id;

    // 类型名称
    @TableField(value = "`type_name`")
    private String type_name;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
