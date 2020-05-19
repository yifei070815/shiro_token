package com.kanq.common.converter;

import com.kanq.common.enums.ActivityStateEnum;

import javax.persistence.AttributeConverter;

public class ActivityStateConverter implements AttributeConverter<ActivityStateEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ActivityStateEnum activityStateEnum) {
        return activityStateEnum.getId();
    }

    @Override
    public ActivityStateEnum convertToEntityAttribute(Integer integer) {
        return ActivityStateEnum.fromId(integer);
    }
}


