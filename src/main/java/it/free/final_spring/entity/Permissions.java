package it.free.final_spring.entity;

public enum Permissions {
    USERS_READ("users:read"),
    USERS_WRITE("users:write");
    private final String permission;

    Permissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
