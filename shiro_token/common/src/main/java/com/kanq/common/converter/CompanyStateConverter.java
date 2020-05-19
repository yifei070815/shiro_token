package com.kanq.common.converter;

import com.kanq.common.enums.CompanyStateEnum;

import javax.persistence.AttributeConverter;

public class CompanyStateConverter implements AttributeConverter<CompanyStateEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CompanyStateEnum companyStateEnum) {
        return companyStateEnum.getId();
    }

    @Override
    public CompanyStateEnum convertToEntityAttribute(Integer integer) {
        return CompanyStateEnum.fromId(integer);
    }
}
