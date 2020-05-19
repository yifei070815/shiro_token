package com.kanq.common.converter;

import com.kanq.common.enums.QualificationEnum;

import javax.persistence.AttributeConverter;

public class QualificationConverter implements AttributeConverter<QualificationEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(QualificationEnum qualificationEnum) {
        return qualificationEnum.getId();
    }

    @Override
    public QualificationEnum convertToEntityAttribute(Integer integer) {
        return QualificationEnum.fromId(integer);
    }
}
