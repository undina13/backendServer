package com.undina.backendserver.model;

public enum Permission {
    PERMISSION_USER("user"),
    PERMISSION_ADMIN("admin");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
