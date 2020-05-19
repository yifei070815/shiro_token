package com.kanq.common.converter;

import com.kanq.common.enums.DegreeEnum;

import javax.persistence.AttributeConverter;

public class DegreeConverter implements AttributeConverter<DegreeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(DegreeEnum degreeEnum) {
        return degreeEnum.getId();
    }

    @Override
    public DegreeEnum convertToEntityAttribute(Integer integer) {
        return DegreeEnum.fromId(integer);
    }
}
