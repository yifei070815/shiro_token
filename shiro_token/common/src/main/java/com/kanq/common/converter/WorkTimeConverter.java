package com.kanq.common.converter;

import com.kanq.common.enums.WorkTimeEnum;

import javax.persistence.AttributeConverter;

public class WorkTimeConverter implements AttributeConverter<WorkTimeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(WorkTimeEnum workTimeEnum) {
        return workTimeEnum.getId();
    }

    @Override
    public WorkTimeEnum convertToEntityAttribute(Integer integer) {
        return WorkTimeEnum.fromId(integer);
    }
}
