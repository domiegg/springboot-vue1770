package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *普通用户：(OrdinaryUsers)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OrdinaryUsers")
public class OrdinaryUsers implements Serializable {

    //OrdinaryUsers编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordinary_users_id")
    private Integer ordinary_users_id;
    // 员工工号
    @Basic
    private String employee_job_number;
    // 员工姓名
    @Basic
    private String employee_name;
    // 性别
    @Basic
    private String gender;
    // 年龄
    @Basic
    private String age;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
