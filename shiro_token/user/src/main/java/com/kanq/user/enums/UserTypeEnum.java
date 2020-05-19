package com.kanq.user.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum UserTypeEnum {
    USER(1, "用户"),
    VIP_USER(2, "VIP用户");

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
        return USER;
    }

}
