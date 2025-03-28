package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *工资条：(PaySlip)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "PaySlip")
public class PaySlip implements Serializable {

    //PaySlip编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pay_slip_id")
    private Integer pay_slip_id;
    // 月份
    @Basic
    private String month;
    // 基本工资
    @Basic
    private Integer base_pay;
    // 绩效
    @Basic
    private Integer achievements;
    // 津贴
    @Basic
    private Integer allowance;
    // 加班费
    @Basic
    private Integer overtime_pay;
    // 五险一金
    @Basic
    private Integer five_insurances_and_one_fund;
    // 缺勤扣除
    @Basic
    private Integer absence_deduction;
    // 实发工资
    @Basic
    private String net_salary;
    // 员工工号
    @Basic
    private Integer employee_job_number;
    // 员工姓名
    @Basic
    private String employee_name;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
