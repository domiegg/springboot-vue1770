package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *迟到警示：(LateWarning)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "LateWarning")
public class LateWarning implements Serializable {

    //LateWarning编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "late_warning_id")
    private Integer late_warning_id;
    // 警示标题
    @Basic
    private String warning_title;
    // 警示时间
    @Basic
    private Timestamp warning_time;
    // 员工工号
    @Basic
    private Integer employee_job_number;
    // 员工姓名
    @Basic
    private String employee_name;
    // 警示内容
    @Basic
    private String warning_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
