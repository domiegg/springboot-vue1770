package com.project.demo.controller;

import com.project.demo.entity.LatePersonnelRegistration;
import com.project.demo.service.LatePersonnelRegistrationService;
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
 *迟到人员登记：(LatePersonnelRegistration)表控制层
 *
 */
@RestController
@RequestMapping("/late_personnel_registration")
public class LatePersonnelRegistrationController extends BaseController<LatePersonnelRegistration,LatePersonnelRegistrationService> {

    /**
     *迟到人员登记对象
     */
    @Autowired
    public LatePersonnelRegistrationController(LatePersonnelRegistrationService service) {
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
