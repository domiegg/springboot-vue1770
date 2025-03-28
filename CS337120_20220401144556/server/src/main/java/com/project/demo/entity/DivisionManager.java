package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *部门经理：(DivisionManager)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DivisionManager")
public class DivisionManager implements Serializable {

    //DivisionManager编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_manager_id")
    private Integer division_manager_id;
    // 经理姓名
    @Basic
    private String name_of_manager;
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
