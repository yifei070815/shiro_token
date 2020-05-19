package com.kanq.common.converter;

import com.kanq.common.enums.ProjectStateEnum;

import javax.persistence.AttributeConverter;

public class ProjectStateConverter implements AttributeConverter<ProjectStateEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProjectStateEnum projectStateEnum) {
        return projectStateEnum.getId();
    }

    @Override
    public ProjectStateEnum convertToEntityAttribute(Integer integer) {
        return ProjectStateEnum.fromId(integer);
    }
}
