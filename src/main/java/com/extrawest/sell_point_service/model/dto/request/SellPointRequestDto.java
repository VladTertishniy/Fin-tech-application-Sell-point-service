package com.extrawest.sell_point_service.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellPointRequestDto {
    @NotBlank
    private String address;
    @NotBlank
    private String name;
    @Positive
    private float mark;
    private boolean isOfflineShop;
}
