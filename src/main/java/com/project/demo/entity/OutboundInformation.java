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
 * 出库信息：(OutboundInformation)表实体类
 *
 */
@TableName("`outbound_information`")
@Data
@EqualsAndHashCode(callSuper = false)
public class OutboundInformation implements Serializable {

    // OutboundInformation编号
    @TableId(value = "outbound_information_id", type = IdType.AUTO)
    private Integer outbound_information_id;

    // 商品编号
    @TableField(value = "`product_number`")
    private String product_number;
    // 商品名称
    @TableField(value = "`product_name`")
    private String product_name;
    // 商品规格
    @TableField(value = "`product_specifications`")
    private String product_specifications;
    // 出库数量
    @TableField(value = "`outbound_quantity`")
    private Integer outbound_quantity;
    // 出库日期
    @TableField(value = "`outbound_date`")
    private Timestamp outbound_date;
    // 出库备注
    @TableField(value = "`outbound_remarks`")
    private String outbound_remarks;










    // 更新时间
    @TableField(value = "update_time")
    private Timestamp update_time;

    // 创建时间
    @TableField(value = "create_time")
    private Timestamp create_time;







}
