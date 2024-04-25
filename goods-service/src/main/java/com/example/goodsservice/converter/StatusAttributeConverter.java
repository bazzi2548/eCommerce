package com.example.goodsservice.converter;

import com.example.goodsservice.domain.StatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StatusAttributeConverter implements AttributeConverter<StatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(StatusEnum status) {
        return status == null ? StatusEnum.주문완료.getValue() : status.getValue();

    }

    @Override
    public StatusEnum convertToEntityAttribute(Integer dbData) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.getValue().equals(dbData)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
