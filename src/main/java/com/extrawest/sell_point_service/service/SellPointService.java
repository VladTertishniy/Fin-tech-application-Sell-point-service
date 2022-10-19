package com.extrawest.sell_point_service.service;

import com.extrawest.sell_point_service.model.dto.request.SellPointRequestDto;
import com.extrawest.sell_point_service.model.dto.response.DeleteResponseDto;
import com.extrawest.sell_point_service.model.dto.response.SellPointResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SellPointService {
    SellPointResponseDto create(SellPointRequestDto sellPointRequestDto);
    DeleteResponseDto delete(Long sellPointId);
    SellPointResponseDto update(Long sellPointId, SellPointRequestDto sellPointRequestDto);
    SellPointResponseDto getById(Long sellPointId);
    Page<SellPointResponseDto> getAll(Pageable pageable);
    List<SellPointResponseDto> getAll();
    boolean isSellPointExist(Long id);
}
