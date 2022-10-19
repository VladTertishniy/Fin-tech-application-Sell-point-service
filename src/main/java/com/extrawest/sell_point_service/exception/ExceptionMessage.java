package com.extrawest.sell_point_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {
    USER_NOT_FOUND("User was not found!"),
    USER_EXIST("User with such email already exist "),
    BAD_CREDENTIALS("Incorrect username or password!"),
    INVALID_TOKEN("JWT token is expired or invalid"),
    NO_USER("There's no user with id: "),
    NO_RIGHTS("You have no rights for doing this!"),
    WEB_SOCKET_SENDING_ERROR("Error while sending message via WebSocket"),
    FILE_SAVING_ERROR("Error saving file, something went wrong...");

    private final String exMessage;
}
