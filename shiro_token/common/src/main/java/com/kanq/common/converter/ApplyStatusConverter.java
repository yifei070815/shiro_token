package com.kanq.common.converter;

import com.kanq.common.enums.ApplyStateEnum;

import javax.persistence.AttributeConverter;

public class ApplyStatusConverter implements AttributeConverter<ApplyStateEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ApplyStateEnum applyStateEnum) {
        return applyStateEnum.getId();
    }

    @Override
    public ApplyStateEnum convertToEntityAttribute(Integer integer) {
        return ApplyStateEnum.fromId(integer);
    }
}
