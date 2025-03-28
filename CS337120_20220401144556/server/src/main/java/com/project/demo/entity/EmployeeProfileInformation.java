package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *员工档案信息：(EmployeeProfileInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "EmployeeProfileInformation")
public class EmployeeProfileInformation implements Serializable {

    //EmployeeProfileInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_profile_information_id")
    private Integer employee_profile_information_id;
    // 员工编号
    @Basic
    private String employee_number;
    // 员工姓名
    @Basic
    private String employee_name;
    // 性别
    @Basic
    private String gender;
    // 年龄
    @Basic
    private String age;
    // 职位
    @Basic
    private String position;
    // 员工状态
    @Basic
    private String employee_status;
    // 入职日期
    @Basic
    private Timestamp entry_date;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
