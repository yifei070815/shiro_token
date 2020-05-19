package com.kanq.common.enums;

import lombok.Getter;

@Getter
public enum CompanyStateEnum {
    SUBSIST(1, "存续"),
    IN_BUSINESS(2, "在业"),
    REVOKE(3, "吊销"),
    LOG_OUT(4, "注销"),
    MOVE_IN(5, "迁入"),
    MOVE_OUT(6, "迁出"),
    CLOSE_DOWN(7, "停业"),
    CLEAR_ACCOUNT(8, "清算");

    private Integer id;

    private String description;

    CompanyStateEnum(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static CompanyStateEnum fromId(Integer id) {
        for (CompanyStateEnum cte : CompanyStateEnum.values()) {
            if (cte.getId() == id) {
                return cte;
            }
        }
        return SUBSIST;
    }
}
