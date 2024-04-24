package com.nighter.nightspot.models;


import java.io.Serializable;

public enum Role implements Serializable {
    SUPER_ADMIN("SUPER_ADMIN_USER"),
    ADMIN("ADMIN_USER"),
    BASE("BASE_USER");

    private final String name;

    Role(String name) {
        this.name = name;
    }
}
