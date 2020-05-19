package com.kanq.common.enums;

import lombok.Getter;

/**
 * 资质审核类型枚举类
 */
@Getter
public enum CheckStateEnum {
    GET_PASS(1, "通过"),
    WAIT_AUDIT(2, "待审核"),
    NOT_PASS(3, "未通过");

    private Integer id;

    private String description;

    CheckStateEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static CheckStateEnum fromId(Integer id) {
        for (CheckStateEnum pse : CheckStateEnum.values()) {
            if (pse.getId() == id) {
                return pse;
            }
        }
        return WAIT_AUDIT;
    }
}
