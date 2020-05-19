package com.kanq.admin.enums;

import lombok.Getter;


@Getter
public enum RoleEnum {
    ADMIN(1, "管理员"),
    NOMAL_USER(2, "普通用户");

    private Integer status;

    private String description;

    RoleEnum(Integer status, String description) {
        this.status = status;
        this.description = description;
    }

    public static RoleEnum fromStatus(Integer status) {
        for (RoleEnum use : RoleEnum.values()) {
            if (use.status == status) {
                return use;
            }
        }
        return ADMIN;
    }






}
