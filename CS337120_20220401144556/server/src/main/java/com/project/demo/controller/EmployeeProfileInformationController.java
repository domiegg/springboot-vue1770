package com.project.demo.controller;

import com.project.demo.entity.EmployeeProfileInformation;
import com.project.demo.service.EmployeeProfileInformationService;
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


/**
 *员工档案信息：(EmployeeProfileInformation)表控制层
 *
 */
@RestController
@RequestMapping("/employee_profile_information")
public class EmployeeProfileInformationController extends BaseController<EmployeeProfileInformation,EmployeeProfileInformationService> {

    /**
     *员工档案信息对象
     */
    @Autowired
    public EmployeeProfileInformationController(EmployeeProfileInformationService service) {
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
