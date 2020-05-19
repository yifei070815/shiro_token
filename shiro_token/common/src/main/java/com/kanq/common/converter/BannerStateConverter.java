package com.kanq.common.converter;

import com.kanq.common.enums.BannerStateEnum;

import javax.persistence.AttributeConverter;

public class BannerStateConverter implements AttributeConverter<BannerStateEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BannerStateEnum bannerStateEnum) {
        return bannerStateEnum.getId();
    }

    @Override
    public BannerStateEnum convertToEntityAttribute(Integer integer) {
        return BannerStateEnum.fromId(integer);
    }
}
