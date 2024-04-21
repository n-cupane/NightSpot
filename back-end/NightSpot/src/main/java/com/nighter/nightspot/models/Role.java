package com.nighter.nightspot.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum Role {
    SUPER_ADMIN("SUPER_ADMIN_USER"),
    ADMIN("ADMIN_USER"),
    BASE("BASE_USER");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
