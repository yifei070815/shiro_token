package com.kanq.common.enums;

import lombok.Getter;

/**
 * 学历枚举类
 */
@Getter
public enum DegreeEnum {
    COLLEGE(1, "博士后"),
    BACHELOR(2, "博士研究生"),
    MASTER(3, "硕士研究生"),
    DOCTOR(4, "本科"),
    POST_DOCTOR(5, "大专"),
    HIGHER(6,"高中");

    private Integer id;

    private String description;

    DegreeEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static DegreeEnum fromId(Integer id) {
        for (DegreeEnum de : DegreeEnum.values()) {
            if (de.getId() == id) {
                return de;
            }
        }
        return BACHELOR;
    }
}
