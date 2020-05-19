package com.kanq.common.converter;

import com.kanq.common.enums.CompanySizeEnum;

import javax.persistence.AttributeConverter;

public class CompanySizeConverter implements AttributeConverter<CompanySizeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CompanySizeEnum companySizeEnum) {
        return companySizeEnum.getId();
    }

    @Override
    public CompanySizeEnum convertToEntityAttribute(Integer integer) {
        return CompanySizeEnum.fromId(integer);
    }
}
