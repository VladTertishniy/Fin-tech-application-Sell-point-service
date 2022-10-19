package com.extrawest.sell_point_service.service.impl;

import com.extrawest.sell_point_service.exception.ApiRequestException;
import com.extrawest.sell_point_service.exception.ExceptionMessage;
import com.extrawest.sell_point_service.handler.CustomWebSocketHandler;
import com.extrawest.sell_point_service.model.AggregationSendingContainer;
import com.extrawest.sell_point_service.model.AggregationSendingOperation;
import com.extrawest.sell_point_service.model.SellPoint;
import com.extrawest.sell_point_service.model.dto.request.SellPointRequestDto;
import com.extrawest.sell_point_service.model.dto.response.DeleteResponseDto;
import com.extrawest.sell_point_service.model.dto.response.SellPointResponseDto;
import com.extrawest.sell_point_service.model.mapper.SellPointMapper;
import com.extrawest.sell_point_service.repository.SellPointRepository;
import com.extrawest.sell_point_service.service.SellPointService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class SellPointServiceImpl implements SellPointService {

    private final SellPointMapper sellPointMapper;
    private final SellPointRepository sellPointRepository;
    private WebSocketSession webSocketSession;
    @Value("${aggregateservice.uri}")
    private String aggregateServiceUri;

    @Override
    public SellPointResponseDto create(SellPointRequestDto sellPointRequestDto) {
        SellPoint sellPoint = sellPointMapper.toModel(sellPointRequestDto);
        SellPoint sellPointSaved = sellPointRepository.save(sellPoint);
        sendMessageToAggregateService(sellPointSaved, AggregationSendingOperation.SELL_POINT_CREATED);
        return sellPointMapper.toDto(sellPointSaved);
    }

    @Override
    public DeleteResponseDto delete(Long sellPointId) {
        SellPoint sellPoint = sellPointRepository.findById(sellPointId)
                .orElseThrow(() -> new NoSuchElementException("Sell point with id: " + sellPointId + " not found"));
        sellPointRepository.delete(sellPoint);
        sendMessageToAggregateService(sellPoint, AggregationSendingOperation.SELL_POINT_DELETED);
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
        SellPoint sellPointSaved = sellPointRepository.save(sellPoint);
        sendMessageToAggregateService(sellPointSaved, AggregationSendingOperation.SELL_POINT_UPDATED);
        return sellPointMapper.toDto(sellPointSaved);
    }

    @Override
    public SellPointResponseDto getById(Long sellPointId) {
        return sellPointMapper.toDto(sellPointRepository.findById(sellPointId).orElseThrow(
                () -> new NoSuchElementException("Sell point with id: " + sellPointId + " not found")
        ));
    }

    @Override
    public Page<SellPointResponseDto> getAll(Pageable pageable) {
        Page<SellPointResponseDto> sellPointResponseDtoPage = sellPointRepository.findAll(pageable).map(sellPointMapper::toDto);
        return new PageImpl<>(sellPointResponseDtoPage.getContent(), pageable, sellPointResponseDtoPage.getTotalElements());
    }

    @Override
    public List<SellPointResponseDto> getAll() {
        return sellPointRepository.findAll().stream().map(sellPointMapper::toDto).toList();
    }

    @Override
    public boolean isSellPointExist(Long id) {
        return sellPointRepository.findById(id).isPresent();
    }

    private void sendMessageToAggregateService(SellPoint sellPoint, AggregationSendingOperation aggregationSendingOperation) {
        try {
            AggregationSendingContainer aggregationSendingContainer = new AggregationSendingContainer();
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            aggregationSendingContainer.setAggregationSendingOperation(aggregationSendingOperation);
            aggregationSendingContainer.setObject(sellPoint);
            if (webSocketSession == null) setClientSession();
            if (!webSocketSession.isOpen()) setClientSession();
            webSocketSession.sendMessage(new TextMessage(ow.writeValueAsString(aggregationSendingContainer)));
        } catch (ExecutionException | InterruptedException | IOException e) {
            throw new ApiRequestException(ExceptionMessage.WEB_SOCKET_SENDING_ERROR);
        }
    }

    private void setClientSession() throws ExecutionException, InterruptedException {
        this.webSocketSession = new StandardWebSocketClient().doHandshake(
                new CustomWebSocketHandler(), new WebSocketHttpHeaders(), URI.create(aggregateServiceUri)
        ).get();
    }
}
