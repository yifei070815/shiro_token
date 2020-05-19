package com.kanq.common.converter;

import com.kanq.common.enums.BusinessTypeEnum;

import javax.persistence.AttributeConverter;

public class BusinessTypeConverter implements AttributeConverter<BusinessTypeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(BusinessTypeEnum businessTypeEnum) {
        return businessTypeEnum.getId();
    }

    @Override
    public BusinessTypeEnum convertToEntityAttribute(Integer integer) {
        return BusinessTypeEnum.fromId(integer);
    }
}
