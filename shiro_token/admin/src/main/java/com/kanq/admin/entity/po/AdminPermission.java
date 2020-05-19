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
@Table(name="admin_permission", schema ="books")
public class AdminPermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    private String name;

    private String url;

    private String permission;//权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:

    private Integer sort;

    //权限标识
    //private String mark;

    private String introduction;

    @Column(name = "state")
    @Convert(converter = CommonStatusConverter.class)
    private CommonStatusEnum state;

    @Column(name = "status")
    @Convert(converter = CommonStatusConverter.class)
    private CommonStatusEnum status;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;
}
