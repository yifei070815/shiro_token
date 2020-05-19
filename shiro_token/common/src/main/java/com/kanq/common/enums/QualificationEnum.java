package com.kanq.common.enums;

import lombok.Getter;

/**
 * 学位枚举类
 */
@Getter
public enum QualificationEnum {
    POST_DOCTOR(1, "博士后"),
    DOCTOR(2, "博士"),
    MASTER(3, "硕士"),
    BACHELOR(4, "学士"),
    OTHER(5,"无");

    private Integer id;

    private String description;

    QualificationEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static QualificationEnum fromId(Integer id) {
        for (QualificationEnum de : QualificationEnum.values()) {
            if (de.getId() == id) {
                return de;
            }
        }
        return BACHELOR;
    }
}
