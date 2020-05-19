package com.kanq.common.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum PoliticalStateEnum {
    PARTY_MEMBERS(1, "党员"),
    LEAGUE_MEMBERS(2, "团员"),
    MASSES(3, "群众");

    private Integer id;

    private String description;

    PoliticalStateEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static PoliticalStateEnum fromId(Integer id) {
        for (PoliticalStateEnum pse : PoliticalStateEnum.values()) {
            if (pse.getId() == id) {
                return pse;
            }
        }
        return PARTY_MEMBERS;
    }
}
