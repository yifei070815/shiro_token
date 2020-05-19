package com.kanq.common.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum WorkStateEnum {
    OTHER(0, "其它"),
    LEAVING(1, "离职"),
    SERVING(2, "在职"),
    STUDENT(3, "学生");

    private Integer id;

    private String description;

    WorkStateEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static WorkStateEnum fromId(Integer id) {
        for (WorkStateEnum wse : WorkStateEnum.values()) {
            if (wse.getId() == id) {
                return wse;
            }
        }
        return LEAVING;
    }

    public static WorkStateEnum fromDescription(String description) {
        for (WorkStateEnum wse : WorkStateEnum.values()) {
            if (description.equals(wse.getDescription())) {
                return wse;
            }
        }
        return LEAVING;
    }
}
