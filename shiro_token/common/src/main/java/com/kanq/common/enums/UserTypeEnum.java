package com.kanq.common.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum UserTypeEnum {
    PERSON(1, "个人用户"),
    COMPANY(2, "企业用户");

    private Integer id;

    private String description;

    UserTypeEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static UserTypeEnum fromId(Integer id) {
        for (UserTypeEnum ate : UserTypeEnum.values()) {
            if (ate.getId() == id) {
                return ate;
            }
        }
        return PERSON;
    }

}
