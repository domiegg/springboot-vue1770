package com.project.demo.controller;

import com.project.demo.entity.PaySlip;
import com.project.demo.service.PaySlipService;
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
 *工资条：(PaySlip)表控制层
 *
 */
@RestController
@RequestMapping("/pay_slip")
public class PaySlipController extends BaseController<PaySlip,PaySlipService> {

    /**
     *工资条对象
     */
    @Autowired
    public PaySlipController(PaySlipService service) {
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
