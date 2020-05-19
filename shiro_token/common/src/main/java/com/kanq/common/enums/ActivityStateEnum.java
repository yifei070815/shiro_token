package com.kanq.common.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum ActivityStateEnum {
    NOT_PUBLISH(0, "待发布"),
    PUBLISH(1, "已发布");

    private Integer id;

    private String description;


    ActivityStateEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static ActivityStateEnum fromId(Integer id) {
        for (ActivityStateEnum ate : ActivityStateEnum.values()) {
            if (ate.getId() == id) {
                return ate;
            }
        }
        return NOT_PUBLISH;
    }

}
