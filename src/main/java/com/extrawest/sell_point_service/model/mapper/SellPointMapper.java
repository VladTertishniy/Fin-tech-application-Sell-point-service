package com.extrawest.sell_point_service.model.mapper;

import com.extrawest.sell_point_service.model.SellPoint;
import com.extrawest.sell_point_service.model.dto.request.SellPointRequestDto;
import com.extrawest.sell_point_service.model.dto.response.SellPointResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellPointMapper {
    SellPointResponseDto toDto (SellPoint sellPoint);
    SellPoint toModel (SellPointRequestDto sellPointRequestDto);
}
