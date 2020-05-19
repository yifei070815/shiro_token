package com.kanq.common.converter;

import com.kanq.common.enums.PoliticalStateEnum;

import javax.persistence.AttributeConverter;

public class PoliticalStateConverter implements AttributeConverter<PoliticalStateEnum, Integer> {


    @Override
    public Integer convertToDatabaseColumn(PoliticalStateEnum politicalStateEnum) {
        return politicalStateEnum.getId();
    }

    @Override
    public PoliticalStateEnum convertToEntityAttribute(Integer integer) {
        return PoliticalStateEnum.fromId(integer);
    }
}
