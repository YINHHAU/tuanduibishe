package com.project.demo.controller;

import com.project.demo.entity.UnmannedVehicleInformation;
import com.project.demo.service.UnmannedVehicleInformationService;
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
 * 无人车信息：(UnmannedVehicleInformation)表控制层
 *
 */
@RestController
@RequestMapping("/unmanned_vehicle_information")
public class UnmannedVehicleInformationController extends BaseController<UnmannedVehicleInformation, UnmannedVehicleInformationService> {

    /**
     * 无人车信息对象
     */
    @Autowired
    public UnmannedVehicleInformationController(UnmannedVehicleInformationService service) {
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
