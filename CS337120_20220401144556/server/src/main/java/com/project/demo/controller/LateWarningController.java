package com.project.demo.controller;

import com.project.demo.entity.LateWarning;
import com.project.demo.service.LateWarningService;
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
 *迟到警示：(LateWarning)表控制层
 *
 */
@RestController
@RequestMapping("/late_warning")
public class LateWarningController extends BaseController<LateWarning,LateWarningService> {

    /**
     *迟到警示对象
     */
    @Autowired
    public LateWarningController(LateWarningService service) {
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
