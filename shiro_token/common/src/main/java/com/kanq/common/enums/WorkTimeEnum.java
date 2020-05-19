package com.kanq.common.enums;

import lombok.Getter;

/**
 * 工作时间枚举类
 */
@Getter
public enum WorkTimeEnum {
    NO_REQUEST(0, "无要求"),
    ONE_TO_THREE(1, "1-3年"),
    THREE_TO_FIVE(2, "3-5年"),
    FIVE_TO_TEN(3, "5-10年"),
    OVER_TEN(4, "10年以上");

    private Integer id;

    private String description;

    WorkTimeEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static WorkTimeEnum fromId(Integer id) {
        for (WorkTimeEnum de : WorkTimeEnum.values()) {
            if (de.getId() == id) {
                return de;
            }
        }
        return ONE_TO_THREE;
    }
}
