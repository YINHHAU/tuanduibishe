package com.project.demo.controller;

import com.project.demo.entity.StorageInformation;
import com.project.demo.service.StorageInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 入库信息：(StorageInformation)表控制层
 *
 */
@RestController
@RequestMapping("/storage_information")
public class StorageInformationController extends BaseController<StorageInformation, StorageInformationService> {

    /**
     * 入库信息对象
     */
    @Autowired
    public StorageInformationController(StorageInformationService service) {
        setService(service);
    }



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(storage_information_id) AS max FROM "+"`storage_information`";
        Integer max = service.selectBaseCount(sql);
        sql = "UPDATE `product_inventory` INNER JOIN `storage_information` ON product_inventory.product_number=storage_information.product_number SET product_inventory.inventory_quantity= product_inventory.inventory_quantity + storage_information.inventory_quantity WHERE storage_information.storage_information_id="+max;
        service.updateBaseSql(sql);
        return success(1);
    }


}
