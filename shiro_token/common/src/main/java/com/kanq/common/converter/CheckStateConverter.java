package com.kanq.common.converter;

import com.kanq.common.enums.CheckStateEnum;

import javax.persistence.AttributeConverter;

public class CheckStateConverter implements AttributeConverter<CheckStateEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CheckStateEnum checkStateEnum) {
        return checkStateEnum.getId();
    }

    @Override
    public CheckStateEnum convertToEntityAttribute(Integer integer) {
        return CheckStateEnum.fromId(integer);
    }
}
