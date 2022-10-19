package com.extrawest.sell_point_service.controller;

import com.extrawest.sell_point_service.model.dto.request.SellPointRequestDto;
import com.extrawest.sell_point_service.model.dto.response.DeleteResponseDto;
import com.extrawest.sell_point_service.model.dto.response.SellPointResponseDto;
import com.extrawest.sell_point_service.service.SellPointService;
import com.extrawest.sell_point_service.util.PathUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(PathUtil.SELL_POINTS_PATH)
@AllArgsConstructor
public class SellPointController {
    private SellPointService sellPointService;

    @PostMapping(PathUtil.CREATE_PATH)
    public ResponseEntity<SellPointResponseDto> create(@RequestBody @Valid SellPointRequestDto sellPointRequestDto) {
        return ResponseEntity.ok(sellPointService.create(sellPointRequestDto));
    }

    @PostMapping(PathUtil.UPDATE_PATH)
    public ResponseEntity<SellPointResponseDto> update(@RequestBody @Valid SellPointRequestDto sellPointRequestDto,
                                                       @PathVariable Long id) {
        return ResponseEntity.ok(sellPointService.update(id, sellPointRequestDto));
    }

    @GetMapping(PathUtil.DELETE_PATH)
    public ResponseEntity<DeleteResponseDto> delete(@PathVariable Long id) {
        return ResponseEntity.ok(sellPointService.delete(id));
    }

    @GetMapping(PathUtil.GET_BY_ID_PATH)
    public ResponseEntity<SellPointResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(sellPointService.getById(id));
    }

    @GetMapping(PathUtil.GET_ALL_ON_PAGE_PATH)
    public ResponseEntity<Page<SellPointResponseDto>> getAllOnPage(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(sellPointService.getAll(pageable));
    }

    @GetMapping(PathUtil.GET_ALL_PATH)
    public ResponseEntity<List<SellPointResponseDto>> getAll() {
        return ResponseEntity.ok(sellPointService.getAll());
    }

    @GetMapping(PathUtil.IS_SELL_POINT_EXIST_PATH)
    public ResponseEntity<Boolean> isSellPointExist(@PathVariable Long id) {
        return ResponseEntity.ok(sellPointService.isSellPointExist(id));
    }
}
