package com.kanq.admin.entity.po;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="admin_role_permission", schema ="books")
public class adminRolePermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_id")
    private int roleId;

    @Column(name = "permission_id")
    private int permissionId;



}
