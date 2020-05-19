package com.kanq.common.converter;

import com.kanq.common.enums.UserTypeEnum;

import javax.persistence.AttributeConverter;

public class UserTypeConverter implements AttributeConverter<UserTypeEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserTypeEnum userTypeEnum) {
        return userTypeEnum.getId();
    }

    @Override
    public UserTypeEnum convertToEntityAttribute(Integer integer) {
        return UserTypeEnum.fromId(integer);
    }


}
