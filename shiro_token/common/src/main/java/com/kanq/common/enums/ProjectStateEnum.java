package com.kanq.common.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum ProjectStateEnum {
    IS_NOT_PUBLISH(0, "待推送"),
    IS_PUBLISH(1, "已推送");

    private Integer id;

    private String description;

    ProjectStateEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static ProjectStateEnum fromId(Integer id) {
        for (ProjectStateEnum pse : ProjectStateEnum.values()) {
            if (pse.getId() == id) {
                return pse;
            }
        }
        return IS_NOT_PUBLISH;
    }

    public static ProjectStateEnum fromDescription(String description) {
        for (ProjectStateEnum pse : ProjectStateEnum.values()) {
            if (pse.getDescription().equals(description)) {
                return pse;
            }
        }
        return IS_PUBLISH;
    }

}
