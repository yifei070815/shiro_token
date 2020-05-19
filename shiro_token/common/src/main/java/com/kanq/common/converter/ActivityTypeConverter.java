package com.kanq.common.converter;

import com.kanq.common.enums.ActivityTypeEnum;

import javax.persistence.AttributeConverter;

public class ActivityTypeConverter implements AttributeConverter<ActivityTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ActivityTypeEnum activityTypeEnum) {
        return activityTypeEnum.getId();
    }

    @Override
    public ActivityTypeEnum convertToEntityAttribute(Integer integer) {
        return ActivityTypeEnum.fromId(integer);
    }
}
