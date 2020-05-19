package com.kanq.user.entiey.pojo;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
//@AllArgsConstructor
@Entity//标志这个Java Bean 承担了JPA配置文件的作用
//@Table(name="表名", schema ="数据库名可省略")
@Table(name="employee")
public class Employee implements Serializable {

    @Id//这个属性映射到表的主键列
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键自增
    private Integer id;

    @Basic//这个属性映射到表的非主键列
    @Column(name="realName")
    private String realName;

    @Column(name="account")
    private String account;

    @Column(name="password")
    private String password;

    @Column(name="phoneNum")
    private String phoneNum;

    @Column(name="userType")
    private String userType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(name="createTime")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    @Column(name="updateTime")
    private Date updateTime;






    public Employee() {
    }

    public Employee(String realName, String account, String password, String phoneNum, String userType, Date createTime, Date updateTime) {
        this.realName = realName;
        this.account = account;
        this.password = password;
        this.phoneNum = phoneNum;
        this.userType = userType;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "employee{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", userType='" + userType + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}
