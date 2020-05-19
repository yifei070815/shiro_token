package com.kanq.common.enums;

import lombok.Getter;

@Getter
public enum BusinessTypeEnum {
    ELECTRONIC_COMMERCE(1, "电子商务"),
    GAME(2, "游戏"),
    MEDIUM(3, "媒体"),
    ADVERTISING(4, "广告营销"),
    DATA_SERVICE(5, "数据服务"),
    HEALTH_CARE(6, "医疗健康"),
    LIFE_SERVICE(7, "生活服务"),
    O_TO_O(8, "O2O"),
    TOURISM(9, "旅游"),
    INFORMATION(10, "分类信息"),
    ON_LINE_EDU(11, "在线教育"),
    SOCIAL_NETWORK(12, "社交网络"),
    INTERNET(13, "互联网"),
    COMPUTER_SOFT(14, "计算机软件"),
    ONLINE_FINANCE(15, "互联网金融"),
    LOGISTICS(16, "物流"),
    INTELLIGENT_HARDWARE(17, "智能硬件"),
    CAR(18, "汽车"),
    CONSULTANT(19, "咨询"),
    OTHERS(20, "其它");

    private Integer id;

    private String description;

    BusinessTypeEnum(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public static BusinessTypeEnum fromId(Integer id) {
        for (BusinessTypeEnum cte : BusinessTypeEnum.values()) {
            if (cte.getId() == id) {
                return cte;
            }
        }
        return ELECTRONIC_COMMERCE;
    }
}
