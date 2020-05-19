package com.kanq.common.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum IdentityTypeEnum {
    CIVILIAN_TO_CADRES(1, "军转干部"),
    SOLDIER(2, "士兵"),
    SERGEANT(3, "士官");

    private Integer id;

    private String description;

    IdentityTypeEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static IdentityTypeEnum fromId(Integer id) {
        for (IdentityTypeEnum ite : IdentityTypeEnum.values()) {
            if (ite.getId() == id) {
                return ite;
            }
        }
        return CIVILIAN_TO_CADRES;
    }

}
