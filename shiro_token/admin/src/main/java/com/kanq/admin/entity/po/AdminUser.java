package com.kanq.admin.entity.po;

import com.kanq.common.converter.CommonStatusConverter;
import com.kanq.common.enums.CommonStatusEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="admin_user", schema ="books")
public class AdminUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "real_name")
    private String realName;

    private String account;

    private String password;

    @Column(name = "phone_num")
    private String phoneNum;

    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "status")
    @Convert(converter = CommonStatusConverter.class)
    private CommonStatusEnum status;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @OneToOne(fetch = FetchType.EAGER)
    //name： AdminUser实体类在数据库中的字段名
    @JoinColumn(name = "role_id", referencedColumnName = "id",insertable = false, updatable = false)
    private AdminRole role;

}
