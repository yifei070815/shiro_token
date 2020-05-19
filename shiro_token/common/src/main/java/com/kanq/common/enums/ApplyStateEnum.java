package com.kanq.common.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum ApplyStateEnum {
    NOT_APPLY(0, "未报名"),
    APPLY(1, "已报名");

    private Integer id;

    private String description;

    ApplyStateEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static ApplyStateEnum fromId(Integer id) {
        for (ApplyStateEnum ate : ApplyStateEnum.values()) {
            if (ate.getId() == id) {
                return ate;
            }
        }
        return NOT_APPLY;
    }

}
