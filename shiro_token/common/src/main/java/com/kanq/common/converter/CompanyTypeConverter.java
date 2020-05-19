package com.kanq.common.converter;

import com.kanq.common.enums.CompanyTypeEnum;

import javax.persistence.AttributeConverter;

public class CompanyTypeConverter implements AttributeConverter<CompanyTypeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(CompanyTypeEnum companyTypeEnum) {
        return companyTypeEnum.getId();
    }

    @Override
    public CompanyTypeEnum convertToEntityAttribute(Integer integer) {
        return CompanyTypeEnum.fromId(integer);
    }
}
