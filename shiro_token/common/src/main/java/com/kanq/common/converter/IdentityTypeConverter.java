package com.kanq.common.converter;

import com.kanq.common.enums.IdentityTypeEnum;

import javax.persistence.AttributeConverter;

public class IdentityTypeConverter implements AttributeConverter<IdentityTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(IdentityTypeEnum identityTypeEnum) {
        return identityTypeEnum.getId();
    }

    @Override
    public IdentityTypeEnum convertToEntityAttribute(Integer integer) {
        return IdentityTypeEnum.fromId(integer);
    }
}
