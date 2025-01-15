package com.example.socialntw.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserType {
    USER("ROLE_USER"),
    CREATOR("ROLE_CREATOR"),
    ADMIN("ROLE_ADMIN");

    private final String value;
}
