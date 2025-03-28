package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *上班打卡：(ClockIn)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ClockIn")
public class ClockIn implements Serializable {

    //ClockIn编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clock_in_id")
    private Integer clock_in_id;
    // 打卡标题
    @Basic
    private String punch_in_title;
    // 上班打卡时间
    @Basic
    private Timestamp clock_in_time;
    // 员工工号
    @Basic
    private Integer employee_job_number;
    // 员工姓名
    @Basic
    private String employee_name;
    // 打卡状态
    @Basic
    private String punch_in_status;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
