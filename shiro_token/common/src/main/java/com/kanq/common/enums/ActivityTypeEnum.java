package com.kanq.common.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum ActivityTypeEnum {
    TRAINING(1, "培训"),
    FORUM(2,"论坛"),
    CONFERENCE(3,"会议");

    private Integer id;

    private String description;

    ActivityTypeEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }


    public static ActivityTypeEnum fromId(Integer id) {
        for (ActivityTypeEnum ate : ActivityTypeEnum.values()) {
            if (ate.getId() == id) {
                return ate;
            }
        }
        return TRAINING;
    }

}
