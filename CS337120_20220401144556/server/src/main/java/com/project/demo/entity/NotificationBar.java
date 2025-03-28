package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *通知栏：(NotificationBar)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "NotificationBar")
public class NotificationBar implements Serializable {

    //NotificationBar编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_bar_id")
    private Integer notification_bar_id;
    // 通知标题
    @Basic
    private String notice_title;
    // 通知时间
    @Basic
    private Timestamp notification_time;
    // 员工工号
    @Basic
    private Integer employee_job_number;
    // 员工姓名
    @Basic
    private String employee_name;
    // 通知内容
    @Basic
    private String notice_content;
    // 员工回复
    @Basic
    private String employee_reply;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
