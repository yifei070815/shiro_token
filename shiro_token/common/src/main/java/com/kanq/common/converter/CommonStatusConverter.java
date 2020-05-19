package com.kanq.common.converter;

import com.kanq.common.enums.CommonStatusEnum;

import javax.persistence.AttributeConverter;

public class CommonStatusConverter implements AttributeConverter<CommonStatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CommonStatusEnum commonStatusEnum) {
        return commonStatusEnum.getStatus();
    }

    @Override
    public CommonStatusEnum convertToEntityAttribute(Integer integer) {
        return CommonStatusEnum.fromStatus(integer);
    }
}
