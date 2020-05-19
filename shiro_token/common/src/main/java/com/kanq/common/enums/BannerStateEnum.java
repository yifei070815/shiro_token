package com.kanq.common.enums;

import lombok.Getter;

/**
 * 活动类型枚举类
 */
@Getter
public enum BannerStateEnum {
    PUT_ON_SHELVES(0, "上架"),
    PULL_OFF_SHELVES(-1, "下架");

    private Integer id;

    private String description;

    BannerStateEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static BannerStateEnum fromId(Integer id) {
        for (BannerStateEnum pse : BannerStateEnum.values()) {
            if (pse.getId() == id) {
                return pse;
            }
        }
        return PUT_ON_SHELVES;
    }


}
