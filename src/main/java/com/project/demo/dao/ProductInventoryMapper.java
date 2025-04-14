package com.project.demo.dao;

import com.project.demo.dao.base.BaseMapper;
import com.project.demo.entity.ProductInventory;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品仓库：(ProductInventory)Mapper接口
 *
 */
@Mapper
public interface ProductInventoryMapper extends BaseMapper<ProductInventory>{

}
