package com.extrawest.sell_point_service.service.impl;

import com.extrawest.sell_point_service.model.SellPoint;
import com.extrawest.sell_point_service.model.dto.request.SellPointRequestDto;
import com.extrawest.sell_point_service.model.dto.response.DeleteResponseDto;
import com.extrawest.sell_point_service.model.dto.response.SellPointResponseDto;
import com.extrawest.sell_point_service.model.mapper.SellPointMapper;
import com.extrawest.sell_point_service.repository.SellPointRepository;
import com.extrawest.sell_point_service.service.SellPointService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class SellPointServiceImpl implements SellPointService {

    private final SellPointMapper sellPointMapper;
    private final SellPointRepository sellPointRepository;

    @Override
    public SellPointResponseDto create(SellPointRequestDto sellPointRequestDto) {
        SellPoint sellPoint = sellPointMapper.toModel(sellPointRequestDto);
        return sellPointMapper.toDto(sellPointRepository.save(sellPoint));
    }

    @Override
    public DeleteResponseDto delete(Long sellPointId) {
        SellPoint sellPoint = sellPointRepository.findById(sellPointId)
                .orElseThrow(() -> new NoSuchElementException("Sell point with id: " + sellPointId + " not found"));
        sellPointRepository.delete(sellPoint);
        return new DeleteResponseDto("Sell point with id: " + sellPointId + " deleted", sellPointId);
    }

    @Override
    public SellPointResponseDto update(Long sellPointId, SellPointRequestDto sellPointRequestDto) {
        SellPoint sellPoint = sellPointRepository.findById(sellPointId)
                .orElseThrow(() -> new NoSuchElementException("Sell point with id: " + sellPointId + " not found"));
        sellPoint.setAddress(sellPointRequestDto.getAddress());
        sellPoint.setMark(sellPointRequestDto.getMark());
        sellPoint.setOfflineShop(sellPointRequestDto.isOfflineShop());
        sellPoint.setName(sellPointRequestDto.getName());
        return sellPointMapper.toDto(sellPointRepository.save(sellPoint));
    }

    @Override
    public SellPointResponseDto getById(Long sellPointId) {
        return sellPointMapper.toDto(sellPointRepository.getById(sellPointId));
    }

    @Override
    public Page<SellPointResponseDto> getAll(Pageable pageable) {
        Page<SellPointResponseDto> sellPointResponseDtoPage = sellPointRepository.findAll(pageable).map(sellPointMapper::toDto);
        return new PageImpl<>(sellPointResponseDtoPage.getContent(), pageable, sellPointResponseDtoPage.getTotalElements());
    }
}
