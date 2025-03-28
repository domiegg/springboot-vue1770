package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *迟到人员登记：(LatePersonnelRegistration)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LatePersonnelRegistration")
public class LatePersonnelRegistration implements Serializable {

    //LatePersonnelRegistration编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "late_personnel_registration_id")
    private Integer late_personnel_registration_id;
    // 员工工号
    @Basic
    private Integer employee_job_number;
    // 员工姓名
    @Basic
    private String employee_name;
    // 打卡状态
    @Basic
    private String punch_in_status;
    // 迟到次数
    @Basic
    private String late_times;
    // 备注
    @Basic
    private String remarks;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
