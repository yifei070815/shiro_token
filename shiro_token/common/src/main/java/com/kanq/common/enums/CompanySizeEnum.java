package com.kanq.common.enums;

import lombok.Getter;

/**
 * 工作时间枚举类
 */
@Getter
public enum CompanySizeEnum {
    LESS_THAN_TWENTY(1, "20人以内"),
    TWENTY_TO_HUNDRED(2, "20~99人"),
    ONE_TO_FIVE_HUNDRED(3, "100~500人"),
    FIVE_HUNDRED_TO_THOUSAND(4, "500~999人"),
    ONE_TO_TEN_THOUSAND(5, "1000~9999人"),
    MORE_THAN_ONE_THOUSAND(6, "10000人以上");

    private Integer id;

    private String description;

    CompanySizeEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static CompanySizeEnum fromId(Integer id) {
        for (CompanySizeEnum de : CompanySizeEnum.values()) {
            if (de.getId() == id) {
                return de;
            }
        }
        return ONE_TO_FIVE_HUNDRED;
    }




}
