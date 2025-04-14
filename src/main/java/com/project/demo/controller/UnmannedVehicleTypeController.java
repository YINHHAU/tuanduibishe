package com.project.demo.controller;

import com.project.demo.entity.UnmannedVehicleType;
import com.project.demo.service.UnmannedVehicleTypeService;
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
 * 无人车类型：(UnmannedVehicleType)表控制层
 *
 */
@RestController
@RequestMapping("/unmanned_vehicle_type")
public class UnmannedVehicleTypeController extends BaseController<UnmannedVehicleType, UnmannedVehicleTypeService> {

    /**
     * 无人车类型对象
     */
    @Autowired
    public UnmannedVehicleTypeController(UnmannedVehicleTypeService service) {
        setService(service);
    }



    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }


}
