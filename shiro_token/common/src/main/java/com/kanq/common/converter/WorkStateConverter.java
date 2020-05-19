package com.kanq.common.converter;

import com.kanq.common.enums.WorkStateEnum;

import javax.persistence.AttributeConverter;

public class WorkStateConverter implements AttributeConverter<WorkStateEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(WorkStateEnum workStateEnum) {

        return workStateEnum.getId();
    }

    @Override
    public WorkStateEnum convertToEntityAttribute(Integer integer) {

        return WorkStateEnum.fromId(integer);
    }
}
