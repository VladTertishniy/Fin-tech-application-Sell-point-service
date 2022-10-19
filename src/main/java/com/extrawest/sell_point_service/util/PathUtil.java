package com.extrawest.sell_point_service.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathUtil {
    public static final String CREATE_PATH = "/create";
    public static final String UPDATE_PATH = "/update/{id}";
    public static final String DELETE_PATH = "/delete/{id}";
    public static final String SELL_POINTS_PATH = "/sellPoints";
    public static final String GET_BY_ID_PATH = "/getById/{id}";
    public static final String GET_ALL_ON_PAGE_PATH = "/getAllOnPage";
    public static final String GET_ALL_PATH = "/getAll";
    public static final String IS_SELL_POINT_EXIST_PATH = "/isSellPointExist/{id}";
}
