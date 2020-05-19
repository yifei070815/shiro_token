package com.kanq.common.enums;

import lombok.Getter;

@Getter
public enum CompanyTypeEnum {
    VENTURE(1, "合资"),
    SOLELY_OWNED(2, "独资"),
    STATE_OWNED(3, "国有"),
    PRIVATE_OWNED(4, "私营"),
    WHOLE_OWNED(5, "全民所有制"),
    COLLECTIVE_OWNED(6, "集体所有制"),
    JOINT_STOCK(7, "股份制"),
    LIMITED_LIABILITY(8, "有限责任制"),
    LISTED(9, "已上市");

    private Integer id;

    private String description;

    CompanyTypeEnum(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static CompanyTypeEnum fromId(Integer id) {
        for (CompanyTypeEnum cte : CompanyTypeEnum.values()) {
            if (cte.getId() == id) {
                return cte;
            }
        }
        return VENTURE;
    }
}
